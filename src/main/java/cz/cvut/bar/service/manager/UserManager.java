package cz.cvut.bar.service.manager;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cz.cvut.bar.model.entity.UserEntity;

@Service(value="userManager")
public class UserManager extends GeneralManager<UserEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7035924044821134299L;
	
	@PostConstruct
	public void init(){
		super.setEntityClass(UserEntity.class);
	}

}
