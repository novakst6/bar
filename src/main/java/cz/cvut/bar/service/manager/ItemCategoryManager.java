package cz.cvut.bar.service.manager;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cz.cvut.bar.model.entity.ItemCategoryEntity;

@Service(value="itemCategoryManager")
public class ItemCategoryManager extends GeneralManager<ItemCategoryEntity>{

	private static final long serialVersionUID = 7035924044821134298L;
	
	@PostConstruct
	public void init(){
		super.setEntityClass(ItemCategoryEntity.class);
	}
}