package pac.dayava;

import pac.*;
import pac.dayclose.DayCloseData;
import pac.daydev.DayDevPac;
import pac.dayva.DayVAPac;
import pac.inter.Accessable;
import pac.inter.AvaRst;
import pac.inter.Storable;
import pac.inter.mcom.DeviationSrc;
import pac.key.KeyData;
import pac.key.KeyYmd;
import tol.DateShift;

import java.sql.ResultSet;
import java.util.Vector;

public class DayAvaPac extends StockPac implements Storable, AvaRst/*, DeviationSrc*/, Accessable {

    private Vector<KeyYmd>keys=new Vector<>();
    private Vector<Integer>datas=new Vector<>();
    int aveSize;
    public DayAvaPac(String se, int sn,int si){
        stockExchange =se;
        stockNum =sn;
        aveSize =si;
    }
    public DayAvaPac(){}
    public int getAvaSize(){ return aveSize; }
    public int getDataSize(){ return keys.size();}
    public void newData(KeyYmd key,int data){
        keys.add(key);
        datas.add(data);
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("addAll:type mismatch!");
        keys.addAll(((DayAvaPac)pac).keys);
        datas.addAll(((DayAvaPac)pac).datas);
        return this;
    }

    // Storable

    @Override
    public Class<?> getPacType() {
        return DayAvaPac.class;
    }

    @Override
    public String getTableName() {
        return this.getStockExchange()+
                String.format("%06d", this.getStockNum())+
                "day";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                getAvaSize()+"ave"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int COMMENT '"+getAvaSize()+"日均值*100'"
        };
    }

    @Override
    public String[] getPrimaryKeys() {
        return new String[]{"ymd"};
    }

    @Override
    public int[] getNpLocation() {
        return new int[]{1};
    }

    @Override
    public int[] getPrimaryLocation() {
        return new int[]{0};
    }

    @Override
    public Class<?>[] getType() {
        return new Class[]{int.class,int.class};
    }

    Object[][] storedata=null;
    @Override
    public Object[][] getStoreData() {
        if(storedata==null){
            Object[][] objss = new Object[getDataSize()][2];
            for(int i=0;i<getDataSize();i++){
                objss[i] = new Object[]{
                        keys.get(i).getKey(0),datas.get(i)
                };
            }
            storedata=objss;
        }
        return storedata;
    }

    // AvaRst


    @Override
    public void iniAvaRst(String exchangename, int stocknum, int size) {
        this.stockExchange =exchangename;
        this.stockNum =stocknum;
        this.aveSize =size;
    }

    @Override
    public void importDataAR(KeyData key, int ava) {
        newData((KeyYmd) key,ava);
    }
/*
    // DeviationSrc

    int comLocation;

    @Override
    public void deviationSrcInitialize() {
        src=DeviationSrc.class;
        rstType=new Class<?>[]{DayDevPac.class};
        comLocation=0;
    }

    @Override
    public int getDvsDataSize() {
        return getDataSize();
    }

    @Override
    public int getDvsData(int index) {
        return datas.get(index);
    }

    @Override
    public KeyData getKeyData(int index) {
        return keys.get(index);
    }

    @Override
    public boolean next() {
        if(comLocation<getDataSize())
            comLocation++;
        return comLocation < getDataSize();
    }

    @Override
    public boolean end() {
        return comLocation < getDataSize();
    }

    @Override
    public boolean move(KeyData key) {
        int maxIndex=0;
        int minIndex=0;
        try {
            if(key.compare(getKeyData())==1){
                minIndex=comLocation+1;
                maxIndex=Math.min(
                        comLocation+DateShift.comDateShift((int)key.getKey(0),(int)getKeyData().getKey(0)),getDataSize()
                );
            }else if(key.compare(getKeyData())==-1){
                maxIndex=comLocation;
                minIndex=Math.max(
                        0,comLocation-DateShift.comDateShift((int)key.getKey(0),(int)getKeyData().getKey(0))
                );
            }else{
                return true;
            }

            int midIndex=(minIndex+maxIndex)/2;
            while(maxIndex-minIndex>1){
                if(getKeyData(midIndex).compare(key)==1)
                    maxIndex=midIndex;
                else if(getKeyData(midIndex).compare(key)==-1)
                    minIndex=midIndex+1;
                else {
                    comLocation = midIndex;
                    return true;
                }
            }

            // 如果匹配失败，则将值修改为比目标值大1的新值；
            comLocation=maxIndex;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean move(int index) {
        if(index>=getDataSize())
            return false;
        comLocation=index;
        return true;
    }

    @Override
    public int getDvsData() {
        return datas.get(comLocation);
    }

    @Override
    public KeyData getKeyData() {
        return keys.get(comLocation);
    }

    @Override
    public int compareKey(DeviationSrc dvs) {
        int ret=-2;
        try {
            ret=getKeyData().compare(dvs.getKeyData());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
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

    */
    // Accessable

    String[] columns=new String[]{
            "ymd",
            getAvaSize()+"ave"
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
    public String getColumn(int index) {
        return columns[index];
    }

    @Override
    public int getColumnLength() {
        return columns.length;
    }

    @Override
    public void accImportData(ResultSet rs) throws Exception {
        int date=rs.getInt("ymd");
        int cp=rs.getInt("closeprice");
        newData(new KeyYmd(date),cp);
    }
}
