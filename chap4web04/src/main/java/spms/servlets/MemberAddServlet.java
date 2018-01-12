package spms.servlets;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
//@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>회원 등록2222</title></head>");
        out.println("<body><h1>회원 등록</h1>");
        out.println("<form action='add' method='post'>");
        out.println("이름: <input type='text' name='name'><br>");
        out.println("이메일: <input type='text' name='email'><br>");
        out.println("암호: <input type='password' name='password'><br>");
        out.println("<input type='submit' value='추가'>");
        out.println("<input type='reset' value='취소'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;
        req.setCharacterEncoding("UTF-8");

        try {
//            DriverManager.registerDriver(new Driver());
            Class.forName(this.getInitParameter("driver"));


            conn = DriverManager.getConnection(
                    this.getInitParameter("url"), //JDBC URL
                    this.getInitParameter("username"),	// DBMS 사용자 아이디
                    this.getInitParameter("password"));	// DBMS 사용자 암호
            stmt = conn.prepareStatement(
                    "INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
                            + " VALUES (?,?,?,NOW(),NOW())");
            stmt.setString(1, req.getParameter("email"));
            stmt.setString(2, req.getParameter("password"));
            stmt.setString(3, req.getParameter("name"));
            stmt.executeUpdate();

            resp.sendRedirect("list");

            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>회원등록결과</title>"
                    +"<meta http-equiv='Refresh' content='1;url=list'>"
                    +"</head>");
            out.println("<body>");
            out.println("<p>등록 성공입니다!</p>");
            out.println("</body></html>");
//            resp.setHeader("Refresh", "1;url=list");
            //resp.addHeader("Refresh", "1;url=list");

        } catch (Exception e) {
            throw new ServletException(e);

        } finally {
            try {if (stmt != null) stmt.close();} catch(Exception e) {}
            try {if (conn != null) conn.close();} catch(Exception e) {}
        }

    }
}
