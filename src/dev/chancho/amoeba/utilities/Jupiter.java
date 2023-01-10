package dev.chancho.amoeba.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Jupiter {
    /*This is a class for handling file -> IO -> operations*/
    FileInputStream in;
    FileOutputStream out;

    public Jupiter(){
        in = null;
        out = null;
    }

    public void setReadTarget(String path) throws IOException {
        try{
            in = new FileInputStream(path);
        }catch(IOException e){
            String msg = String.format("Could not open file for reading: %s\n%s",path,e.getMessage());
            System.out.println(msg);
        }
    }
    public void setWriteTarget(String path) throws IOException {
        try{
            out = new FileOutputStream(path);
        }catch(IOException e){
            String msg = String.format("Could not open file for writing: %s\n%s",path,e.getMessage());
            System.out.println(msg);
        }
    }
}
