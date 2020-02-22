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

    @Override
    public int compare(KeyData key)throws Exception {
        if(key.getClass()!=KeyTime.class)
            throw new Exception("KeyData type mismatch");
        if(ymd>((KeyTime) key).ymd)
            return 1;
        else if(ymd<((KeyTime) key).ymd)
            return -1;
        else return Integer.compare(hm, ((KeyTime) key).hm);
    }
}
