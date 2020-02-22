package ctr.comctr;

import acc.UniAccess;
import com.Computer;
import pac.Packet;
import pac.inter.Accessible;
import pac.inter.Storable;
import sto.Storer;

public class ComGrow {
    public ComGrow(Accessible acc, Computer com)throws Exception{
        new Storer().store((Storable) com.compute((Packet)new UniAccess(acc).getPackage()));
    }
}
