package pac.daygrow;

import pac.Packet;
import pac.StockPac;
import pac.dev.DayDevPac;
import pac.inter.Accessible;
import pac.inter.GrowRst;
import pac.inter.Storable;
import pac.inter.mcom.DeviationSrc;
import pac.key.KeyData;
import pac.key.KeyYmd;
import tol.DateShift;

import java.sql.ResultSet;
import java.util.Vector;

public class DayGrowPac extends StockPac implements GrowRst, Storable, Accessible, DeviationSrc {
    private Vector<KeyYmd>keys=new Vector<>();
    private Vector<Integer> datas =new Vector<>();
    int size;

    public DayGrowPac(String se, int sn) {
        super(se, sn);
        pactype =DayGrowPac.class;
    }

    public DayGrowPac(String sen) {
        super(sen);
        pactype =DayGrowPac.class;
    }

    public DayGrowPac() {
        super();
        pactype =DayGrowPac.class;
    }

    public int getDataSize(){
        return keys.size();
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        keys.addAll(((DayGrowPac)pac).keys);
        datas.addAll(((DayGrowPac)pac).datas);
        return this;
    }

    @Override
    public Class<?> getPacType() {
        return super.getPacType();
    }

    public void newData(KeyYmd key,int data){
        keys.add(key);
        this.datas.add(data);
    }

    // GrowRst

    @Override
    public void iniGrowRst(String exchangename, int stocknum,int size) {
        this.stockExchange =exchangename;
        this.stockNum =stocknum;
        this.size=size;
    }

    @Override
    public void importDataGR(KeyData key, int grows) {
        newData((KeyYmd)key,grows);
    }

    // Storable


    @Override
    public String getTableName() {
        return getStockExchange()+
                String.format("%06d", getStockNum())+
                "day";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                //size+
                "growth_rate"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int COMMENT '"+size+"增长率*10000'"
        };
    }

    @Override
    public String[] getPrimaryKeys() {
        return new String[]{
                "ymd"
        };
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
        return new Class[]{
                int.class,int.class
        };
    }

    Object[][] stodata=null;
    @Override
    public Object[][] getStoreData() {
        if(stodata==null){
            Object[][] objss=new Object[getDataSize()][2];
            for(int i=0;i<getDataSize();i++){
                objss[i]=new Object[]{
                        keys.get(i).getKey(0),
                        datas.get(i)
                };
            }
            stodata=objss;
        }
        return stodata;
    }

    // Accessible

    String[] columns=new String[]{
            "ymd",
            "growth_rate"
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
        int gr=rs.getInt("growth_rate");
        newData(new KeyYmd(date),gr);
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
        return comLocation<getDataSize();
    }

    @Override
    public boolean end() {
        return comLocation>=getDataSize();
    }

    @Override
    public boolean move(KeyData key) {
        int maxIndex=0;
        int minIndex=0;
        try {
            if(key.compare(getKeyData())==1){
                minIndex=comLocation+1;
                maxIndex=Math.min(
                        comLocation+ DateShift.comDateShift((int)key.getKey(0),(int)getKeyData().getKey(0)),getDataSize()
                );
            }else if(key.compare(getKeyData())==-1){
                maxIndex=comLocation;
                minIndex=Math.max(
                        0,comLocation-DateShift.comDateShift((int)key.getKey(0),(int)getKeyData().getKey(0))
                );
            }else{
                return true;
            }

            while(maxIndex-minIndex>0){
                int midIndex=(minIndex+maxIndex)/2;
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
}
