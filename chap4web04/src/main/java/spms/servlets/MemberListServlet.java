package spms.servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1. 사용할 JDBC드라이버를 등록하라.

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //2. 드라이버를 사용하여 MySQL 서버와 연결.
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb"
                    , "root"
                    , "dltjsgh");
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
            while(rs.next()) {
                out.println(
                        rs.getInt("MNO") + "," +
                                rs.getString("MNAME") + "," +
                                rs.getString("EMAIL") + "," +
                                rs.getDate("CRE_DATE") + "<br>"
                );
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            try{rs.close();}catch (Exception e){}
            try{stmt.close();}catch (Exception e){}
            try{con.close();}catch (Exception e){}

        }


    }



}
