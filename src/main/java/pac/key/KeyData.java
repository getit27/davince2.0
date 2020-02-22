package pac.key;

public interface KeyData {
    public int getKnum();
    public Object getKey(int index);
    public int compare(KeyData key)throws Exception;
}
