package pac.table;

import pac.Packages;
import pac.inter.LimAccessable;

import java.sql.ResultSet;
import java.util.Vector;

public class TableListPac extends Packages implements LimAccessable {
    private Class<?> packagetype=TableListPac.class;

    Vector<String> data=new Vector<>();

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
    public Packages addAll(Packages pac) throws  Exception{
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
        return "table_schema='runoob'";
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
