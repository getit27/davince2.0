package ctr.instoctr;

import inp.Inputer;
import pac.inter.Storable;
import sto.Storer;

public class InSto {
    public InSto(Inputer in)throws Exception{
        new Storer().store(in.getPackage());
    }
}
