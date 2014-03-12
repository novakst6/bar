/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.bar.service.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
//
//
///**
// *
// * @author Stenlik
// */
public class HibernateEntityObjectMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7562118210319448262L;

	public HibernateEntityObjectMapper() {
		Hibernate4Module hbm = new Hibernate4Module();
		hbm.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        this.registerModule(hbm);
    }
    
}
