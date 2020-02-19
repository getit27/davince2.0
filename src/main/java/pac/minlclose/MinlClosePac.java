package pac.minlclose;

import pac.Packet;
import pac.inter.*;
import pac.key.KeyData;
import pac.StockPac;
import pac.key.KeyTime;
import pac.minl.MinlData;
import pac.minlgrow.MinlGrowPac;

import java.sql.ResultSet;
import java.util.Vector;

public class MinlClosePac extends StockPac implements LimAccessable,Accessable, AvaSrc, GrowSrc, MinGrowthSrc {
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

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((MinlClosePac)pac).data);
        return this;
    }

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
    public int getASDataSize() {
        return data.size();
    }

    @Override
    public int getASData(int index) {
        return data.get(index).getCloseprice();
    }

    @Override
    public KeyData getKeyData(int index) {
        return new KeyTime(
                data.get(index).getYmd(),
                data.get(index).getHm()
        );
    }

    // GrowSrc


    @Override
    public int getGSDataSize() {
        return data.size();
    }

    @Override
    public int getGSData(int index) {
        return data.get(index).getCloseprice();
    }

    // LimAccess

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

    // MinGrowthSrc

    @Override
    public void MinGrowthSrcInitialize() {
        src=MinGrowthSrc.class;
        rstType=new Class<?>[]{MinlGrowPac.class};
    }

    @Override
    public int getMgsDataSize() {
        return data.size();
    }

    @Override
    public int getMgsData(int index) {
        return data.get(index).getCloseprice();
    }

    @Override
    public boolean isLastMin(int index) {
        return data.get(index).getHm()==1500;
    }

    // Mcomputable

    Class<?> src=null;
    Class<?>[] rstType=null;

    @Override
    public Class<?> getSrc() {
        return src;
    }

    @Override
    public Class<?>[] getRstType() {
        return rstType;
    }
}

