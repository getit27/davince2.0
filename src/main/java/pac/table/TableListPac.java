package pac.table;

import pac.Packet;
import pac.inter.LimAccessible;

import java.sql.ResultSet;
import java.util.Vector;

public class TableListPac extends Packet implements LimAccessible {
    private Class<?> packagetype=TableListPac.class;

    Vector<String> data=new Vector<>();

    String dbName= "runoob";

    public TableListPac(String dbName){
        this.dbName=dbName;
    }

    public int getDataLength(){
        return data.size();
    }

    public String getData(int index){
        return data.get(index);
    }

    public boolean contains(String element){
        return data.contains(element);
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((TableListPac)pac).data);
        return this;
    }

    // LimAccessable

    boolean accessed=false;

    @Override
    public int getLimitLength() {
        return 1;
    }

    @Override
    public String getLimit(int index) {
        return "table_schema='"+dbName+"'";
    }

    @Override
    public boolean isAccessed() {
        return accessed;
    }

    @Override
    public void getAccessed() {
        accessed=true;
    }

    @Override
    public String getTableName() {
        return "information_schema.tables";
    }

    @Override
    public String getColumn(int index) {
        return "table_name";
    }

    @Override
    public int getColumnLength() {
        return 1;
    }

    @Override
    public void accImportData(ResultSet rs) throws Exception {
        String table=rs.getString("table_name");
        data.add(table);
    }
}
