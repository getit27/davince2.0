package com;

import pac.inter.AvaSrc;
import pac.inter.mcom.Mcomputable;
import pac.inter.mcom.Mcomputed;

abstract public class Computerp {
    protected Class<?>[]sourceType;
    protected Class<?>thisType=this.getClass();
    public Computerp(){
        sourceType=null;
        thisType=this.getClass();
    }
    public void initialize(Mcomputable[] source) throws Exception{
        int sourceLength=sourceType.length;
        if(source.length!=sourceLength)
            throw new Exception(thisType.getName()+" receives "+sourceLength+" packet");
        for(int i=0;i<sourceLength;i++){
            if(!Mcomputable.class.isAssignableFrom(sourceType[i])){
                throw new Exception(sourceType[i].getName()+" of sourceType is not MComputable");
            }
            if(source[i].getSrc()!=sourceType[i])
                throw new Exception(thisType.getName()+" receives "+sourceType[i].getName()+" as "+i+" packet");
        }
    }
    abstract public Mcomputed[]compute();
    protected Object createRstObject(Class<?> type)throws Exception{
        Object ro=type.getDeclaredConstructor().newInstance();
        return ro;
    }
}
