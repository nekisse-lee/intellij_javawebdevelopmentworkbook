package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.*;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        try {

            ServletContext sc = this.getServletContext();

            HashMap<String, Object> model = new HashMap<String, Object>();
            model.put("memberDao", sc.getAttribute("memberDao"));

            String pageControllerPath = null;
            Controller  pageController = null;


            if ("/member/list.do".equals(servletPath)) {
                pageController = new MemberListController();
//                pageControllerPath = "/member/list";

            } else if ("/member/add.do".equals(servletPath)) {
                pageController = new MemberAddController();
//                pageControllerPath = "/member/add";
                if (request.getParameter("email") != null) {
                    model.put("member", new Member()
                            .setEmail(request.getParameter("email"))
                            .setPassword(request.getParameter("password"))
                            .setName(request.getParameter("name")));
                }
//                if (request.getParameter("email") != null) {
//                    request.setAttribute("member", new Member()
//                            .setEmail(request.getParameter("email"))
//                            .setPassword(request.getParameter("password"))
//                            .setName(request.getParameter("name")));
//                }
            } else if ("/member/update.do".equals(servletPath)) {
                pageController = new MemberUpdateontroller();
//                pageControllerPath = "/member/update";
                if (request.getParameter("email") != null) {
                    model.put("member", new Member()
                            .setNo(Integer.parseInt(request.getParameter("no")))
                            .setEmail(request.getParameter("email"))
                            .setName(request.getParameter("name")));
//                    request.setAttribute("member", new Member()
//                            .setNo(Integer.parseInt(request.getParameter("no")))
//                            .setEmail(request.getParameter("email"))
//                            .setName(request.getParameter("name")));
                } else {
                    model.put("no", new Integer(request.getParameter("no")));
                }
            } else if ("/member/delete.do".equals(servletPath)) {
                pageController = new MemberDeleteController();
                model.put("no", new Integer(request.getParameter("no")));
//                pageControllerPath = "/member/delete";
            } else if ("/auth/login.do".equals(servletPath)) {
                pageController = new LogInController();
                if (request.getParameter("email") != null) {
                    model.put("loninInfo", new Member()
                            .setEmail(request.getParameter("email"))
                            .setEmail(request.getParameter("password")));
                }
//                pageControllerPath = "/auth/login";
            } else if ("/auth/logout.do".equals(servletPath)) {
                pageController = new LogOutController();
//                pageControllerPath = "/auth/logout";
            }

            // 페이지 컨트롤러를 실행한다.
            String viewUrl = pageController.execute(model);
            // Map 객체에 저장된 값을 ServletRequest에 복사한다.
            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }

//            RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
//            rd.include(request, response);

//            String viewUrl = (String) request.getAttribute("viewUrl");
            if (viewUrl.startsWith("redirect:")) {
                response.sendRedirect(viewUrl.substring(9));
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
                rd.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
    }
}
