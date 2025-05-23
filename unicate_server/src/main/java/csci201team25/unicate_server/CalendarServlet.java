package csci201team25.unicate_server;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// System.out.println("Servlet CP");
	    
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// Checking for an active session
		boolean active_session = false;
		HttpSession session = request.getSession(false);
		
		String userID = request.getParameter("userID");
		System.out.println("userID: <" + userID + ">");
		

		// Variable information
		String overlap_event = "Spring Break";
		String db = "unicate";
		String username = "root";
		String password = "root";
		String connection = "jdbc:mysql://localhost/" + db.trim() + "?user=" + username.trim() + "&password=" + password.trim();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connection);
			st = conn.createStatement();
			
			String query = "";
			// SQL query if the user is logged in (getting universities from SQL with userID)
			if (userID != null) {
				query = "SELECT u.UniversityName, u.CalendarDates " +
	                    "FROM UserUniversity uu " +
	                    "JOIN Universities u ON uu.uniID = u.uniID " +
	                    "WHERE uu.userID = " + userID;
			}
			// SQL query if the user isn't logged in (getting universities from URL in the form of uni IDs)
			else {
				List<String> universityIDs = new LinkedList<>();
				universityIDs.add(request.getParameter("uni1"));
				universityIDs.add(request.getParameter("uni2"));
				universityIDs.add(request.getParameter("uni3"));
				universityIDs.add(request.getParameter("uni4"));
				universityIDs.add(request.getParameter("uni5"));
			    String id_list = "";
			    boolean first = true;
			    for (int i = 0; i < universityIDs.size(); i++) {
			    	String id = universityIDs.get(i).trim();
			    	if (id.equals("0")) {
			        	continue; 
			        }
			    	else if (first) {
			        	id_list = id;
			        	first = false;
			        } 
			        else {
			        	id_list = id_list + "," + id;
			    	}
			    }
			    query = "SELECT UniversityName, CalendarDates FROM Universities WHERE uniID IN (" + id_list + ")";
			}
			rs = st.executeQuery(query);
			
			// Storing the break times in a map
			Map<String, String[]> breaks = new HashMap<>();
			while (rs.next()) {
				String uni = rs.getString("UniversityName");
			    	String json_dates = rs.getString("CalendarDates");
			    
			    	// System.out.println("Uni CP: " + uni);
			    	// System.out.println("Dates JSON CP: " + json_dates);
			    
			    	JsonElement jsonElement = JsonParser.parseString(json_dates);
			    	JsonArray array = jsonElement.getAsJsonArray();
			    
			    	// Going through all of the events to find the specific event in a list of events
				for (int i = 0; i < array.size(); i++) {
					JsonElement elem = array.get(i);
				    	JsonObject event = elem.getAsJsonObject();
			    		if (overlap_event.equals(event.get("event").getAsString())) {
				            	String start = event.get("start_date").getAsString();
				           	String end = event.get("end_date").getAsString();
				            	breaks.put(uni, new String[]{start, end});
				            	break;
			        	}
			    	}
			}

			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(breaks);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
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
