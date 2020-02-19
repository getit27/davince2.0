package ctr.comctr;

import acc.UniAccess;
import com.Computer;
import pac.Packet;
import pac.inter.Accessable;
import pac.inter.Storable;
import sto.Storer;

public class ComAva {
    public ComAva(Accessable acc, Computer com)throws Exception{
        new Storer().store((Storable) com.compute((Packet)new UniAccess(acc).getPackage()));
    }
}
