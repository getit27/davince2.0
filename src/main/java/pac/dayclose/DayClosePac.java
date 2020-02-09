package pac.dayclose;
import pac.inter.Accessable;
import pac.*;
import pac.inter.AvaSrc;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.sql.ResultSet;
import java.util.Vector;
public class DayClosePac extends StockPac implements Accessable, AvaSrc {
    Vector<DayCloseData>data=new Vector<DayCloseData>();
    public DayClosePac(String se, int sn){
        stockexchange=se;
        stocknum=sn;
    }

    public DayClosePac(String sen) {
        super(sen);
    }

    public DayClosePac(){};
    public void newDay(DayCloseData dayclose){
        data.add(dayclose);
    }
    public Vector<DayCloseData> getDayClose(){ return data; }

    // Accessable

    String[] columns=new String[]{
            "ymd",
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
        return stockexchange+String.format("%06d",stocknum)+"day";
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
        int cp=rs.getInt("closeprice");
        DayCloseData d=new DayCloseData(data,cp);
        newDay(d);
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
        return new KeyYmd(data.get(index).getYmd());
    }
}

