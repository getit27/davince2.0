package pac.dayvol;

import pac.Packet;
import pac.StockPac;
import pac.inter.Accessible;
import pac.inter.AvaSrc;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.sql.ResultSet;
import java.util.Vector;

public class DayVolPac extends StockPac implements AvaSrc, Accessible {
    Vector<DayVolData> data=new Vector<>();

    public DayVolPac(String se,int sn){
        stockExchange =se;
        stockNum =sn;
        pactype =DayVolPac.class;
    }

    public DayVolPac(String sen) {
        super(sen);
    }

    void newData(DayVolData dvd){
        data.add(dvd);
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((DayVolPac)pac).data);
        return this;
    }

    // AvaSrc

    @Override
    public int getASDataSize() {
        return data.size();
    }

    @Override
    public int getASData(int index) {
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
        return stockExchange +String.format("%06d", stockNum)+"day";
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
