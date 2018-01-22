package spms.test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

// ServletContext에 보관된 MySqlMemberDao 사용하기
@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
//        RequestDispatcher rd = request.getRequestDispatcher(
//                "/auth/LogInForm.jsp");
//        rd.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
            Member member = memberDao.exist(
                    request.getParameter("email"),
                    request.getParameter("password"));
            if (member != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                request.setAttribute("viewUrl", "redirect:../member/list.do");
//                response.sendRedirect("../member/list");

            } else {
                request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
//                RequestDispatcher rd = request.getRequestDispatcher(
//                        "/auth/LogInFail.jsp");
//                rd.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);

        }
    }
}
