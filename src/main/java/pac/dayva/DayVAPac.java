package pac.dayva;

import pac.Packages;
import pac.StockPac;
import pac.inter.AvaRst;
import pac.inter.Storable;
import pac.key.KeyData;

import java.util.Vector;

public class DayVAPac extends StockPac implements AvaRst, Storable {

    Vector<DayVAData> data=new Vector<>();

    void newData(DayVAData dvad){
        data.add(dvad);
    }

    public DayVAPac(String se, int sn) {
        super(se, sn);
        pactype =DayVAPac.class;
    }

    public DayVAPac() {
        super();
        pactype =DayVAPac.class;
    }

    @Override
    public Packages addAll(Packages pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((DayVAPac)pac).data);
        return this;
    }

    // AvaRst

    int size;

    @Override
    public void iniAvaRst(String exchangename, int stocknum, int size) {
        this.stockexchange=exchangename;
        this.stocknum=stocknum;
        this.size=size;
    }

    @Override
    public void importDataAR(KeyData key, int ava) {
        newData(new DayVAData((int)key.getKey(0),ava));
    }

    // Storable


    @Override
    public Class<?> getPacType() {
        return DayVAPac.class;
    }

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
                size+"vol"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int COMMENT '"+size+"日成交量均值'"
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
            Object[][] objss=new Object[data.size()][2];
            int i=0;
            for(DayVAData dvad:data){
                objss[i]=new Object[]{
                        dvad.getYmd(),
                        dvad.getVolava()
                };
                i++;
            }
            stodata=objss;
        }
        return stodata;
    }
}
