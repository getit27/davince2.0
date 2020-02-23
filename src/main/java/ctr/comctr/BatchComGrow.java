package ctr.comctr;

import acc.LimAccess;
import acc.UniAccess;
import com.AvaCom;
import com.GrowCom;
import com.MinGrowthCom;
import pac.dayava.DayAvaPac;
import pac.dayclose.DayClosePac;
import pac.daygrow.DayGrowPac;
import pac.dayva.DayVAPac;
import pac.dayvol.DayVolPac;
import pac.inter.Storable;
import pac.inter.mcom.Mcomputable;
import pac.minlava.MinlAvaPac;
import pac.minlclose.MinlClosePac;
import pac.table.TableListPac;
import sto.Storer;

public class BatchComGrow {
    public BatchComGrow(int type)throws Exception {
        TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
        if(type==0||type==2){
            for(int i=0;i<tablelist.getDataLength();i++){
                String tablename=tablelist.getData(i);
                String stockname=tablename.substring(0,8);
                if(tablename.substring(8).equals("day")){
                    System.out.println(tablename);

                    DayClosePac dcp =new DayClosePac(stockname);
                    new UniAccess(dcp).getPackage();
                    GrowCom gc=new GrowCom(DayClosePac.class, DayGrowPac.class);
                    new Storer().store((Storable) gc.compute(dcp));
                }
            }
        }
        if(type==1||type==2) {
            for (int i = 0; i < tablelist.getDataLength(); i++) {
                String tablename = tablelist.getData(i);
                String stockname = tablename.substring(0, 8);
                if (tablename.substring(8).equals("minl")) {
                    System.out.println(tablename);

                    MinlClosePac mcp = new MinlClosePac(stockname);
                    new UniAccess(mcp).getPackage();
                    MinGrowthCom mgc = new MinGrowthCom();
                    mcp.minGrowthSrcInitialize();
                    mgc.initialize(new Mcomputable[]{mcp});
                    new Storer().store((Storable) mgc.compute()[0]);
                }
            }
        }
    }
}
