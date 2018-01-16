package spms.servlets;

import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(
                "/member/MemberFrom.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
        ServletContext sc = this.getServletContext();
        conn = (Connection) sc.getAttribute("conn");

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            memberDao.insert(new Member()
                    .setEmail(req.getParameter("email"))
                    .setPassword(req.getParameter("password"))
                    .setName(req.getParameter("name")));

            resp.sendRedirect("list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req,resp);
        }
    }
}
