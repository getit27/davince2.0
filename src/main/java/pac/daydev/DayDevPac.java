package pac.daydev;

import pac.Packet;
import pac.StockPac;
import pac.inter.Storable;
import pac.inter.mcom.DeviationRst;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.util.Vector;

public class DayDevPac extends StockPac implements DeviationRst, Storable {
    private Vector<KeyYmd> keys=new Vector<>();
    private Vector<Integer>datas=new Vector<>();
    private String indexExchange;
    private int indexNum;
    private String[]columns=null;

    public DayDevPac(){

    }
    public DayDevPac(String se,int sn,String ise,int isn){
        super(se,sn);
        indexExchange=ise;
        indexNum=isn;
    }

    public void setIndex(String se, int sn){
        indexExchange=se;
        indexNum=sn;
    }

    public int getDataSize(){return keys.size();}
    public void newData(KeyYmd key,int data){
        keys.add(key);
        datas.add(data);
    }

    public String getIndexExchange(){
        return indexExchange;
    }

    public int getIndexNum() {
        return indexNum;
    }

    @Override
    public Packet addAll(Packet pac) throws Exception {
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("DayDevPac-addAll:type mismatch");
        keys.addAll(((DayDevPac)pac).keys);
        datas.addAll(((DayDevPac)pac).datas);
        return this;
    }

    // DeviationRst

    @Override
    public void deviationRstInitialize(String stockExchange, int stockNum, String indexExchange,int indexNum) {
        this.stockExchange=stockExchange;
        this.stockNum=stockNum;
        this.indexExchange=indexExchange;
        this.indexNum=indexNum;
    }

    @Override
    public void importDvrData(KeyData key, int deviation) {
        newData((KeyYmd)key,deviation);
    }

    // Storable



    @Override
    public String getTableName() {
        return  this.getStockExchange()+
                String.format("%06d", this.getStockNum())+
                "day";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                "deviation_between_"+indexExchange+indexNum
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int COMMENT '相对"+indexExchange+indexNum+"的涨幅偏离'"
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
}
