package cz.cvut.bar.model.form;

import java.io.Serializable;

public class UserRoleForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1265388664389368283L;
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
