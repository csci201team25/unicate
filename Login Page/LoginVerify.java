import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginVerify")
public class LoginVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean isName = false;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/unicate?user=root&password=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM users WHERE Username = \'" + request.getParameter("Username") + "\' AND HashedPassword = \'" + request.getParameter("Password") + "\';");
			rs = ps.executeQuery();
			isName = false;
			if (rs.next()) {
				isName = true;
			}
		} catch (SQLException s) {
			System.out.println ("Exception: " + s.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.println ("Exception: " + c.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		String output = "incorrect";
		if (isName) {
			output = "verified";
		}
		PrintWriter out = response.getWriter();
		System.out.println("Output: " + output);
		response.setContentType("text/plain");
        out.print(output);
        out.flush();
	}
}
