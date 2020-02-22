package pac;

import pac.key.KeyData;
import pac.key.KeyYmd;

import java.util.Vector;

public abstract class StockPac extends Packet {
    protected String stockExchange =null;
    protected int stockNum =0;

    public StockPac(String se, int sn){
        stockExchange =se;
        stockNum =sn;
    }
    public StockPac(String sen){
        stockExchange =sen.substring(0,2);
        stockNum =Integer.parseInt(sen.substring(2,8));
    }

    public StockPac(){};
    public String getStockExchange(){ return stockExchange; }
    public int getStockNum(){ return stockNum; }
}
