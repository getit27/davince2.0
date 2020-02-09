package pac.dayvol;

import pac.StockPac;
import pac.inter.Accessable;
import pac.inter.AvaSrc;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.sql.ResultSet;
import java.util.Vector;

public class DayVolPac extends StockPac implements AvaSrc, Accessable {
    Vector<DayVolData> data=new Vector<>();

    public DayVolPac(String se,int sn){
        stockexchange=se;
        stocknum=sn;
        packagetype=DayVolPac.class;
    }

    public DayVolPac(String sen) {
        super(sen);
    }

    void newData(DayVolData dvd){
        data.add(dvd);
    }

    // AvaSrc

    @Override
    public int getDataSize() {
        return data.size();
    }

    @Override
    public int getComData(int index) {
        return data.get(index).getVolume();
    }

    @Override
    public KeyData getKeyData(int index) {
        return new KeyYmd(data.get(index).getYmd());
    }

    // Accessable

    String[] columns=new String[]{
            "ymd",
            "volume"
    };

    boolean accessed=false;

    @Override
    public void getAccessed() {
        accessed=true;
    }

    @Override
    public boolean isAccessed() {
        return accessed;
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
    public void accImportData(ResultSet rs) throws Exception{
        int data=rs.getInt("ymd");
        int vol=rs.getInt("volume");
        DayVolData dvd=new DayVolData(data,vol);
        newData(dvd);
    }
}
