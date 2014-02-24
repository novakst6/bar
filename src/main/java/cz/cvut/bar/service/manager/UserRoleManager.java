package cz.cvut.bar.service.manager;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import cz.cvut.bar.model.entity.UserRoleEntity;

@Service(value="userRoleManager")
public class UserRoleManager extends GeneralManager<UserRoleEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 577460168515994143L;

	@PostConstruct
	public void init(){
		super.setEntityClass(UserRoleEntity.class);
	}
}
