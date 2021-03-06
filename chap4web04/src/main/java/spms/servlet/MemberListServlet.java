package spms.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1. 사용할 JDBC드라이버를 등록하라.
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //ServletConfig config = this.getServletConfig();
            //Class.forName(config.getInitParameter("driver"));
            // GenericServlet 가 이미 ServlitConfig를 구현..

            //Class.forName(this.getInitParameter("driver"));

            ServletContext ctx = this.getServletContext();
            Class.forName(ctx.getInitParameter("driver"));

            // 드라이버를 사용하여 MySQL 서버와 연결.
            con = DriverManager.getConnection(
                    ctx.getInitParameter("url"),
                    ctx.getInitParameter("username"),
                    ctx.getInitParameter("password"));

            //3. 커넥션 객체로부터 SQL을 던질  객체를 준비. Statement
            stmt = con.createStatement();

            //4. SQL을 던지는 객체를 사용하여 서버에 질의하라
            rs = stmt.executeQuery(
                    "SELECT MNO,MNAME,EMAIL,CRE_DATE,MOD_DATE FROM MEMBERS ORDER BY MNO ASC");

            //5. 서버에서 가져온 데이터를 사용하여 HTML을 만들어서 웹 브라우저에 출력하라.
            servletResponse.setContentType("text/html; charset=UTF-8");

            PrintWriter out = servletResponse.getWriter();
            out.println("<html><head><title>회원목록</title></head><body>");
            out.println("<h1> 회원 목록 </h1>");
            out.println("<p><a href ='add'> 신규회원 </a></p> ");
            while (rs.next()) {
                out.println(
                        rs.getInt("MNO") + "," +
                                "<a href='update?no=" + rs.getInt("MNO") + "'>" +
                                rs.getString("MNAME") + "</a>," +
                                rs.getString("EMAIL") + "," +
                                rs.getDate("CRE_DATE") +
                                "<a href='delete?no=" + rs.getInt("MNO") +
                                "'>[삭제]</a><br>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try{rs.close();}catch (Exception e){}
            try{stmt.close();}catch (Exception e){}
            try{con.close();}catch (Exception e){}

        }


    }



}
