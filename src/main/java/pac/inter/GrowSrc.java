package pac.inter;

import pac.key.KeyData;

public interface GrowSrc {
    public String getStockExchange();
    public int getStockNum();
    public int getGSDataSize();
    public int getGSData(int index);
    public KeyData getKeyData(int index);
}
