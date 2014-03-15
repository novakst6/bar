package cz.cvut.bar.service.manager;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cz.cvut.bar.model.entity.FileEntity;

@Service(value="fileManager")
public class FileManager extends GeneralManager<FileEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3479863871423854855L;

	@PostConstruct
	public void init(){
		super.setEntityClass(FileEntity.class);
	}
}
