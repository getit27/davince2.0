package pac.inter;

import pac.key.KeyData;

public interface AvaSrc {
    public String getStockexchange();
    public int getStocknum();
    public int getDataSize();
    public int getComData(int index);
    public KeyData getKeyData(int index);
}
