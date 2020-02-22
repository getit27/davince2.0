package com;

import pac.inter.mcom.DeviationRst;
import pac.inter.mcom.DeviationSrc;
import pac.inter.mcom.Mcomputable;
import pac.inter.mcom.Mcomputed;

public class DeviationCom extends Computerp {

    private DeviationSrc dvcStock=null;
    private DeviationSrc dvcIndex=null;

    public DeviationCom() {
        sourceType=new Class<?>[]{
                DeviationSrc.class,
                DeviationSrc.class
        };
    }

    @Override
    public void initialize(Mcomputable[] source) throws Exception {
        super.initialize(source);
        dvcStock=(DeviationSrc) source[0];
        dvcIndex=(DeviationSrc) source[1];
    }

    @Override
    public Mcomputed[] compute() {
        DeviationRst dvr=null;
        try{
            dvr=(DeviationRst)createRstObject(dvcStock.getRstType()[0]);
            dvr.deviationRstInitialize(dvcStock.getStockExchange(),dvcStock.getStockNum(),
                    dvcIndex.getStockExchange(),dvcIndex.getStockNum());

            synchronize(dvcStock,dvcIndex);
            dvr.importDvrData(dvcStock.getKeyData(),
                    dvcStock.getDvsData()-dvcIndex.getDvsData());
            while(dvcStock.next()&&dvcIndex.next()){
                if(synchronize(dvcStock,dvcIndex))
                    dvr.importDvrData(dvcStock.getKeyData(),
                        dvcStock.getDvsData()-dvcIndex.getDvsData());
                else
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Mcomputed[]{dvr};
    }

    private boolean synchronize(DeviationSrc a,DeviationSrc b){
        while(a.compareKey(b)!=0&&!a.end()&&!b.end()){
            if(a.compareKey(b)>0)
                b.move(a.getKeyData());
            else
                a.move(b.getKeyData());
        }
        return !a.end() && !b.end();
    }
}
