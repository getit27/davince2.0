package pac.minlclose;

public class MinlCloseData {
    int ymd;
    int hm;
    int closeprice;
    public MinlCloseData(int date,int time,int close){
        ymd=date;
        hm=time;
        closeprice=close;
    }
    public int getYmd(){ return ymd; }
    public int getHm(){ return hm; }
    public int getCloseprice(){ return closeprice; }
}
