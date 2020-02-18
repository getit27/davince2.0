package ctr;

import acc.LimAccess;
import com.AvaCom;
import ctr.comctr.BatchComAva;
import ctr.comctr.ComAva;
import ctr.instoctr.BatchDayInSto;
import ctr.instoctr.BatchMinlInSto;
import ctr.instoctr.InSto;
import inp.DayIn;
import inp.MinlIn;
import pac.dayava.DayAvaPac;
import pac.dayclose.DayClosePac;
import pac.dayva.DayVAPac;
import pac.dayvol.DayVolPac;
import pac.minlava.MinlAvaData;
import pac.minlava.MinlAvaPac;
import pac.minlclose.MinlClosePac;
import pac.table.TableListPac;
import set.DataBaseSet;

import java.io.FileInputStream;
import java.util.Properties;

public class Controller {
    static String shday;
    static String shminl;
    static String szday;
    static String szminl;

    public static void main(String[]args) {
        //new InSto(new DayIn("F:\\gkt\\zyyht\\vipdoc\\sh\\lday\\sh000001.day"));
        //new InSto(new MinlIn("F:\\gkt\\zyyht\\vipdoc\\sh\\minline\\sh000001.lc1"));
        try {
            DataBaseSet.loadDataBaseSet();
            loadMainSet();
            GlobalVariable.initGlobalVariable();
            //new ComAva(new DayClosePac("sh", 1), new AvaCom(5, DayClosePac.class, DayAvaPac.class));
            //new ComAva(new MinlClosePac("sh",1),new AvaCom(5 ,MinlClosePac.class, MinlAvaPac.class));
            //new ComAva(new DayVolPac("sh",1),new AvaCom(5,DayVolPac.class, DayVAPac.class));
            System.out.println("shday");
            new BatchDayInSto(shday);
            System.out.println("shminl");
            new BatchMinlInSto(shminl);
            System.out.println("szday");
            new BatchDayInSto(szday);
            System.out.println("szminl");
            new BatchMinlInSto(szminl);
            //new LimAccess(new TableListPac()).getPackage();
            new BatchComAva();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void loadMainSet()throws Exception{
        Properties pro = new Properties();
        FileInputStream in = new FileInputStream("a.properties");
        pro.load(in);
        shday=pro.getProperty("SHDAY");
        shminl=pro.getProperty("SHMINL");
        szday=pro.getProperty("SZDAY");
        szminl=pro.getProperty("SZMINL");

        in.close();
    }
}