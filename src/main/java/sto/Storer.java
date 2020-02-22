// 使用interface的储存器
package sto;

import pac.inter.Storable;

import java.sql.*;

import static set.DataBaseSet.*;

public class Storer {

    public static String selectName(String tablename){
        return " SELECT table_name " +
                "FROM information_schema.TABLES " +
                "WHERE table_name ='"+
                tablename+
                "'";
    }
    public static String selectColumn(String tablename,String column){
        return "SELECT column_name " +
                "FROM information_schema.columns " +
                "WHERE table_name='"+tablename+"'" +
                "AND column_name='"+column+"'";
    }
    public static String selectData(String tablename,String[]target,String[]column,String[]value){
        String sql="SELECT ";
        for(int i=0;i<target.length;i++){
            if(i<target.length-1){
                sql+=target[i]+",";
            }else{
                sql+=target[i]+" ";
            }
        }
        sql+="FROM "+tablename+" ";
        sql+="WHERE ";
        for(int i=0;i<column.length;i++){
            if(i<column.length-1){
                sql+=column[i]+"="+value[i]+" AND ";
            }else{
                sql+=column[i]+"="+value[i]+" ";
            }
        }
        return sql;
    }
    public static String createTable(String tablename,String[] column,String[] comment,String[] primary){
        String sql="CREATE TABLE `"+tablename+"` (";
        for(int i=0;i<column.length;i++){
            sql+=" `"+column[i]+"` "+comment[i]+",";
        }
        sql+="PRIMARY KEY (";
        for(int i=0;i<primary.length;i++){
            if(i<primary.length-1)
                sql+="`"+primary[i]+"`,";
            else
                sql+="`"+primary[i]+"`)";
        }
        sql+=") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;";
        return sql;
    }
    public static String insertData(String tablename,String[]target,String[]value){
        String sql="INSERT INTO " + tablename + " ";
        sql+="(";
        for(int i=0;i<target.length;i++){
            if(i<target.length-1){
                sql+=target[i]+",";
            }else{
                sql+=target[i]+")";
            }
        }
        sql+="VALUE (";
        for(int i=0;i<value.length;i++){
            if(i<value.length-1){
                sql+=value[i]+",";
            }else{
                sql+=value[i]+")";
            }
        }
        return sql;
    }
    public static String updateData(String tablename,String[]target,String[]tvalue,
                                    String[]condition,String[]cvalue){
        String sql="UPDATE "+tablename+" SET ";
        for(int i=0;i<target.length;i++){
            if(i<target.length-1)
                sql+=target[i]+"="+tvalue[i]+",";
            else
                sql+=target[i]+"="+tvalue[i]+" ";
        }
        sql+="WHERE ";
        for(int i=0;i<condition.length;i++){
            if(i<condition.length-1)
                sql+=condition[i]+"="+cvalue[i]+" AND ";
            else
                sql+=condition[i]+"="+cvalue[i];
        }
        return sql;
    }

    public void store(Storable sta)throws Exception{

        if(sta.getStoreData().length==0)
            return;
        Connection conn = null;
        Statement stmt = null;
        try {

            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 实例化Statement对象
            stmt = conn.createStatement();

            String sql0 =selectName(sta.getTableName());
            ResultSet rs=stmt.executeQuery(sql0);

            // 如果表不存在则创建表
            if(!rs.next()){
                String sql1=createTable(sta.getTableName(),sta.getColumns(),sta.getComments(),sta.getPrimaryKeys());
                stmt.execute(sql1);
            }

            // 否则检查各属性是否存在
            else{
                for(int i=0;i<sta.getColumns().length;i++){
                    String sql2=selectColumn(sta.getTableName(),sta.getColumns()[i]);
                    rs=stmt.executeQuery(sql2);

                    //如果不存在，添加该属性
                    if(!rs.next()){
                        String sql3="alter table "+sta.getTableName()+" add "+sta.getColumns()[i];
                        sql3+=" "+sta.getComments()[i];
                        stmt.execute(sql3);
                    }
                }
            }

            // 插入数据
            String sql4 ="INSERT INTO "+sta.getTableName()+"(";
            for(int i=0;i<sta.getColumns().length;i++){
                if(i<sta.getColumns().length-1){
                    sql4+=sta.getColumns()[i]+",";
                }else{
                    sql4+=sta.getColumns()[i]+")";
                }
            }
            sql4+="VALUE\n(";
            for(int i = 0; i<sta.getStoreData().length; i++) {
                Object[] obj = sta.getStoreData()[i];
                String[]columnsvalue=new String[sta.getColumns().length];
                for(int j=0;j<columnsvalue.length;j++){
                    columnsvalue[j]=(obj[j]).toString();
                }
                for (int j = 0; j < columnsvalue.length; j++) {
                    if (j < columnsvalue.length - 1) {
                        sql4 += columnsvalue[j] + ",";
                    } else {
                        sql4 += columnsvalue[j] + ")";
                    }
                }
                if(i<sta.getStoreData().length-1)
                    sql4+=",\n(";
            }
            sql4+="on duplicate key update ";
            String[]target=new String[sta.getNpLocation().length];
            for(int i=0;i<target.length;i++){
                target[i]=sta.getColumns()[sta.getNpLocation()[i]];
                sql4+=target[i]+"=values("+target[i]+")";
                if(i<target.length-1)
                    sql4+=",";
            }
            //System.out.println(sql4);
            stmt.execute(sql4);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){ }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
