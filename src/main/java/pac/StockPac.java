package pac;

public abstract class StockPac extends Packages {
    protected String stockexchange=null;
    protected int stocknum=0;
    public StockPac(String se, int sn){
        stockexchange=se;
        stocknum=sn;
    }
    public StockPac(String sen){
        stockexchange=sen.substring(0,2);
        stocknum=Integer.parseInt(sen.substring(2,8));
    }
    public StockPac(){};
    public String getStockexchange(){ return stockexchange; }
    public int getStocknum(){ return stocknum; }
}
