package pac.inter;

import pac.key.KeyData;

public interface AvaSrc {
    public String getStockexchange();
    public int getStocknum();
    public int getASDataSize();
    public int getASData(int index);
    public KeyData getKeyData(int index);
}
