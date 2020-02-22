package pac.inter;

import pac.key.KeyData;

public interface AvaSrc {
    public String getStockExchange();
    public int getStockNum();
    public int getASDataSize();
    public int getASData(int index);
    public KeyData getKeyData(int index);
}
