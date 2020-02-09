package pac.key;

public class KeyTime implements KeyData {
    int ymd;
    int hm;
    public KeyTime(int data,int time){
        ymd=data;
        hm=time;
    }

    @Override
    public int getKnum() {
        return 2;
    }

    @Override
    public Object getKey(int index) {
        return new int[]{ymd,hm}[index];
    }
}
