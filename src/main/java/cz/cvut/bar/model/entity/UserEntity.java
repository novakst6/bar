package cz.cvut.bar.model.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@Entity
@Table(name="USERS")
@JsonAutoDetect
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7150396999231840133L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<UserRoleEntity> roles = new LinkedList<UserRoleEntity>();

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

	public List<UserRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleEntity> roles) {
		this.roles = roles;
	}
	
	
	
}
