import acc.UniAccess;
import com.DeviationCom;
import pac.dayava.DayAvaPac;
import pac.dayclose.DayClosePac;
import pac.daygrow.DayGrowPac;
import pac.inter.Storable;
import pac.inter.mcom.Mcomputable;
import set.DataBaseSet;
import sto.Storer;

import static ctr.Controller.loadMainSet;

public class DayDeviationRstTest {
    public static void main(String[]args){
        try{
            DataBaseSet.loadDataBaseSet();
            loadMainSet();

            DayGrowPac indexDap=new DayGrowPac("sh",1);
            DayGrowPac stockDap=new DayGrowPac("sh",600000);

            new UniAccess(indexDap).getPackage();
            new UniAccess(stockDap).getPackage();

            DeviationCom dvc=new DeviationCom();

            indexDap.deviationSrcInitialize();
            stockDap.deviationSrcInitialize();

            dvc.initialize(new Mcomputable[]{stockDap,indexDap});
            new Storer().store((Storable) dvc.compute()[0]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
