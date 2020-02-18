package com;

import pac.inter.AvaRst;
import pac.inter.AvaSrc;
import pac.Packages;

import java.lang.*;

public class AvaCom implements Computer {

    int size=0;
    Class<?>intype=null;
    Class<?>outtype=null;
    public AvaCom(int s,Class<?> in,Class<?> out)throws Exception{
        size=s;
        if(AvaSrc.class.isAssignableFrom(in)&&AvaRst.class.isAssignableFrom(out)) {
            intype = in;
            outtype = out;
        }else{
            throw new Exception("in and out must be subclass of AvaSrc and AvaRst");
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Class<?> getInType() {
        return intype;
    }

    @Override
    public Class<?> getOutType() {
        return outtype;
    }

    @Override
    public Packages compute(Packages p) {
        AvaSrc asc= (AvaSrc)p;
        AvaRst art=null;
        try {
            // 创建输出对象
            art = (AvaRst)outtype.getDeclaredConstructor().newInstance();
            art.iniAvaRst(asc.getStockexchange(), asc.getStocknum(), size);
            //Vector<MinlCloseData> mlcd=mlcp.getDayClose();
            if(size>asc.getASDataSize())
                return (Packages)art;
            long avemsize=0;
            for(int i=0;i<size;i++){
                avemsize+=asc.getASData(i);
            }
            art.importDataAR(asc.getKeyData(size-1),(int)(avemsize/(long)size));
            for(int i = size; i<asc.getASDataSize(); i++){
                avemsize-=asc.getASData(i-size);
                avemsize+=asc.getASData(i);
                art.importDataAR(asc.getKeyData(i),(int)(avemsize/(long)size));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return (Packages)art;
    }
}
