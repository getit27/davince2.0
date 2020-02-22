import acc.UniAccess;
import com.MinGrowthCom;
import pac.inter.mcom.Mcomputable;
import pac.inter.Storable;
import pac.minlclose.MinlClosePac;
import set.DataBaseSet;
import sto.Storer;

import static ctr.Controller.loadMainSet;

public class MinGrowthTest {
    public static void main(String[]args) {
        try {
            DataBaseSet.loadDataBaseSet();
            loadMainSet();
            //new InSto(new DayIn("F:\\gkt\\zyyht\\vipdoc\\sh\\lday\\sh000001.day"));
            //new InSto(new MinlIn("F:\\gkt\\zyyht\\vipdoc\\sh\\minline\\sh000001.lc1"));
            //new InSto(new MinlIn("I:\\minline\\sh000001.lc1"));
            //new ComAva(new MinlClosePac("sh",1),new AvaCom(5 , MinlClosePac.class, MinlAvaPac.class));
            //new ComGrow(new DayClosePac("sh",1),new GrowCom(DayClosePac.class, DayGrowPac.class));
            MinlClosePac mcp = new MinlClosePac("sh",600000);
            new UniAccess(mcp).getPackage();
            MinGrowthCom mgc = new MinGrowthCom();
            mcp.minGrowthSrcInitialize();
            mgc.initialize(new Mcomputable[]{mcp});
            new Storer().store((Storable)mgc.compute()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
