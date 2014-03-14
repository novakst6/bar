package cz.cvut.bar.controller.http;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cz.cvut.bar.model.entity.FileEntity;
import cz.cvut.bar.model.form.UploadFileForm;
import cz.cvut.bar.service.manager.FileManager;


@Controller
@RequestMapping(value="/files")
public class FileController {
	
	@Autowired
	private FileManager fileManager;
	
	private final String FILE_PATH = "C:\\Temp\\"; //TODO File Service
	
	@RequestMapping(value="upload.htm",method=RequestMethod.GET)
	public String uploadFileGET(@ModelAttribute("formModel") UploadFileForm formModel, Model model){
		model.addAttribute("filesList", fileManager.findAll());
		return "uploadFile";
	}
	
	@RequestMapping(value="upload.htm",method=RequestMethod.POST)
	public String uploadFilePOST(@Valid @ModelAttribute("formModel") UploadFileForm formModel, BindingResult errors){
		if(errors.hasErrors()){
			return "uploadFile";
		}
		
		MultipartFile mFile = formModel.getFile();
		FileEntity file = new FileEntity();
		file.setContentType(mFile.getContentType());
		file.setName(mFile.getOriginalFilename());
		file.setSize(mFile.getSize());
		
		//save file
		try{
			InputStream is = mFile.getInputStream();
			
			File newFile = new File(FILE_PATH+mFile.getOriginalFilename());
			if(newFile.exists()){
				//handle exception
			}
			
			OutputStream os = new FileOutputStream(newFile);
			
			byte[] buffer = new byte[2048];
			int read = 0;
			while((read = is.read(buffer)) != -1){
				os.write(buffer,0,read);
			}
			
			is.close();
			os.close();
			
			fileManager.add(file);
			
		}catch(IOException ex){
			// error message
		}
		
		return "redirect:upload.htm";
	}
	
	@RequestMapping(value="download/{id}", method=RequestMethod.GET)
	public void download(@PathVariable(value="id") Long id, HttpServletResponse response){
		try {
		      FileEntity file = fileManager.findById(id);
		      
		      InputStream is = new FileInputStream(FILE_PATH+file.getName());
		      // copy it to response's OutputStream
		      IOUtils.copy(is, response.getOutputStream());
		      response.setContentType(file.getContentType());
	          response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
		      response.flushBuffer();
		    } catch (IOException ex) {
		    	throw new RuntimeException("IOError writing file to output stream");
		    }
	}
	
	
}
