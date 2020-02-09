package ctr.instoctr;

import inp.DayIn;
import sto.Storer;

import java.io.File;

public class BatchDayInSto {
    public BatchDayInSto(String path)throws Exception{
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            int number = files.length;
            System.out.println("正在处理"+path+"共计"+number+"个文件");
            for (int i = 0; i < files.length; i++) {
                System.out.println("处理"+(i+1)+"/"+number+":"+files[i].getName()+"...");
                new Storer().store(new DayIn(files[i]).getPackage());
            }
        }
    }
}
