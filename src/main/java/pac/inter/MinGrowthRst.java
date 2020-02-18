package pac.inter;

import pac.key.KeyData;

public interface MinGrowthRst extends Mcomputed {
    public void MinGrowthRstInitialize(String stockexchange,int stocknum);
    public void importMgrData(KeyData key, int growth);
}
