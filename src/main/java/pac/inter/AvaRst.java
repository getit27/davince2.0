package pac.inter;

import pac.key.KeyData;

public interface AvaRst {
    public void iniAvaRst(String exchangename,int stocknum,int size);
    public void importData(KeyData key, int ava);
}
