package pac.dayava;

import pac.*;
import pac.dayva.DayVAPac;
import pac.inter.AvaRst;
import pac.inter.Storable;
import pac.key.KeyData;

import java.util.Vector;

public class DayAvaPac extends StockPac implements Storable, AvaRst {
    Vector<DayAvaData>data= new Vector<DayAvaData>();
    int size;
    public DayAvaPac(String se, int sn,int si){
        stockexchange=se;
        stocknum=sn;
        size=si;
    }
    public DayAvaPac(){};
    public int getAvaSize(){ return size; }
    public void newDay(DayAvaData dayclose){
        data.add(dayclose);
    }
    public Vector<DayAvaData> getDayAva(){ return data; }

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
            Vector<DayAvaData> days = getDayAva();
            Object[][] objss = new Object[days.size()][2];
            int i=0;
            for(DayAvaData day:days){
                objss[i] = new Object[]{
                        day.getYmd(),day.getAverage()
                };
                i++;
            }
            storedata=objss;
        }
        return storedata;
    }

    // AvaRst


    @Override
    public void iniAvaRst(String exchangename, int stocknum, int size) {
        this.stockexchange=exchangename;
        this.stocknum=stocknum;
        this.size=size;
    }

    @Override
    public void importData(KeyData key, int ava) {
        newDay(new DayAvaData((int)key.getKey(0),ava));
    }
}
