package pac.key;

public class KeyYmd implements KeyData {
    int ymd;
    public KeyYmd(int date){
        ymd=date;
    }

    @Override
    public int getKnum() {
        return 1;
    }

    @Override
    public Object getKey(int index) {
        return ymd;
    }
}
