package pac.inter.mcom;

import pac.key.KeyData;

public interface MinGrowthSrc extends Mcomputable {
    public void minGrowthSrcInitialize();// 将getSrc getRstType设为MinGrowthSrc
    public String getStockExchange();
    public int getStockNum();
    public int getMgsDataSize();
    public int getMgsData(int index);
    public KeyData getKeyData(int index);
    public boolean isLastMin(int index);
}
