package pac.minl;
public class MinlData {
    int ymd;
    int hm;
    int openprice;
    int maxprice;
    int minprice;
    int closeprice;
    float gmv;
    int volume;
    public MinlData(int date, int time,
                    int open, int max, int min,
                    int close, float g_m_v, int vol){
        ymd=date;
        hm=time;
        openprice=open;
        maxprice=max;
        minprice=min;
        closeprice=close;
        gmv=g_m_v;
        volume=vol;
    }

    public int getYmd() {
        return ymd;
    }
    public int getHm() {
        return hm;
    }
    public int getOpen(){ return openprice; }
    public int getMax(){ return maxprice; }
    public int getMin(){ return minprice; }
    public int getClose(){ return closeprice; }
    public float getGmv(){ return gmv; }
    public int getVol(){ return volume; }
}