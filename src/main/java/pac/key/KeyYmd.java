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

    @Override
    public int compare(KeyData key) throws Exception {
        if(key.getClass()!=KeyYmd.class)
            throw new Exception("KeyData type mismatch");
        return Integer.compare(ymd, ((KeyYmd) key).ymd);
    }
}
