package pac.inter.mcom;

import pac.key.KeyData;

public interface DeviationSrc extends Mcomputable {
    public void deviationSrcInitialize();
    public String getStockExchange();
    public int getStockNum();
    public int getDvsDataSize();
    public int getDvsData(int index);
    public KeyData getKeyData(int index);

    public boolean next();
    public boolean end();
    public boolean move(KeyData key);
    public boolean move(int index);
    public int getDvsData();
    public KeyData getKeyData();
    public int compareKey(DeviationSrc dvs);
}
