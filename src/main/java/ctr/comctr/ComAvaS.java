package ctr.comctr;

import acc.UniAccess;
import com.AvaCom;
import pac.Packet;
import pac.inter.Accessible;
import pac.inter.Storable;
import sto.Storer;

public class ComAvaS {
    public ComAvaS(Accessible acc, AvaCom com, int[]sizes)throws Exception{
        // 获取数据包
        Packet p=(Packet)new UniAccess(acc).getPackage();
        for(int i:sizes){
            com.setSize(i);
            new Storer().store((Storable) com.compute(p));
        }
    }
}
