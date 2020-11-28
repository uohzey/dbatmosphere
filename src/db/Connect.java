package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import entity.User;

public class Connect {
//    private String url="jdbc:mysql://127.0.0.1:3306/test1";
//    private String uesrname="root";
//    private String password="jk518858";
//    private Connection con;

    private Connection connection=null;
    private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
    private String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=camoc";//数据源  ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
    private String Name="******";
    private String Pwd="******";
    private Statement stmt = null;
    private ResultSet rs = null;

    public Connect() {
        try {
            Class.forName(driverName);
            connection=DriverManager.getConnection(dbURL,"******","******");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet query(String str) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public User Login(String name, String password) {
        try {

            Statement stmt = connection.createStatement();
            System.out.println(name+" "+password);
            rs = stmt.executeQuery("select * from DBUser where username='"+name+"' and password='"+password+"'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(rs==null) {
            return null;
        }
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        return user;
    }
//    public static void main(String[] args) {
//        Connect cc=new Connect();
//        cc.query();
//    }
}
