package pac.minlgrow;

public class MinlGrowData {
    int ymd;
    int hm;
    int growth;
    public MinlGrowData(int date, int time, int grow){
        ymd=date;
        hm=time;
        growth=grow;
    }
    public int getYmd(){ return ymd; }
    public int getHm(){ return hm; }
    public int getGrowth(){ return growth; }
}
