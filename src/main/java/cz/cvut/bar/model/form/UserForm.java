package cz.cvut.bar.model.form;

import java.io.Serializable;
import java.util.ArrayList;

public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2581873104997429581L;

	private Long id;
	private String username;
	private String password;
	private ArrayList<Long> roles = new ArrayList<Long>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Long> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<Long> roles) {
		this.roles = roles;
	}
	
	
}
