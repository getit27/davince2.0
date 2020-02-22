package pac.minlgrow;

import pac.Packet;
import pac.StockPac;
import pac.inter.mcom.MinGrowthRst;
import pac.inter.Storable;
import pac.key.KeyData;

import java.util.Vector;

public class MinlGrowPac extends StockPac implements Storable, MinGrowthRst {
    Vector<MinlGrowData>datas=new Vector<>();

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

    @Override
    public Class<?> getPacType() {
        return super.getPacType();
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        datas.addAll(((MinlGrowPac)pac).datas);
        return this;
    }

    public void newData(MinlGrowData dgd){
        datas.add(dgd);
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
            Object[][] objss=new Object[datas.size()][3];
            int i=0;
            for(MinlGrowData mlgd:datas){
                objss[i]=new Object[]{
                        mlgd.getYmd(),
                        mlgd.getHm(),
                        mlgd.getGrowth()
                };
                i++;
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
        newData(new MinlGrowData((int)key.getKey(0),(int)key.getKey(1),growth));
    }
}
