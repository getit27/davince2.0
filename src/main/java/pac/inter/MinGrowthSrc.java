package pac.inter;

import pac.key.KeyData;

public interface MinGrowthSrc extends Mcomputable {
    public void MinGrowthSrcInitialize();// 将getSrc getRstType设为MinGrowthSrc
    public String getStockexchange();
    public int getStocknum();
    public int getMgsDataSize();
    public int getMgsData(int index);
    public KeyData getKeyData(int index);
    public boolean isLastMin(int index);
}
