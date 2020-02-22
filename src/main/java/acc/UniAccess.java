package acc;

import pac.inter.Accessible;
import set.DataBaseSet;

import java.sql.*;

public class UniAccess implements Accesser {
    Accessible ap=null;
    public UniAccess(Accessible p){
        ap=p;
    }

    @Override
    public Class<?> getPacType() {
        return ap.getClass();
    }

    @Override
    public Accessible getPackage() {

        Connection conn =null;
        Statement stmt=null;

        try{
            if(ap==null){
                throw new Exception("Accessable is not initialize");
            }
            Class.forName(DataBaseSet.JDBC_DRIVER);
            conn = DriverManager.getConnection(DataBaseSet.DB_URL,DataBaseSet.USER,DataBaseSet.PASS);
            stmt = conn.createStatement();
            String sql="SELECT ";
            for(int i = 0; i<ap.getColumnLength(); i++){
                if(i<ap.getColumnLength()-1)
                    sql+=ap.getColumn(i)+",";
                else
                    sql+=ap.getColumn(i)+" FROM "+ap.getTableName();
            }
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                ap.accImportData(rs);
            }
            rs.close();
            stmt.close();
            conn.close();
            ap.getAccessed();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return ap;
    }
}
