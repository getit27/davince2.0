import ctr.Controller;
import ctr.GlobalVariable;
import ctr.comctr.BatchComAva;
import ctr.comctr.BatchComDev;
import ctr.comctr.BatchComGrow;
import ctr.instoctr.BatchDayInSto;
import set.DataBaseSet;

import java.util.Scanner;

import static ctr.Controller.*;

public class App {
    public static void main(String[]args){
        try {
            DataBaseSet.loadDataBaseSet();
            loadMainSet();
            GlobalVariable.initGlobalVariable();

            char read;
            int state=0;
            int type=0;
            int com=0;
            System.out.println("1.输入\n2.计算");
            read = (char) System.in.read();
            while(read=='\n')
                read = (char) System.in.read();
            switch (read){
                case '1':state=1;break;
                case '2':state=2;break;
                case '3':state=-1;break;
            }
            while(state!=-1){
                switch (state){
                    case 0:
                        System.out.println("1.输入\n2.计算\n3.退出");
                        read = (char) System.in.read();
                        while(read=='\n')
                            read = (char) System.in.read();
                        switch (read){
                            case '1':state=1;break;
                            case '2':state=2;break;
                            case '3':state=-1;break;
                        }
                        break;
                    case 1:
                        System.out.println("1.日线\n2.分钟线\n3.全部\n4.返回");
                        read = (char) System.in.read();
                        while(read=='\n')
                            read = (char) System.in.read();
                        switch (read) {
                            case '1': type=0;state=11;break;
                            case '2': type=1;state=11;break;
                            case '3': type=2;state=11;break;
                            case '4':state=0;break;
                        }
                        break;
                    case 11:
                        if(type==0||type==2){
                            new BatchDayInSto(shday);
                            new BatchDayInSto(szday);
                        }
                        if(type==1||type==2){
                            new BatchDayInSto(shminl);
                            new BatchDayInSto(szminl);
                        }
                        state=0;
                        System.out.println("完成");
                        break;
                    case 2:
                        System.out.println("1.日线\n2.分钟线\n3.全部\n4.返回");
                        read = (char) System.in.read();
                        while(read=='\n')
                            read = (char) System.in.read();
                        switch (read) {
                            case '1': type=0;state=21;break;
                            case '2': type=1;state=21;break;
                            case '3': type=2;state=21;break;
                            case '4':state=0;break;
                        }
                        break;
                    case 21:
                        System.out.println("1.均值\n2.增长率\n3.偏离度\n4.全部\n5.返回");
                        read = (char) System.in.read();
                        while(read=='\n')
                            read = (char) System.in.read();
                        switch (read) {
                            case '1': com=0;state=22;break;
                            case '2': com=1;state=22;break;
                            case '3': com=2;state=22;break;
                            case '4': com=3;state=22;break;
                            case '5':state=2;break;
                        }
                        break;
                    case 22:
                        if(com==0){
                            new BatchComAva(type);
                        } else if(com==1){
                            new BatchComGrow(type);
                        }else if(com==2){
                            new BatchComDev(type);
                        }else{
                            new BatchComAva(type);
                            new BatchComGrow(type);
                            new BatchComDev(type);
                        }
                        System.out.println("完成");
                        state=0;
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
