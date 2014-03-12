package cz.cvut.bar.controller.json;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cz.cvut.bar.model.entity.UserEntity;
import cz.cvut.bar.service.manager.UserManager;

@Controller
@RequestMapping(value="/json/user")
public class UserJsonController {

	private static final Logger logger = LoggerFactory.getLogger(UserJsonController.class);

	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public @ResponseBody Object getUser(@PathVariable Long id) throws Exception{
		
		UserEntity user = userManager.findById(id);
		logger.info(user+"");
		return user;
	}
	
	@RequestMapping(method={RequestMethod.POST})
	@ResponseStatus(value=HttpStatus.CREATED)
	public void postUser(@RequestBody final UserEntity user){
		userManager.add(user);
		logger.info(user.getRoles().size()+" Tolik ma roli");
		
	}
	
}
