package dev.chancho.unicell;

import java.io.File;

public class Unicell {
	public static void main(String[] args) {
		String dir = "/home/pirate/Mohawk Test/";
		File path = new File(dir);
		for(String f : path.list()){
			String fNew = f.substring(0, f.indexOf("_"));
			fNew+=f.substring(f.lastIndexOf("_"));
			System.out.println(fNew);
			File image = new File(dir+f);
			image.renameTo(new File(dir+fNew));
			
		};
	}
}
