package com;

import pac.inter.Mcomputable;
import pac.inter.Mcomputed;
import pac.inter.MinGrowthRst;
import pac.inter.MinGrowthSrc;
import tol.RoundDivision;

public class MinGrowthCom extends Computerp {

    private MinGrowthSrc mgs=null;

    @Override
    public void initialize(Mcomputable[] source) throws Exception{
        if(source.length!=1)
            throw new Exception("MinGrowthCom receive 1 package");
        if(source[0].getSrc()!= MinGrowthSrc.class)
            throw new Exception("MinGrowthCom receive MinGrowthSrc");
        mgs=(MinGrowthSrc) source[0];
    }

    @Override
    public Mcomputed[] compute() {
        MinGrowthRst mgr=null;
        try {
            //创建输出对象
            mgr=(MinGrowthRst)createRstObject(mgs.getRstType()[0]);
            mgr.MinGrowthRstInitialize(mgs.getStockexchange(),mgs.getStocknum());

            int lastdayprice=2147483647;

            for(int i=0;i<mgs.getMgsDataSize();i++){
                mgr.importMgrData(mgs.getKeyData(i),(int)(
+                        RoundDivision.roundDiv(10000*(long)mgs.getMgsData(i),lastdayprice)-10000
                        )
                );
                if(mgs.isLastMin(i))
                    lastdayprice=mgs.getMgsData(i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Mcomputed[]{mgr};
    }
}
