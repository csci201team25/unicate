package csci201team25.unicate_server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
//import org.mindrot.jbcrypt.BCrypt; //hashing library

@WebServlet("/RegisterVerify")
public class RegisterVerify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/unicate?user=root&password=root");

            String sql = "INSERT INTO Users (Username, HashedPassword) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            String unhashedPW = request.getParameter("Password");
            // security is overrated
//            String hashedPW = BCrypt.hashpw(unhashedPW, BCrypt.gensalt());
            ps.setString(1, request.getParameter("UserName"));
            ps.setString(2, unhashedPW); //hashedPW);

            ps.executeUpdate();

            response.sendRedirect("AddSchool.html");
            return;

        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("Register.html?error=registration_failed");
        }
         finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null){
                 conn.close();
                }
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}