package cz.cvut.bar.controller.http;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.cvut.bar.model.entity.ItemCategoryEntity;
import cz.cvut.bar.model.entity.UserEntity;
import cz.cvut.bar.model.entity.UserRoleEntity;
import cz.cvut.bar.model.form.UserForm;
import cz.cvut.bar.model.form.UserRoleForm;
import cz.cvut.bar.service.manager.ItemCategoryManager;
import cz.cvut.bar.service.manager.UserManager;
import cz.cvut.bar.service.manager.UserRoleManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private UserManager userManager;	
	@Autowired private UserRoleManager userRoleManager;
	@Autowired private ItemCategoryManager itemCategoryManager;
	@Autowired private StandardPasswordEncoder spe;
	
	// index
	@RequestMapping(value = {"/", "/index.htm"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, Authentication auth) {
		
		logger.info("index");
		
		if(itemCategoryManager.findAll().isEmpty()){
			logger.debug("Initing cats!");
			
			ItemCategoryEntity cat1 = new ItemCategoryEntity();
			cat1.setName("Alko");
			itemCategoryManager.add(cat1);
			
			ItemCategoryEntity cat2 = new ItemCategoryEntity();
			cat2.setName("Maso");
			itemCategoryManager.add(cat2);
		}else{			
			logger.info("Cats not empty: "+itemCategoryManager.findAll().size());
		}

		if(userRoleManager.findAll().isEmpty()){
			logger.debug("Initing users!");
			
			UserRoleEntity r1 = new UserRoleEntity();
			r1.setName("ROLE_USER");
			userRoleManager.add(r1);
			
			UserRoleEntity r2 = new UserRoleEntity();
			r2.setName("ROLE_ADMIN");
			userRoleManager.add(r2);
			
			UserEntity u1 = new UserEntity();
			u1.setUsername("USER_1");
			u1.setPassword(spe.encode("12345"));
			u1.getRoles().add(r1);
			userManager.add(u1);
			
			UserEntity u2 = new UserEntity();
			u2.setUsername("USER_2");
			u2.setPassword(spe.encode("12345"));
			u2.getRoles().add(r1);
			u2.getRoles().add(r2);
			userManager.add(u2);
		} else{
			System.out.print("Nothing to do.");
			logger.info("Users not empty");
		}
		
		model.addAttribute("users", userManager.findAll());
		model.addAttribute("cats", itemCategoryManager.findAll());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("time", formattedDate );
		
		if(auth != null){
			User u = (User) auth.getPrincipal();
			model.addAttribute("user",u.getUsername());
			model.addAttribute("role", u.getAuthorities());
		}
		
		return "home";
	}
	
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/list.htm", method = RequestMethod.GET)
	public String list(Locale locale, Model model){
		model.addAttribute("roleList", userRoleManager.findAll());
		model.addAttribute("userList", userManager.findAll());
		return "list";
	}
	
	@RequestMapping(value="/addUserRole.htm",method=RequestMethod.GET)
	public String addUserRoleGET(@ModelAttribute(value="formModel") UserRoleForm formModel, Model model, Locale locale){
		model.addAttribute("roleList", userRoleManager.findAll());
		return "addUserRole";
	}
	
	@RequestMapping(value="/addUserRole.htm",method=RequestMethod.POST)
	public String addUserRolePOST(@ModelAttribute(value="formModel") UserRoleForm formModel, BindingResult errors, Model model){
		UserRoleEntity role = new UserRoleEntity();
		role.setName(formModel.getName());
		userRoleManager.add(role);
		return "redirect:addUserRole.htm";
	}
	
	@RequestMapping(value="/addUser.htm", method = RequestMethod.GET)
	public String addUserGET(@ModelAttribute(value="formModel") UserForm formModel, Model model){
		model.addAttribute("roles", userRoleManager.findAll());
		model.addAttribute("userList", userManager.findAll());
		return "addUser";
	}
	
	@RequestMapping(value="/addUser.htm", method = RequestMethod.POST)
	public String addUserPOST(@ModelAttribute(value="formModel") UserForm formModel, BindingResult errors, Model model){
		UserEntity user = new UserEntity();
		user.setUsername(formModel.getUsername());
		user.setPassword(spe.encode(formModel.getPassword()));
		List<UserRoleEntity> roles = new LinkedList<UserRoleEntity>();
		if(formModel.getRoles() != null){
			for(Long l: formModel.getRoles()){
				UserRoleEntity r = userRoleManager.findById(l);
				roles.add(r);
			}
		}
		user.setRoles(roles);
		userManager.add(user);
		
		return "redirect:addUser.htm";
	}
	
	@RequestMapping(value="/websockettest.htm", method = RequestMethod.GET)
	public String addUserGET(){
		return "websockettest";
	}
	
}
