package dev.chancho.amoeba.utilities;

import dev.chancho.amoeba.Board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Jupiter {
    /*This is a class for handling file -> IO -> operations*/
    FileInputStream in;
    FileOutputStream out;
    String working_directory;
    File temp;

    public Jupiter(Board b){
        working_directory = b.hub.workingDirectory;
        working_directory += "/.chancho_dev/";
        File localFolder = new File(working_directory);
        if(localFolder.isDirectory() || localFolder.mkdir()){
            System.out.printf("Established or Located Working Directory: %s\n",working_directory);
        }
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

    public void createTempFile(){
        temp = new File(working_directory+"temp");
        if (temp.isFile() && temp.delete()) {
                System.out.print("Deleted existing tempFile\n");
        }
        try {
            if(temp.createNewFile()){
                setWriteTarget(temp.getPath());
            }
        } catch (IOException e) {
            System.out.print("Could not create tempFile\n");
            throw new RuntimeException(e);
        }
    }

    public void write(String s){
        for(char c : s.toCharArray()){
            try {
                out.write(c);
            } catch (IOException e) {
                System.out.print("Unable to write to output stream");
                throw new RuntimeException(e);
            }
        }
    }
}
