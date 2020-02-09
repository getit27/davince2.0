package pac.minlava;

public class MinlAvaData {
    int ymd;
    int hm;
    int average;
    public MinlAvaData(int data,int time, int ava){
        ymd=data;
        hm=time;
        average=ava;
    }
    public int getYmd(){ return ymd; }
    public int getHm(){ return hm; }
    public int getAverage(){ return average; }
}
