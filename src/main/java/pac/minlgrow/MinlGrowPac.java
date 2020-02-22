package pac.minlgrow;

import pac.Packet;
import pac.StockPac;
import pac.dev.MinlDevPac;
import pac.inter.Accessible;
import pac.inter.mcom.DeviationSrc;
import pac.inter.mcom.MinGrowthRst;
import pac.inter.Storable;
import pac.key.KeyData;
import pac.key.KeyTime;
import tol.DateShift;

import java.sql.ResultSet;
import java.util.Vector;

public class MinlGrowPac extends StockPac implements Storable, MinGrowthRst, Accessible, DeviationSrc {
    private Vector<KeyTime>keys=new Vector<>();
    private Vector<Integer> datas =new Vector<>();

    public MinlGrowPac(String se, int sn) {
        super(se, sn);
        pactype = MinlGrowPac.class;
    }

    public MinlGrowPac(String sen) {
        super(sen);
        pactype = MinlGrowPac.class;
    }

    public MinlGrowPac() {
        super();
        pactype = MinlGrowPac.class;
    }

    public int getDataSize(){
        return keys.size();
    }

    @Override
    public Class<?> getPacType() {
        return super.getPacType();
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        keys.addAll(((MinlGrowPac)pac).keys);
        datas.addAll(((MinlGrowPac)pac).datas);
        return this;
    }

    public void newData(KeyTime key, int data){
        keys.add(key);
        datas.add(data);
    }

/*
    // GrowRst

    @Override
    public void iniGrowRst(String exchangename, int stocknum,int size) {
        this.stockexchange=exchangename;
        this.stocknum=stocknum;
        this.size=size;
    }

    @Override
    public void importDataGR(KeyData key, int grows) {
        newData(new MinlGrowData((int)key.getKey(0),(int)key.getKey(1),grows));
    }
*/

    // Storable


    @Override
    public String getTableName() {
        return getStockExchange()+
                String.format("%06d", getStockNum())+
                "minl";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                "hm",
                "growth_rate"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int NOT NULL",
                "int COMMENT '增长率*10000'"
        };
    }

    @Override
    public String[] getPrimaryKeys() {
        return new String[]{
                "ymd",
                "hm"
        };
    }

    @Override
    public int[] getNpLocation() {
        return new int[]{2};
    }

    @Override
    public int[] getPrimaryLocation() {
        return new int[]{0,1};
    }

    @Override
    public Class<?>[] getType() {
        return new Class[]{
                int.class,
                int.class,
                int.class
        };
    }

    Object[][] stodata=null;
    @Override
    public Object[][] getStoreData() {
        if(stodata==null){
            Object[][] objss=new Object[getDataSize()][3];
            for(int i=0;i<getDataSize();i++){
                objss[i]=new Object[]{
                        keys.get(i).getKey(0),
                        keys.get(i).getKey(1),
                        datas.get(i)
                };
            }
            stodata=objss;
        }
        return stodata;
    }

    // MinGrowthRst

    @Override
    public void minGrowthRstInitialize(String stockexchange, int stocknum) {
        this.stockExchange =stockexchange;
        this.stockNum =stocknum;
    }

    @Override
    public void importMgrData(KeyData key, int growth) {
        newData((KeyTime)key,growth);
    }

    // Accessible

    String[] columns=new String[]{
            "ymd",
            "hm",
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
        int time=rs.getInt("hm");
        int gr=rs.getInt("growth_rate");
        newData(new KeyTime(date,time),gr);
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
        rstType=new Class<?>[]{MinlDevPac.class};
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
