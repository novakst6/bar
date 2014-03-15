package cz.cvut.bar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@Entity
@Table(name="item_category")
@JsonAutoDetect
public class ItemCategoryEntity implements Serializable{

	private static final long serialVersionUID = 7150396999231840133L;
	
	// ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; public Long getId() {return id;} public void setId(Long id){this.id = id;}
	
	// name
	@Column(name="name")
	private String name; public String getName() {return name;} public void setName(String name){this.name = name;}
	
	// toString
	@Override
	public String toString(){
		return "id:"+id+", name:"+name;		
	}
}
