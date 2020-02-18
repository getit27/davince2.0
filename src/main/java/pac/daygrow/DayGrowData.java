package pac.daygrow;

public class DayGrowData {
    int ymd;
    int growth;
    public DayGrowData(int date, int grow){
        ymd=date;
        growth=grow;
    }
    public int getYmd(){ return ymd; }
    public int getGrowth(){  return growth; }
}
