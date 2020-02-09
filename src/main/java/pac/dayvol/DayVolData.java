package pac.dayvol;

public class DayVolData {
    int ymd;
    int volume;
    public DayVolData(int date,int vol){
        ymd=date;
        volume=vol;
    }
    public int getYmd(){ return ymd; }
    public int getVolume() { return volume; }
}
