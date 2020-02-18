package pac.inter;

import pac.key.KeyData;

public interface GrowRst {
    public void iniGrowRst(String exchangename,int stocknum,int size);
    public void importDataGR(KeyData key, int grows);
}
