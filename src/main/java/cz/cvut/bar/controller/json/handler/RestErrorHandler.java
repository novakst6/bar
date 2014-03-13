package cz.cvut.bar.controller.json.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationError processMethodArgumentNotValid(MethodArgumentNotValidException ex){
		BindingResult result = ex.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();
		ValidationError verr = new ValidationError();
		for(FieldError f: errors){
			verr.addFieldError(f.getField(), f.getDefaultMessage());
		}
		return verr;
	}
}
