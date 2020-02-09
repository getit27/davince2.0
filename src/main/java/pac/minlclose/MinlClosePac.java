package pac.minlclose;

import pac.inter.Accessable;
import pac.inter.AvaSrc;
import pac.key.KeyData;
import pac.StockPac;
import pac.key.KeyTime;
import pac.minl.MinlData;

import java.sql.ResultSet;
import java.util.Vector;

public class MinlClosePac extends StockPac implements Accessable, AvaSrc {
    static final Class<?> datatype=MinlData.class;
    Vector<MinlCloseData>data=new Vector<MinlCloseData>();
    public MinlClosePac(String se, int sn){
        stockexchange=se;
        stocknum=sn;
    }

    public MinlClosePac(String sen) {
        super(sen);
    }

    public MinlClosePac(){};
    public void newData(MinlCloseData minlclose){
        data.add(minlclose);
    }
    public Vector<MinlCloseData> getDayClose(){ return data; }

    // Accessable

    String[] columns=new String[]{
            "ymd",
            "hm",
            "closeprice"
    };

    boolean accessed=false;

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
        return stockexchange+String.format("%06d",stocknum)+"minl";
    }

    @Override
    public String getColumn(int index) {
        return columns[index];
    }

    @Override
    public int getColumnLength() {
        return columns.length;
    }

    @Override
    public void accImportData(ResultSet rs) throws Exception {
        int data=rs.getInt("ymd");
        int time=rs.getInt("hm");
        int cp=rs.getInt("closeprice");
        MinlCloseData d=new MinlCloseData(data,time,cp);
        newData(d);
    }

    // AvaSrc

    @Override
    public int getDataSize() {
        return data.size();
    }

    @Override
    public int getComData(int index) {
        return data.get(index).getCloseprice();
    }

    @Override
    public KeyData getKeyData(int index) {
        return new KeyTime(
                data.get(index).getYmd(),
                data.get(index).getHm()
        );
    }
}

