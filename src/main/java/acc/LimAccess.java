package acc;

import pac.inter.LimAccessible;
import set.DataBaseSet;

import java.sql.*;

public class LimAccess implements Accesser {
    LimAccessible lap=null;
    public LimAccess(LimAccessible p){
        lap=p;
    }

    @Override
    public Class<?> getPacType() {
        return lap.getClass();
    }

    @Override
    public LimAccessible getPackage() {

        Connection conn =null;
        Statement stmt=null;

        try{
            if(lap==null){
                throw new Exception("Accessable is not initialize");
            }
            Class.forName(DataBaseSet.JDBC_DRIVER);
            conn = DriverManager.getConnection(DataBaseSet.DB_URL,DataBaseSet.USER,DataBaseSet.PASS);
            stmt = conn.createStatement();
            String sql="SELECT ";
            for(int i = 0; i<lap.getColumnLength(); i++){
                if(i<lap.getColumnLength()-1)
                    sql+=lap.getColumn(i)+",";
                else
                    sql+=lap.getColumn(i)+" FROM "+lap.getTableName();
            }
            // 条件
            sql+=" WHERE ";
            for(int i=0;i<lap.getLimitLength();i++){
                if(i<lap.getLimitLength()-1)
                    sql+=lap.getLimit(i)+" AND ";
                else
                    sql+=lap.getLimit(i);
            }
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                lap.accImportData(rs);
            }
            rs.close();
            stmt.close();
            conn.close();
            lap.getAccessed();
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
        return lap;
    }
}
