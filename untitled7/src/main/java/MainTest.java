
import java.sql.*;

import static java.lang.Class.forName;

public class MainTest {
  public static void main(String[] args) throws SQLException{
    //创建java程序与数据库的连接对象
    Connection connection = null;
    //创建执行静态SQL语句的接口对象
    Statement statement = null;
    //创建游标对象，返回查询结果集
    ResultSet resultSet = null;
    //将MySQL数据库驱动名称封装在字符串中
    String driver = "com.mysql.cj.jdbc.Driver";
    //指定使用数据库的路径、编码格式、时区，并以字符串进行封装
    String url = "jdbc:mysql://localhost:3306/yzj?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    //指定登录账户
    String user = "root";
    //指定账户密码
    String psw = "1926669707";
    //加载数据库驱动
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    //连接数据库
    connection = DriverManager.getConnection(url,user,psw);
    //通过Connection对象获取Statement对象
    statement = connection.createStatement();
    String sql = "select * from teacher";
    resultSet = statement.executeQuery(sql);
    System.out.println("id\t|\tuserName\t|\tuserPsw");
    while(resultSet.next()){
      int id = resultSet.getInt("tno");
      String userName = resultSet.getString("name");
      String userPsw = resultSet.getString("sex");
      System.out.println(id+"\t|\t\t"+userName+"\t\t|\t"+userPsw);
    }
    if (resultSet!=null)resultSet.close();
    if (statement!=null)statement.close();
    if (connection!=null)connection.close();

  }
}


