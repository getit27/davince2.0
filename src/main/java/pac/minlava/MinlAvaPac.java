package pac.minlava;

import pac.Packet;
import pac.StockPac;
import pac.inter.AvaRst;
import pac.inter.Storable;
import pac.key.KeyData;

import java.util.Vector;

public class MinlAvaPac extends StockPac implements AvaRst, Storable {
    Vector<MinlAvaData>data= new Vector<MinlAvaData>();
    int size;
    public MinlAvaPac(String se, int sn, int si){
        stockexchange=se;
        stocknum=sn;
        size=si;
    }
    public MinlAvaPac(){};
    public int getSize(){ return size; }
    public void newData(MinlAvaData minlava){
        data.add(minlava);
    }
    public Vector<MinlAvaData> getDayAva(){ return data; }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((MinlAvaPac)pac).data);
        return this;
    }

    // AvaRst

    @Override
    public void iniAvaRst(String exchangename, int stocknum, int size) {
        this.stockexchange=exchangename;
        this.stocknum=stocknum;
        this.size=size;
    }

    @Override
    public void importDataAR(KeyData key, int ava) {
        newData(new MinlAvaData(
                (int)key.getKey(0),
                (int)key.getKey(1),
                ava
        ));
    }

    // Storable


    @Override
    public Class<?> getPacType() {
        return MinlAvaPac.class;
    }

    @Override
    public String getTableName() {
        return getStockexchange()+
                String.format("%06d",getStocknum())+
                "minl";
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "ymd",
                "hm",
                size+"ave"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int NOT NULL",
                "int COMMENT '"+size+"分钟均值'"
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
            Object[][] objss=new Object[data.size()][3];
            int i=0;
            for(MinlAvaData mlad:data){
                objss[i]=new Object[]{
                        mlad.getYmd(),
                        mlad.getHm(),
                        mlad.getAverage()
                };
                i++;
            }
            stodata=objss;
        }
        return stodata;
    }
}
