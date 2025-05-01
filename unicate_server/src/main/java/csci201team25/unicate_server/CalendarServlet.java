import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// System.out.println("Servlet CP");
	    
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// Checking for an active session
		Boolean active_session = false;
		HttpSession session = request.getSession(false);
		int userID = 0;
		if (session != null) {
			// https://stackoverflow.com/questions/6031278/session-attribute-access-and-converting-to-int
			userID = (Integer) session.getAttribute("userID");
			active_session = true;
		}

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
			if (active_session) {
				query = "SELECT u.UniversityName, u.CalendarDates " +
	                    "FROM UserUniversity uu " +
	                    "JOIN Universities u ON uu.uniID = u.uniID " +
	                    "WHERE uu.userID = " + userID;
			}
			// SQL query if the user isn't logged in (getting universities from URL in the form of uni IDs)
			else {
			    String[] universityIDs = request.getParameter("universities").split(",");
			    String id_list = "";
			    for (int i = 0; i < universityIDs.length; i++) {
			        String id = universityIDs[i].trim();
			        id_list = id_list + id;
			        if (i < universityIDs.length - 1) {
			            id_list = id_list + ",";
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

			    JsonReader reader = Json.createReader(new StringReader(json_dates));
			    JsonArray array = reader.readArray();
			    
			    // Going through all of the events to find the specific event in a list of events
			    for (int i = 0; i < array.size(); i++) {
			    	// https://stackoverflow.com/questions/7634518/getting-jsonobject-from-jsonarray
			    	JsonObject event = array.getJsonObject(i);
			    	if (overlap_event.equals(event.getString("event"))) {
			            String start = event.getString("start_date");
			            String end = event.getString("end_date");
			            breaks.put(uni, new String[]{start, end});
			            break;
			        }
			    }
			}
			
			// Sending break dates to frontend as JSON object
			JsonObjectBuilder json_builder = Json.createObjectBuilder();
			for (Map.Entry<String, String[]> entry : breaks.entrySet()) {
				String uni_name = entry.getKey();
				JsonArray dates_array = Json.createArrayBuilder().add(entry.getValue()[0]).add(entry.getValue()[1]).build();
				json_builder.add(uni_name, dates_array);
			} 
			// System.out.println("JSON Builder CP: " + json_builder.build().toString());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json_builder.build().toString());

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
