package ctr.comctr;

import acc.UniAccess;
import com.Computer;
import pac.Packages;
import pac.inter.Accessable;
import pac.inter.AvaSrc;
import pac.inter.Storable;
import sto.Storer;

public class ComAva {
    public ComAva(Accessable acc, Computer com)throws Exception{
        new Storer().store((Storable) com.compute((Packages)new UniAccess(acc).getPackage()));
    }
}
