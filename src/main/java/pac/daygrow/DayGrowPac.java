package pac.daygrow;

import pac.Packages;
import pac.StockPac;
import pac.inter.GrowRst;
import pac.inter.Storable;
import pac.key.KeyData;

import java.util.Vector;

public class DayGrowPac extends StockPac implements GrowRst, Storable {
    Vector<DayGrowData>datas=new Vector<>();
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

    @Override
    public Packages addAll(Packages pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        datas.addAll(((DayGrowPac)pac).datas);
        return this;
    }

    @Override
    public Class<?> getPacType() {
        return super.getPacType();
    }

    public void newData(DayGrowData dgd){
        datas.add(dgd);
    }

    // GrowRst

    @Override
    public void iniGrowRst(String exchangename, int stocknum,int size) {
        this.stockexchange=exchangename;
        this.stocknum=stocknum;
        this.size=size;
    }

    @Override
    public void importDataGR(KeyData key, int grows) {
        newData(new DayGrowData((int)key.getKey(0),grows));
    }

    // Storable


    @Override
    public String getTableName() {
        return getStockexchange()+
                String.format("%06d",getStocknum())+
                "day";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                size+"growth_rate"
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
            Object[][] objss=new Object[datas.size()][2];
            int i=0;
            for(DayGrowData dvad:datas){
                objss[i]=new Object[]{
                        dvad.getYmd(),
                        dvad.getGrowth()
                };
                i++;
            }
            stodata=objss;
        }
        return stodata;
    }
}
