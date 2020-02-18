package set;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class DataBaseSet {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    public static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";

    public static String DB_NAME ="RUNOOB";

    // 数据库的用户名与密码，需要根据自己的设置
    public static String USER = "root";
    public static String PASS = "root";
    public static void loadDataBaseSet()throws Exception{
        Properties pro = new Properties();
        FileInputStream in = new FileInputStream("a.properties");
        pro.load(in);
        JDBC_DRIVER=pro.getProperty("JDBC_DRIVER");
        DB_URL=pro.getProperty("DB_URL");
        USER=pro.getProperty("USER");
        PASS=pro.getProperty("PASS");
        DB_NAME=pro.getProperty("DB_NAME");
        in.close();
    }
}
