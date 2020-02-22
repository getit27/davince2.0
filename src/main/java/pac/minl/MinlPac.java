// 分钟数据包，但是主键更少
package pac.minl;
import pac.Packet;
import pac.StockPac;
import pac.inter.Storable;

import java.util.Vector;

public class MinlPac extends StockPac implements Storable {
    Vector<MinlData>data=new Vector<MinlData>();
    public MinlPac(String se, int sn){
        stockExchange =se;
        stockNum =sn;
    }
    public MinlPac(){}
    public void newMinl(MinlData min){
        data.add(min);
    };
    public Vector<MinlData> getMinl(){
        return data;
    }

    @Override
    public Packet addAll(Packet pac) throws  Exception{
        if(pac.getPacType()!=this.getPacType())
            throw new Exception("type not matching!");
        data.addAll(((MinlPac)pac).data);
        return this;
    }

    // Storable


    @Override
    public Class<?> getPacType() {
        return MinlPac.class;
    }

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
                "openprice",
                "maxprice",
                "minprice",
                "closeprice",
                "gmv",
                "volume"
        };
    }

    @Override
    public String[] getComments() {
        return new String[]{
                "int NOT NULL",
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
        return new String[]{
                "ymd",
                "hm"
        };
    }

    @Override
    public int[] getNpLocation() {
        return new int[]{2,3,4,5,6,7};
    }

    @Override
    public int[] getPrimaryLocation() {
        return new int[]{0,1};
    }

    @Override
    public Class<?>[] getType() {
        return new Class[]{
                int.class,int.class,
                int.class,int.class,int.class,int.class,float.class,int.class
        };
    }

    Object[][] storedata=null;
    @Override
    public Object[][] getStoreData() {
        if(storedata==null){
            Vector<MinlData> mins=getMinl();
            Object[][] objss=new Object[mins.size()][11];
            int i=0;
            for(MinlData minl:mins){
                objss[i]=new Object[]{
                        minl.getYmd(),
                        minl.getHm(),
                        minl.getOpen(),
                        minl.getMax(),
                        minl.getMin(),
                        minl.getClose(),
                        minl.getGmv(),
                        minl.getVol()
                };
                i++;
            }
            storedata=objss;
        }
        return storedata;
    }
}
