package cz.cvut.bar.service.manager;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
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

	public UserEntity findByUsername(String username) {
		Session s = getCurrentSession();
		try{
			Query q = s.createQuery("SELECT u FROM UserEntity as u WHERE u.username = :username");
			q.setParameter("username", username);
			@SuppressWarnings("unchecked")
			List<UserEntity> result = q.list();
			if(result.isEmpty()){
				return null;
			} else if(result.size() > 1){
				throw new Exception("Duplicate users");
			}
			return result.get(0);
		}catch(Exception e){
			return null;
		}
	}

}
