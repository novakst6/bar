package cz.cvut.bar.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
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

import cz.cvut.bar.model.entity.UserEntity;
import cz.cvut.bar.model.entity.UserRoleEntity;
import cz.cvut.bar.model.form.UserForm;
import cz.cvut.bar.model.form.UserRoleForm;
import cz.cvut.bar.service.manager.UserManager;
import cz.cvut.bar.service.manager.UserRoleManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserRoleManager userRoleManager;
	
	@Autowired
	private StandardPasswordEncoder spe;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Authentication auth) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("Èas na serveru", formattedDate );
		
		if(auth != null){
			User u = (User) auth.getPrincipal();
			model.addAttribute("user",u.getUsername());
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
		Set<UserRoleEntity> roles = new HashSet<UserRoleEntity>();
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
	
}
