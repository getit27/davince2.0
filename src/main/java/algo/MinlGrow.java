package algo;

import acc.LimAccess;
import acc.UniAccess;
import ctr.GlobalVariable;
import pac.Packages;
import pac.dayclose.DayClosePac;
import pac.key.KeyYmd;
import pac.minlclose.MinlClosePac;

public class MinlGrow{
    String stockexchange;
    int stocknum;
    int firstday;
    int firstmintue;
    MinlClosePac mcp=null;
    DayClosePac dcp=null;
    public MinlGrow(String stockexchange,int stocknum,int firstday,int firstmintue) {
        this.stockexchange=stockexchange;
        this.stocknum=stocknum;
        this.firstday=firstday;
        this.firstmintue=firstmintue;
    }
    public MinlGrow(String stockexchange,int stocknum,int firstday,int firstmintue,
                    MinlClosePac mcp,DayClosePac dcp){
        this.stockexchange=stockexchange;
        this.stocknum=stocknum;
        this.firstday=firstday;
        this.firstmintue=firstmintue;
        this.mcp=mcp;
        this.dcp=dcp;
    }
    public void alGo(){
        try{

            // 获取分钟线数据包
            if(mcp==null) {
                mcpCom();
            }

            // 确定实际第一天
            int actfirstday=mcp.getDayClose().get(0).getYmd();
            int actfirstmin=mcp.getDayClose().get(0).getHm();

            // 获取日线数据
            boolean day= true;
            if(dcp==null) {
                day = GlobalVariable.tablelist.contains(
                        mcp.getStockexchange() + String.format("%06d", mcp.getStocknum()));

                if (day) {
                    dcp = new DayClosePac(stockexchange, stocknum);
                    dcp.setLimitations(new String[]{"ymd>=" + (firstday-1)});
                    new LimAccess(dcp).getPackage();
                }
            }

            int lastday=actfirstday-1;
            int lastMinClose=0;



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private int getDayClose(int day){
        KeyYmd key=new KeyYmd(day);

        return 0;
    }

    private void mcpCom()throws Exception{
        if (firstday == -1) {
            MinlClosePac acc1 = new MinlClosePac(stockexchange, stocknum);
            new UniAccess(acc1).getPackage();
            mcp = acc1;
        } else if (firstmintue == -1) {
            MinlClosePac acc1 = new MinlClosePac(stockexchange, stocknum);
            acc1.setLimitations(new String[]{"ymd>=" + firstday});
            new LimAccess(acc1).getPackage();
            mcp = acc1;
        } else {
            MinlClosePac acc1 = new MinlClosePac(stockexchange, stocknum);
            MinlClosePac acc2 = new MinlClosePac(stockexchange, stocknum);
            acc1.setLimitations(new String[]{"ymd>" + firstday});
            acc2.setLimitations(new String[]{"ymd=" + firstday, "hm>=" + firstmintue});
            new LimAccess(acc1).getPackage();
            new LimAccess(acc2).getPackage();
            mcp = (MinlClosePac) acc2.addAll((Packages) acc1);
        }
    }

}
