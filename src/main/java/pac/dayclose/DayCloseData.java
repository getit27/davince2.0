package pac.dayclose;

import pac.key.KeyYmd;

public class DayCloseData {
    int ymd;
    int closeprice;
    public DayCloseData(int date, int close){
        ymd=date;
        closeprice=close;
    }
    public int getYmd(){ return ymd; }
    public KeyYmd getKey(){
        return new KeyYmd(ymd);
    }
    public int getCloseprice(){ return closeprice; }
}
