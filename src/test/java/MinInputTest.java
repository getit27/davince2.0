import ctr.instoctr.InSto;
import inp.Inputer;
import inp.MinlIn;
import set.DataBaseSet;

import static ctr.Controller.loadMainSet;

public class MinInputTest {
    public static void main(String[]args){
        try{
            DataBaseSet.loadDataBaseSet();
            loadMainSet();

            new InSto(new MinlIn("F:\\gkt\\zyyht\\vipdoc\\sh\\minline\\sh600000.lc1"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
