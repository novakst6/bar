package cz.cvut.bar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FILES")
public class FileEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030716748261216245L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FILE_ID")
	private Long id;
	@Column(name="PATH_TO_FILE")
	private String path;
	@Column(name="FILE_TYPE")
	private String contentType;
	@Column(name="FILE_NAME")
	private String name;
	@Column(name="FILE_SIZE")
	private Long size;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
