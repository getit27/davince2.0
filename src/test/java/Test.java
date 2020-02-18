import com.AvaCom;
import com.GrowCom;
import ctr.comctr.ComAva;
import ctr.comctr.ComGrow;
import ctr.instoctr.InSto;
import inp.DayIn;
import inp.MinlIn;
import pac.dayclose.DayClosePac;
import pac.daygrow.DayGrowPac;
import pac.minlava.MinlAvaPac;
import pac.minlclose.MinlClosePac;
import pac.minlgrow.MinlGrowPac;
import set.DataBaseSet;

import static ctr.Controller.loadMainSet;

public class Test {
    public static void main(String[]args) {
        try {
            DataBaseSet.loadDataBaseSet();
            loadMainSet();
            //new InSto(new DayIn("F:\\gkt\\zyyht\\vipdoc\\sh\\lday\\sh000001.day"));
            //new InSto(new MinlIn("F:\\gkt\\zyyht\\vipdoc\\sh\\minline\\sh000001.lc1"));
            //new InSto(new MinlIn("I:\\minline\\sh000001.lc1"));
            //new ComAva(new MinlClosePac("sh",1),new AvaCom(5 , MinlClosePac.class, MinlAvaPac.class));
            //new ComGrow(new DayClosePac("sh",1),new GrowCom(DayClosePac.class, DayGrowPac.class));
            new ComGrow(new MinlClosePac("sh",1),new GrowCom(5,MinlClosePac.class, MinlGrowPac.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
