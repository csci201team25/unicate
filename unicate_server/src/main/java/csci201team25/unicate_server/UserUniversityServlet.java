package csci201team25.unicate_server;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.Console;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/UserUniversityServlet")
public class UserUniversityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String db = "unicate";
		String username = "root";
		String password = "root";
		String connection = "jdbc:mysql://localhost/" + db.trim() + "?user=" + username.trim() + "&password=" + password.trim();

		ArrayList<String> uniIDs = new ArrayList<String>();
		
		String userID = request.getParameter("userId");
		
		String uniID_1 = request.getParameter("uni1");
		if (!uniID_1.equals("0")){
			uniIDs.add(uniID_1);
		}
		String uniID_2 = request.getParameter("uni2");
		if (!uniID_2.equals("0")){
			uniIDs.add(uniID_2);
		}
		String uniID_3 = request.getParameter("uni3");
		if (!uniID_3.equals("0")){
			uniIDs.add(uniID_3);
		}
		String uniID_4 = request.getParameter("uni4");
		if (!uniID_4.equals("0")){
			uniIDs.add(uniID_4);
		}
		String uniID_5 = request.getParameter("uni5");
		if (!uniID_5.equals("0")){
			uniIDs.add(uniID_5);
		}
		
		try {
			// remove previous user uni pairs for given user
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(connection);
			
			String delQuery = "DELETE FROM UserUniversity WHERE userID = (?)";
			
			PreparedStatement ps = conn.prepareStatement(delQuery);
			ps.setString(1, userID);
			System.out.println(delQuery);
			ps.executeUpdate();

			// then, insert the new user uni pairs
			
			String query = "INSERT INTO UserUniversity (userID, uniID) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			
			for(String item: uniIDs) {
				ps.setString(1, userID);
				ps.setString(2, item);
				ps.executeUpdate();
				System.out.println(query);
			}
			
			response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("verified");
	        
		} 
		
		// Catching errors 
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
