package ctr.comctr;

import acc.UniAccess;
import com.AvaCom;
import com.Computer;
import pac.Packages;
import pac.inter.Accessable;
import pac.inter.Storable;
import sto.Storer;

public class ComAvaS {
    public ComAvaS(Accessable acc, AvaCom com, int[]sizes)throws Exception{
        // 获取数据包
        Packages p=(Packages)new UniAccess(acc).getPackage();
        for(int i:sizes){
            com.setSize(i);
            new Storer().store((Storable) com.compute(p));
        }
    }
}
