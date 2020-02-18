package com;

import pac.Packages;
import pac.inter.GrowRst;
import pac.inter.GrowSrc;
import tol.RoundDivision;


public class GrowCom implements Computer{

    int size=1;
    Class<?>intype =null;
    Class<?>outtype=null;
    public GrowCom(Class<?>in, Class<?>out)throws Exception{
        if(GrowSrc.class.isAssignableFrom(in)&& GrowRst.class.isAssignableFrom(out)) {
            intype = in;
            outtype = out;
        }else{
            throw new Exception("in and out must be subclass of AvaSrc and AvaRst");
        }
    }

    public GrowCom(int s,Class<?>in, Class<?>out)throws Exception{
        size=s;
        if(GrowSrc.class.isAssignableFrom(in)&& GrowRst.class.isAssignableFrom(out)) {
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
        GrowSrc gsc=(GrowSrc)p;
        GrowRst grt=null;
        try{
            // 创造输出对象
            grt =(GrowRst)outtype.getDeclaredConstructor().newInstance();
            grt.iniGrowRst(gsc.getStockexchange(),gsc.getStocknum(),size);

            if(size>gsc.getGSDataSize())
                return (Packages)grt;

            for(int i = size; i<gsc.getGSDataSize(); i++){
                grt.importDataGR(gsc.getKeyData(i),(int)(
                        RoundDivision.roundDiv(10000*(long)gsc.getGSData(i),gsc.getGSData(i-size))-10000
                        ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return (Packages) grt;
    }
}
