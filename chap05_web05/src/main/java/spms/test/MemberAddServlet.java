package spms.test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

// ServletContext에 보관된 MySqlMemberDao 사용하기
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewUrl","/member/MemberForm.jsp");
//        RequestDispatcher rd = request.getRequestDispatcher(
//                "/member/MemberForm.jsp");
//        rd.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();
            MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");

            Member member = (Member) request.getAttribute("member");
            memberDao.insert(member);

            request.setAttribute("viewUrl","redirect:list.do");

//            memberDao.insert(new Member()
//                    .setEmail(request.getParameter("email"))
//                    .setPassword(request.getParameter("password"))
//                    .setName(request.getParameter("name")));
//
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
