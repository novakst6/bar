package cz.cvut.bar.service.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

public class BinaryFileService {
		
	public static final int bufferSize = 1024;
	public static final String winLogDir = "E:/CVUT/JEE/bar/";
	public static final String winFileDir = winLogDir+"src/main/webapp/files/";
	
	public static File getFileByName(String fileName){
		return new File(winFileDir+fileName);
	}
	
	public static void sendFileToOutputStream(ServletOutputStream os, InputStream is){
	    try {
		    byte[] buffer = new byte[bufferSize];
		    int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1){
			    os.write(buffer, 0, bytesRead);
			}
			os.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// logging
	public static void log(String txt){
		File f = new File(/*System.getProperty("java.io.tmpdir")+*/"E:/CVUT/JEE/bar/log.txt");
		//txt += " Workdir: "+System.getProperty("user.dir");
		
		//File f;
		
		/*try {
			f = new File(System.getProperty("java.io.tmpdir")+"log.txt");
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(txt.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if(true) return;*/
			
			
		// vytvoření souboru, když je potřeba
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (Exception e) { return;	}
		}
		
		// zapsání textu
		try{
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
		    out.println(txt);
		    out.close();
		    bw.close();
		    fw.close();
		}catch (IOException e) {}
	}
}
