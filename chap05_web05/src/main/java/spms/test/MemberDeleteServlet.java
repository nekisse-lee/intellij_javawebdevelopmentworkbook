package spms.test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;

// ServletContext에 보관된 MySqlMemberDao 사용하기
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");

            memberDao.delete(Integer.parseInt(request.getParameter("no")));
            request.setAttribute("viewUrl", "redirect:list.do");
//            response.sendRedirect("list");

        } catch (Exception e) {
            throw new ServletException(e);
//            e.printStackTrace();
//            request.setAttribute("error", e);
//            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//            rd.forward(request, response);

        }
    }
}
