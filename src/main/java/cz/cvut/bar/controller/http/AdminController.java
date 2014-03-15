package cz.cvut.bar.controller.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.SpringVersion;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.cvut.bar.service.filesystem.BinaryFileService;

@Controller
public class AdminController {
	
	// admin
	@RequestMapping(value = {"/admin", "/admin.htm"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, Authentication auth) {
			
		// �as na serveru
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("time", formattedDate );		
		
		// verze springu		
		model.addAttribute("springVersion", SpringVersion.getVersion());
		
		return "admin";
	}
		
	// files
	@RequestMapping(value = "/files/**", method = RequestMethod.GET)
	@ResponseBody
	public void getFile(HttpServletRequest request, HttpServletResponse response) {
		
		boolean fileOK = true;
		String relativeFilePath = ""; //relativn� cesta k souboru, od ..files/
		File theFile = null;
		
		do{
			// parsovat adresu
			relativeFilePath = request.getRequestURI().split("/files/")[1];
			if(relativeFilePath == null){ fileOK = false; break; }
			if(relativeFilePath.equals("")){ fileOK = false; break; }
			//BinaryFileService.log("relativeFilePath: "+relativeFilePath);
			
			// z�skat odkaz na soubor ve FS
			theFile = BinaryFileService.getFileByName(relativeFilePath);
			if(theFile == null){ fileOK = false; break; }
			if(!theFile.exists()){ fileOK = false; break; }
		}while(false);
		
		
		// poslat soubor nebo not found
		if(fileOK){
			try {
				InputStream is = new FileInputStream(theFile);
				ServletOutputStream os = response.getOutputStream();
				BinaryFileService.sendFileToOutputStream(os, is);
			} catch (Exception e) {}
		}else{
			try {
				response.getOutputStream().write(((String)"[NOT_FOUND]").getBytes());
				response.getOutputStream().flush();
			} catch (Exception e) {}
		}
	}
}
