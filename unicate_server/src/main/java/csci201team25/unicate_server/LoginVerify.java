package csci201team25.unicate_server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

@WebServlet("/LoginVerify")
public class LoginVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String username = request.getParameter("Username");
		String password = request.getParameter("Password");

		Map<String, Object> result = new TreeMap<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/unicate?user=root&password=root");

			ps = conn.prepareStatement("SELECT * FROM users WHERE Username = ? AND HashedPassword = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				int userID = rs.getInt("userID");

				result.put("status", "verified");
				result.put("userID", userID);
				result.put("username", username);
			} else {
				result.put("status", "incorrect");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
			try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
			try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
		}

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(result));
		out.flush();
	}
}
