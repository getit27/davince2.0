package tol;

public class RoundDivision {
    static public int roundDiv(long dividend ,int divisor ){
        dividend*=10;
        int result=(int)(dividend/divisor);
        if(result%10<5){
            result/=10;
        }else{
            result/=10;
            result++;
        }
        return result;
    }
}
