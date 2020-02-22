import acc.UniAccess;
import com.GrowCom;
import ctr.instoctr.InSto;
import inp.DayIn;
import pac.dayclose.DayClosePac;
import pac.daygrow.DayGrowPac;
import pac.inter.Storable;
import set.DataBaseSet;
import sto.Storer;

import static ctr.Controller.loadMainSet;

public class DayGrowthTest {
    public static void main(String[]args){
        try{
            DataBaseSet.loadDataBaseSet();
            loadMainSet();

            DayClosePac dcp =new DayClosePac("sh",600000);
            new UniAccess(dcp).getPackage();
            GrowCom gc=new GrowCom(DayClosePac.class, DayGrowPac.class);
            new Storer().store((Storable) gc.compute(dcp));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
