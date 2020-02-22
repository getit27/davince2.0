package pac.inter.mcom;

import pac.key.KeyData;

public interface MinGrowthRst extends Mcomputed {
    public void minGrowthRstInitialize(String stockExchange, int stockNum);
    public void importMgrData(KeyData key, int growth);
}
