import ctr.instoctr.InSto;
import inp.DayIn;
import inp.MinlIn;
import set.DataBaseSet;

import static ctr.Controller.loadMainSet;

public class InputTest {
    public static void main(String[]args){
        try{
            DataBaseSet.loadDataBaseSet();
            loadMainSet();

            new InSto(new DayIn("F:\\gkt\\zyyht\\vipdoc\\sh\\lday\\sh600000.day"));


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
