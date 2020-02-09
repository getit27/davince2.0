package pac.day;
import pac.Packages;
import pac.inter.Storable;

import java.util.Vector;
public class DayPac extends Packages implements Storable {
    String stockexchange;
    int stocknum;
    Vector<DayData>data=new Vector<DayData>();
    public DayPac(String se,int sn){
        stockexchange=se;
        stocknum=sn;
    }
    public void newDay(DayData day){
        data.add(day);
    };
    public Vector<DayData> getDay(){
        return data;
    }
    public String getStockexchange(){return stockexchange;}
    public int getStocknum(){return stocknum;}

    // Storable


    @Override
    public Class<?> getPacType() {
        return DayPac.class;
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
                "openprice",
                "maxprice" ,
                "minprice" ,
                "closeprice" ,
                "gmv" ,
                "volume"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
                "int COMMENT '开盘价*100'",
                "int COMMENT '最高价*100'",
                "int COMMENT '最低价*100'",
                "int COMMENT '收盘价*100'",
                "float COMMENT '成交额'",
                "int COMMENT '成交量'"
        };
    }

    @Override
    public String[] getPrimaryKeys() {
        return new String[]{"ymd"};
    }

    @Override
    public int[] getNpLocation() {
        return new int[]{1,2,3,4,5,6};
    }

    @Override
    public int[] getPrimaryLocation() {
        return new int[]{0};
    }

    @Override
    public Class<?>[] getType() {
        return new Class[]{int.class,int.class,int.class,
                int.class,int.class,float.class,int.class};
    }

    Object[][] storedata=null;
    @Override
    public Object[][] getStoreData() {
        if(storedata==null) {
            Vector<DayData> days = getDay();
            Object[][] objss = new Object[days.size()][7];
            int i = 0;
            for (DayData day : days) {
                Object[] objs = new Object[]{day.getYmd(), day.getOpen(), day.getMax(),
                        day.getMin(), day.getClose(), day.getGmv(), day.getVol()};
                objss[i] = objs;
                i++;
            }
            storedata=objss;
        }
        return storedata;
    }
}
