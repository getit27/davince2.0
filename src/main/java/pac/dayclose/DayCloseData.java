package pac.dayclose;

public class DayCloseData {
    int ymd;
    int closeprice;
    public DayCloseData(int date, int close){
        ymd=date;
        closeprice=close;
    }
    public int getYmd(){ return ymd; }
    public int getCloseprice(){ return closeprice; }
}
