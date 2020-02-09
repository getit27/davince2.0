package tol;

public class BytesTo{
    static public int Bytes2int(byte[] byteArray){
        int value=0;
        try{
            if(byteArray.length==4){
                value=byteArray[0]&0xff;
                value |=(byteArray[1]&0xff)<<8;
                value |=(byteArray[2]&0xff)<<16;
                value |=(byteArray[3]&0xff)<<24;
            }else if(byteArray.length==2) {
                value=byteArray[0]&0xff;
                value |=(byteArray[1]&0xff)<<8;
            }else{
                throw new Exception("error");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return value;
    }
    static public float Bytes2float(byte[] byteArray){
        try{
                if(byteArray.length!=4){
                    throw new Exception("error");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            int value=byteArray[0]&0xff;
            value |=(byteArray[1]&0xff)<<8;
            value |=(byteArray[2]&0xff)<<16;
            value |=(byteArray[3]&0xff)<<24;
            return Float.intBitsToFloat(value);
    }
}