package ctr.comctr;

import acc.LimAccess;
import acc.UniAccess;
import com.DeviationCom;
import com.GrowCom;
import com.MinGrowthCom;
import pac.dayclose.DayClosePac;
import pac.daygrow.DayGrowPac;
import pac.inter.Storable;
import pac.inter.mcom.Mcomputable;
import pac.minlclose.MinlClosePac;
import pac.minlgrow.MinlGrowPac;
import pac.table.TableListPac;
import sto.Storer;

public class BatchComDev {
    public BatchComDev(int type)throws Exception {
        TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
        if(type==0||type==2){
            // 读取参照指数
            DayGrowPac indexDap=new DayGrowPac("sh",1);
            new UniAccess(indexDap).getPackage();
            indexDap.deviationSrcInitialize();
            // 创建计算器类
            DeviationCom dvc=new DeviationCom();

            for(int i=0;i<tablelist.getDataLength();i++){
                String tablename=tablelist.getData(i);
                String stockname=tablename.substring(0,8);
                if(tablename.substring(8).equals("day")){
                    System.out.println(tablename);

                    DayGrowPac stockDap=new DayGrowPac(stockname);
                    new UniAccess(stockDap).getPackage();
                    stockDap.deviationSrcInitialize();
                    dvc.initialize(new Mcomputable[]{stockDap,indexDap});
                    new Storer().store((Storable) dvc.compute()[0]);
                }
            }
        }
        if(type==1||type==2) {
            // 读取参照指数
            MinlGrowPac indexMap=new MinlGrowPac("sh",1);
            new UniAccess(indexMap).getPackage();
            indexMap.deviationSrcInitialize();
            // 创建计算器类
            DeviationCom dvc=new DeviationCom();

            for (int i = 0; i < tablelist.getDataLength(); i++) {
                String tablename = tablelist.getData(i);
                String stockname = tablename.substring(0, 8);
                if (tablename.substring(8).equals("minl")) {
                    System.out.println(tablename);
                    // 读取主体数据
                    MinlGrowPac stockMap=new MinlGrowPac(stockname);
                    new UniAccess(stockMap).getPackage();
                    stockMap.deviationSrcInitialize();
                    // 初始化计算器类
                    dvc.initialize(new Mcomputable[]{stockMap,indexMap});
                    // 计算存储
                    new Storer().store((Storable) dvc.compute()[0]);
                }
            }
        }
    }
}
