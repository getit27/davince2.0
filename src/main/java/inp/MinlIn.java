package inp;

import pac.inter.Storable;
import pac.minl.MinlData;
import pac.minl.MinlPac;
import tol.BytesTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MinlIn implements Inputer {
    static final Class<?> pactype= MinlPac.class;
    String filelocation=null;
    File file=null;
    public MinlIn(String location){
        filelocation=location;
    }
    public MinlIn(File infile){
        file=infile;
    }
    public Class<?> getPacType(){ return pactype; }
    public Storable getPackage(){
        if(file==null) {
            if (filelocation == null) {
                try {
                    throw new Exception("inputer has not been initialized!");
                }catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                }
                return null;
            }
            file = new File(filelocation);
        }
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(file);
        }catch (IOException e){
            System.out.println(e);
        }

        String filename=file.getName();
        String se=filename.substring(0,2);
        int sn=Integer.parseInt(filename.substring(2,8));
        MinlPac pac=new MinlPac(se,sn);

        int probe;
        int counter=0;
        try {
            probe=fis.read();
            while (probe!=-1) {
                fis.skip(-1);
                //System.out.println(counter++);
                byte[][]bytes=new byte[8][4];
                for(int i=0;i<8;i++){
                    fis.read(bytes[i]);
                }
                byte[]date={bytes[0][0],bytes[0][1]};
                byte[]time={bytes[0][2],bytes[0][3]};
                int datei=BytesTo.Bytes2int(date);
                int timei= BytesTo.Bytes2int(time);
                int year,month,day;
                int hour,minute;
                year=(datei/2048)+2004;
                month=(datei%2048)/100;
                day=(datei%2048)%100;
                hour=timei/60;
                minute=timei%60;
                int ymd=year*10000+month*100+day;
                int hm=hour*100+minute;

                MinlData min=new MinlData(ymd,hm,
                        (int)(BytesTo.Bytes2float(bytes[1])*100),
                        (int)(BytesTo.Bytes2float(bytes[2])*100),
                        (int)(BytesTo.Bytes2float(bytes[3])*100),
                        (int)(BytesTo.Bytes2float(bytes[4])*100),
                        (int)BytesTo.Bytes2float(bytes[5]),
                        BytesTo.Bytes2int(bytes[6]));
                pac.newMinl(min);
                probe=fis.read();
            }
        }catch(IOException e){
            System.out.println(e);
        }
        filelocation=null;
        file=null;
        return pac;
    }
}