package ctr.comctr;

import acc.LimAccess;
import com.AvaCom;
import pac.dayava.DayAvaPac;
import pac.dayclose.DayClosePac;
import pac.dayva.DayVAPac;
import pac.dayvol.DayVolPac;
import pac.minlava.MinlAvaPac;
import pac.minlclose.MinlClosePac;
import pac.table.TableListPac;

public class BatchComAva {
    public BatchComAva()throws Exception{
        TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
        for(int i=0;i<tablelist.getDataLength();i++){
            String tablename=tablelist.getData(i);
            String stockname=tablename.substring(0,8);
            if(tablename.substring(8).equals("day")){
                System.out.println(tablename);
                new ComAvaS(new DayClosePac(stockname),new AvaCom(5, DayClosePac.class, DayAvaPac.class),
                        new int[]{5,10,15,30,40,60,120,250});
                new ComAvaS(new DayVolPac(stockname),new AvaCom(5,DayVolPac.class, DayVAPac.class),
                        new int[]{5,10,20});
            }else if(tablename.substring(8).equals("minl")){
                System.out.println(tablename);
                new ComAvaS(new MinlClosePac(stockname),new AvaCom(5 ,MinlClosePac.class, MinlAvaPac.class),
                        new int[]{5,10,20});
            }
        }
    }
    public BatchComAva(int type)throws Exception{
        if(type==0){
            TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
            for(int i=0;i<tablelist.getDataLength();i++){
                String tablename=tablelist.getData(i);
                String stockname=tablename.substring(0,8);
                if(tablename.substring(8).equals("day")){
                    System.out.println(tablename);
                    new ComAvaS(new DayClosePac(stockname),new AvaCom(5, DayClosePac.class, DayAvaPac.class),
                            new int[]{5,10,15,30,40,60,120,250});
                    new ComAvaS(new DayVolPac(stockname),new AvaCom(5,DayVolPac.class, DayVAPac.class),
                            new int[]{5,10,20});
                }
            }
        }else if(type==1){
            TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
            for(int i=0;i<tablelist.getDataLength();i++){
                String tablename=tablelist.getData(i);
                String stockname=tablename.substring(0,8);
                if(tablename.substring(8).equals("minl")){
                    System.out.println(tablename);
                    new ComAvaS(new MinlClosePac(stockname),new AvaCom(5 ,MinlClosePac.class, MinlAvaPac.class),
                            new int[]{5,10,20});
                }
            }
        }else if(type==2){
            TableListPac tablelist= (TableListPac) new LimAccess(new TableListPac()).getPackage();
            for(int i=0;i<tablelist.getDataLength();i++){
                String tablename=tablelist.getData(i);
                String stockname=tablename.substring(0,8);
                if(tablename.substring(8).equals("day")){
                    System.out.println(tablename);
                    new ComAvaS(new DayClosePac(stockname),new AvaCom(5, DayClosePac.class, DayAvaPac.class),
                            new int[]{5,10,15,30,40,60,120,250});
                    new ComAvaS(new DayVolPac(stockname),new AvaCom(5,DayVolPac.class, DayVAPac.class),
                            new int[]{5,10,20});
                }else if(tablename.substring(8).equals("minl")){
                    System.out.println(tablename);
                    new ComAvaS(new MinlClosePac(stockname),new AvaCom(5 ,MinlClosePac.class, MinlAvaPac.class),
                            new int[]{5,10,20});
                }
            }
        }
    }
}
