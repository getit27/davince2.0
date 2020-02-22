package pac.dayclose;
import pac.inter.*;
import pac.*;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.sql.ResultSet;
import java.util.LinkedHashMap;

public class DayClosePac extends StockPac implements Accessible, AvaSrc, GrowSrc, LimAccessible {
    LinkedHashMap<KeyYmd, Integer> data=new LinkedHashMap<>();
    public DayClosePac(String se, int sn){
        stockExchange =se;
        stockNum =sn;
    }

    public DayClosePac(String sen) {
        super(sen);
    }

    public DayClosePac(){};

    public void newDay(DayCloseData dayclose){
        data.put(dayclose.getKey(),dayclose.getCloseprice());
    }
    public LinkedHashMap<KeyYmd, Integer> getData(){ return data; }

    public Object get(int index){
        KeyYmd key=(KeyYmd)data.keySet().toArray()[index];
        return data.get(key);
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.putAll(((DayClosePac)pac).data);
        return this;
    }

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
    public void accImportData(ResultSet rs) throws Exception {
        int data=rs.getInt("ymd");
        int cp=rs.getInt("closeprice");
        DayCloseData d=new DayCloseData(data,cp);
        newDay(d);
    }

    // AvaSrc

    @Override
    public int getASDataSize() {
        return data.size();
    }

    @Override
    public int getASData(int index) {
        return data.get((KeyYmd) data.keySet().toArray()[index]);
    }

    @Override
    public KeyData getKeyData(int index) {
        return (KeyYmd) data.keySet().toArray()[index];
    }
    // GrowSrc

    @Override
    public int getGSDataSize() {
        return data.size();
    }

    @Override
    public int getGSData(int index) {
        return data.get((KeyYmd) data.keySet().toArray()[index]);
    }

    // LimAccessable

    String[]Limitations=null;

    public void setLimitations(String[] limitations) {
        Limitations = limitations;
    }

    @Override
    public int getLimitLength() {
        return Limitations.length;
    }

    @Override
    public String getLimit(int index) {
        return Limitations[index];
    }
}

