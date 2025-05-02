package csci201team25.unicate_server;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/AddUniversitiesServlet")
public class AddUniversitiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// System.out.println("Servlet CP");
	    
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// db info (name and login)
		String db = "unicate";
		String username = "root";
		String password = "root";		
		String connection = "jdbc:mysql://localhost/" + db.trim() + "?user=" + username.trim() + "&password=" + password.trim();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connection);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT uniID, UniversityName FROM Universities");
			
			// sending uni ID and name to frontend as JSON object
			Map<String,String> uniMap = new HashMap<>();
			String id = "";
			String uni_name = "";
			while (rs.next()) {
				id = rs.getString("uniID");
				uni_name = rs.getString("UniversityName");
				uniMap.put(id, uni_name);
			} 			
			
            		Gson gson = new GsonBuilder().create();
			String json = gson.toJson(uniMap);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} 
		
		// catching errors 
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println ("ClassNotFoundException: " + e.getMessage());
		} 
		
		// Closing objects
		// https://stackoverflow.com/questions/22671697/try-try-with-resources-and-connection-statement-and-resultset-closing
		finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());		    
			}

			try {
				if (st != null) st.close();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());		    
			}

			try {
				if (rs != null) rs.close();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());		    
			}
		}	
	} 
}

