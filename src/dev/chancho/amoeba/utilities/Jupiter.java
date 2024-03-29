package dev.chancho.amoeba.utilities;

import dev.chancho.amoeba.Board;

import java.io.*;

public class Jupiter {
    /*This is a class for handling file -> IO -> operations*/
    FileInputStream in;
    FileOutputStream out;
    public String working_directory;
    File temp, localFolder;

    public Jupiter(Board b){
        working_directory = b.hub.workingDirectory;
        working_directory += "/.chancho_dev/";
        localFolder = new File(working_directory);
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
        deleteTempFile();
        try {
            if(temp.createNewFile()){
                setWriteTarget(temp.getPath());
            }
        } catch (IOException e) {
            System.out.print("Could not create tempFile\n");
            throw new RuntimeException(e);
        }
    }
    public void deleteTempFile(){
        temp = new File(working_directory+"temp");
        if (temp.isFile() && temp.delete()) {
            System.out.print("Deleted existing tempFile\n");
        }
    }

    public void write(String s){
        for(char c : s.toCharArray()){
            try {
                out.write(c);
            } catch (IOException e) {
                System.out.print("Unable to write to output stream\n");
                throw new RuntimeException(e);
            }
        }
    }

    public void saveFile(String path){
        try {
            out.close();
            setReadTarget(temp.getPath());
            setWriteTarget(working_directory+path);
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        } catch (IOException e) {
            System.out.print("Unable to save/copy temp file\n");
            throw new RuntimeException(e);
        }
    }

    public String[] getMacroList(){
        return localFolder.list();
    }

    public InputStream getInputStream(){
        return in;
    }

}
