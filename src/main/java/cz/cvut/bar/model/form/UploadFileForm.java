package cz.cvut.bar.model.form;

import javax.validation.constraints.AssertFalse;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileForm {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@AssertFalse(message="Soubor nesmí být prázdný!")
	public boolean isEmptyFile(){
		if(file != null){
			return file.isEmpty();
		}else{
			return true;
		}
	}
	
	
}
