package spms.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;


        try {
            ServletContext ctx = this.getServletContext();
            Class.forName(ctx.getInitParameter("driver"));

            conn = DriverManager.getConnection(
                    ctx.getInitParameter("url"),
                    ctx.getInitParameter("username"),
                    ctx.getInitParameter("password"));
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "DELETE FROM MEMBERS WHERE MNO=" +
                            req.getParameter("no")
            );
            resp.sendRedirect("list");


        } catch (Exception e) {
            throw new ServletException(e);
        }finally {
            try {if (stmt != null) stmt.close();} catch(Exception e) {}
            try {if (conn != null) conn.close();} catch(Exception e) {}
        }
    }
}
