package ctr;

import acc.LimAccess;
import pac.table.TableListPac;
import set.DataBaseSet;

public class GlobalVariable {
    public static TableListPac tablelist=null;
    public static void initGlobalVariable(){
        tablelist=new TableListPac(DataBaseSet.DB_NAME);
        new LimAccess(tablelist).getPackage();
    }

}
