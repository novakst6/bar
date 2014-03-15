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

import cz.cvut.bar.model.entity.ItemCategoryEntity;
import cz.cvut.bar.model.entity.UserEntity;
import cz.cvut.bar.service.json.JsonResponse;
import cz.cvut.bar.service.manager.ItemCategoryManager;
import cz.cvut.bar.service.filesystem.BinaryFileService;

@Controller
@RequestMapping(value="/json/item_category")
public class ItemCategoryJsonController {

	private static final Logger logger = LoggerFactory.getLogger(ItemCategoryJsonController.class);
	
	@Autowired
	private ItemCategoryManager itemCategoryManager;
	
	@RequestMapping(value={"/create", "/create/"}, method = RequestMethod.GET)
	public @ResponseBody Object create() throws Exception{		
		ItemCategoryEntity cat = new ItemCategoryEntity();
		if(itemCategoryManager.add(cat)){
			return cat;			
		}else{
			return JsonResponse.NOK;
		}		
	}
	
	@RequestMapping(value={"/read/{id}", "{id}"}, method = RequestMethod.GET)
	public @ResponseBody Object read(@PathVariable Long id) throws Exception{
		
		ItemCategoryEntity user = itemCategoryManager.findById(id);
		logger.info(user+"");
		return user;
	}

	/*
	//@RequestMapping(value="/update/{id}", method = {RequestMethod.POST})
	@RequestMapping(value="/update/{id}", method = {RequestMethod.POST}, consumes = "application/x-www-form-urlencoded", headers = "content-type=application/x-www-form-urlencoded")
	//@RequestMapping(value="/update/{id}", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/json")
	public @ResponseBody String update(@PathVariable Long id, @RequestBody String cat) throws Exception{
		logger.info("Cat update, id: "+id);
		logger.info("Cat: "+cat);
		
		return JsonResponse.TEST;
		
		// get user
		//UserEntity user = userManager.findById(id);		
		//if(user==null) { return JsonResponse.NOK; }
		
		// update/edit 
		//if(userManager.edit(user)){
		//	return JsonResponse.OK;			
		//}		
		//return JsonResponse.NOK;
	}*/
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/json")
	//@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody String update(@RequestBody final ItemCategoryEntity cat){
		itemCategoryManager.add(cat);
		logger.info("Editing cat: " + cat);
		return JsonResponse.TEST;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Object delete(@PathVariable Long id) throws Exception{
		
		// get user
		ItemCategoryEntity cat = itemCategoryManager.findById(id);		
		if(cat==null) { return JsonResponse.NOK; }
		
		// delete 
		if(itemCategoryManager.delete(cat)){
			return JsonResponse.OK;			
		}		
		return JsonResponse.NOK;
	}
}
