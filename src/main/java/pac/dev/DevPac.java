package pac.dev;

import pac.Packet;
import pac.StockPac;
import pac.inter.Storable;
import pac.inter.mcom.DeviationRst;
import pac.key.KeyData;
import pac.key.KeyYmd;

import java.util.Vector;

abstract public class DevPac<K extends KeyData> extends StockPac implements DeviationRst {
    protected Vector<K> keys=new Vector<>();
    protected Vector<Integer> datas=new Vector<>();
    protected String indexExchange;
    protected int indexNum;
    protected String[]columns=null;

    public DevPac(){

    }
    public DevPac(String se,int sn,String ise,int isn){
        super(se,sn);
        indexExchange=ise;
        indexNum=isn;
    }

    public void setIndex(String se, int sn){
        indexExchange=se;
        indexNum=sn;
    }

    public int getDataSize(){
        return keys.size();
    };
    public void newData(K key,int data){
        keys.add(key);
        datas.add(data);
    }

    public String getIndexExchange(){
        return indexExchange;
    }
    public int getIndexNum() {
        return indexNum;
    }


    // DeviationRst

    @Override
    public void deviationRstInitialize(String stockExchange, int stockNum, String indexExchange,int indexNum) {
        this.stockExchange=stockExchange;
        this.stockNum=stockNum;
        this.indexExchange=indexExchange;
        this.indexNum=indexNum;
    }

}
