package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPoll {
    String url;
    String username;
    String password;

    ArrayList<Connection> connList = new ArrayList<Connection>();

    public DBConnectionPoll(String driver, String url, String username, String password)
    throws Exception{
        this.url = url;
        this.username = username;
        this.password = password;

        Class.forName(driver);
    }

    public Connection getConnecion() throws Exception {
        if (connList.size() > 0) {
            Connection conn = connList.get(0);
            if (conn.isValid(10)) {
                return conn;
            }
        }
        return DriverManager.getConnection(url, username, password);
    }

    public void returnConnection(Connection conn) throws Exception {
        connList.add(conn);
    }

    public void closeAll() {
        for (Connection conn : connList) {
            try{conn.close();}catch (Exception e){}
        }
    }

}
