package csci201team25.unicate_server;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // parse JSON request body
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JsonObject json = new Gson().fromJson(sb.toString(), JsonObject.class);
        String commentText = json.get("text").getAsString();
        int userId = json.get("userId").getAsInt();
        int locId = json.get("locId").getAsInt();

        // insert into comments table
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:????/[DATABASE_NAME]", "user", "password");
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO Comments (userID, content, locID) VALUES (?, ?, ?)")) {

            stmt.setInt(1, userId);
            stmt.setString(2, commentText);
            stmt.setInt(3, locId);
            stmt.executeUpdate();

            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
