package pac.dev;

import pac.Packet;
import pac.StockPac;
import pac.inter.Storable;
import pac.key.KeyData;
import pac.key.KeyTime;
import pac.key.KeyYmd;
import pac.minlava.MinlAvaData;

import java.util.Vector;

public class MinlDevPac extends DevPac<KeyTime> implements Storable {

    // Packet

    @Override
    public Packet addAll(Packet pac) throws Exception {
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("DayDevPac-addAll:type mismatch");
        keys.addAll(((MinlDevPac)pac).keys);
        datas.addAll(((MinlDevPac)pac).datas);
        return this;
    }

    // DeviationRst

    @Override
    public void importDvrData(KeyData key, int deviation) {
        newData((KeyTime) key,deviation);
    }

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
                "deviation_between_"+indexExchange+indexNum
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int NOT NULL",
                "int COMMENT '相对"+indexExchange+indexNum+"的涨幅偏离'"
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

    Object[][] storedata=null;
    @Override
    public Object[][] getStoreData() {
        if(storedata==null){
            Object[][] objss = new Object[getDataSize()][3];
            for(int i=0;i<getDataSize();i++){
                objss[i] = new Object[]{
                        keys.get(i).getKey(0),
                        keys.get(i).getKey(1),
                        datas.get(i)
                };
            }
            storedata=objss;
        }
        return storedata;
    }
}
