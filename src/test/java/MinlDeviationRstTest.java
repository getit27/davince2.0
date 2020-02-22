import acc.UniAccess;
import com.DeviationCom;
import pac.daygrow.DayGrowPac;
import pac.inter.Storable;
import pac.inter.mcom.Mcomputable;
import pac.minlgrow.MinlGrowPac;
import set.DataBaseSet;
import sto.Storer;

import static ctr.Controller.loadMainSet;

public class MinlDeviationRstTest {
    public static void main(String[]args){
        try{
            DataBaseSet.loadDataBaseSet();
            loadMainSet();

            MinlGrowPac indexMap=new MinlGrowPac("sh",1);
            MinlGrowPac stockMap=new MinlGrowPac("sh",600000);

            new UniAccess(indexMap).getPackage();
            new UniAccess(stockMap).getPackage();

            DeviationCom dvc=new DeviationCom();

            indexMap.deviationSrcInitialize();
            stockMap.deviationSrcInitialize();

            dvc.initialize(new Mcomputable[]{stockMap,indexMap});
            new Storer().store((Storable) dvc.compute()[0]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
