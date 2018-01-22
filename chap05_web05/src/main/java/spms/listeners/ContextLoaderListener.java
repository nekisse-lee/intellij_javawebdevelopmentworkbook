package spms.listeners;

import javafx.application.Application;
import spms.context.ApplicationContext;
import spms.controls.*;
import spms.dao.MySqlMemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

//import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext sc = event.getServletContext();

            String propertiesPath = sc.getRealPath(
                    sc.getInitParameter("contextConfigLocation"));

            applicationContext = new ApplicationContext(propertiesPath);

//            ds = new BasicDataSource();
//            ds.setDriverClassName(sc.getInitParameter("driver"));
//            ds.setUrl("url");
//            ds.setUsername("username");
//            ds.setPassword("password");

            //서버(톰캣) 에서 데이터 소스관리!
//            InitialContext initialContext = new InitialContext();
//            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");

//            MySqlMemberDao memberDao = new MySqlMemberDao();
//            memberDao.setDataSource(ds);

//            MySqlMemberDao memberDao = new MySqlMemberDao();
//            memberDao.setDataSource(ds);
////            sc.setAttribute("memberDao", memberDao);
//            sc.setAttribute("/auth/login.do",
//                    new LogInController().setMemberDao(memberDao));
//            sc.setAttribute("/auth/logout.do", new LogOutController());
//            sc.setAttribute("/member/list.do",
//                    new MemberListController().setMemberDao(memberDao));
//            sc.setAttribute("/member/add.do",
//                    new MemberAddController().setMemberDao(memberDao));
//            sc.setAttribute("/member/update.do",
//                    new MemberUpdateController().setMemberDao(memberDao));
//            sc.setAttribute("/member/delete.do",
//                    new MemberDeleteController().setMemberDao(memberDao));

        } catch(Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

//        try {if(ds != null)ds.close();} catch (SQLException e) {}
    }
}
