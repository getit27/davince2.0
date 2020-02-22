package pac.inter.mcom;

import pac.key.KeyData;

public interface DeviationRst extends Mcomputed{
    public void deviationRstInitialize(String stockExchange,int stockNum,String indexExchange,int indexNum);
    public void importDvrData(KeyData key,int deviation);
}
