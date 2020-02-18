package ctr;

import acc.LimAccess;
import pac.table.TableListPac;

public class GlobalVariable {
    public static TableListPac tablelist=null;
    public static void initGlobalVariable(){
        tablelist=new TableListPac();
        new LimAccess(tablelist).getPackage();
    }

}
