package cz.cvut.bar.controller.json.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
	
	private List<JsonFieldError> errors = new ArrayList<>();
	
	public ValidationError(){}
	
	public void addFieldError(String field, String message){
		errors.add(new JsonFieldError(field, message));
	}

	public List<JsonFieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<JsonFieldError> errors) {
		this.errors = errors;
	}
	
}
