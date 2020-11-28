package test;
import java.sql.*;
import com.mysql.jdbc.Statement;
/**
 * Create by uohzey on 2020/11/17
 * You can contact me through my email yezhou19@mail.edu.ustc.cn
 */
public class tt {
     Connection connection=null;
     String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
     String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=camoc";//数据源  ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
     String Name="sa";
     String Pwd="jk518858";

     public tt(){
         try{
             Class.forName(driverName);
             connection=DriverManager.getConnection(dbURL,"sa","jk518858");
             System.out.println("连接数据库成功");
         }catch(Exception e){
             e.printStackTrace();
             System.out.println("连接失败");
         }
     }

    public static void main(String[] args) {
        tt tt2=new tt();
    }


}
