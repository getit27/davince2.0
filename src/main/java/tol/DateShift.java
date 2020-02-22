package tol;

public class DateShift {
    static public int comDateShift(int dt1,int dt2){
        int y1=dt1/10000,m1=dt1%10000/100,d1=dt1%100,
                y2=dt2/10000,m2=dt2%10000/100,d2=dt2%100;
        if(dt1>dt2){
            return (y1-y2)*266+(m1-m2)*31+d1-d2;
        }else{
            return (y2-y1)*266+(m2-m1)*31+d2-d1;
        }
    }
}
