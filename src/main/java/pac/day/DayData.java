package pac.day;
public class DayData{
    int ymd;
    int openprice;
    int maxprice;
    int minprice;
    int closeprice;
    float gmv;
    int volume;
    public DayData(int date,int open,int max,int min,
                   int close,float g_m_v,int vol){
        ymd=date;
        openprice=open;
        maxprice=max;
        minprice=min;
        closeprice=close;
        gmv=g_m_v;
        volume=vol;
    }
    public int getYmd(){ return ymd; }
    public int getOpen(){ return openprice; }
    public int getMax(){ return maxprice; }
    public int getMin(){ return minprice; }
    public int getClose(){ return closeprice; }
    public float getGmv(){ return gmv; }
    public int getVol(){ return volume; }
}