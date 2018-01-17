package spms.listeners;

import org.apache.commons.dbcp.BasicDataSource;
import spms.dao.MemberDao;
import spms.util.DBConnectionPoll;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;

//import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
//    BasicDataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext sc = event.getServletContext();

//            ds = new BasicDataSource();
//            ds.setDriverClassName(sc.getInitParameter("driver"));
//            ds.setUrl("url");
//            ds.setUsername("username");
//            ds.setPassword("password");

            //서버(톱캣) 에서 데이터 소스관리!
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");

            MemberDao memberDao = new MemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("memberDao", memberDao);

        } catch(Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

//        try {if(ds != null)ds.close();} catch (SQLException e) {}
    }
}
