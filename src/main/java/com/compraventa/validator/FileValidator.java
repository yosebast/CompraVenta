package com.compraventa.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.compraventa.entity.EntidadProducto;

@Component
public class FileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EntidadProducto.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		EntidadProducto  ep = (EntidadProducto) target;
		if(ep.getUrlFoto1().getSize() != 0  &&  ep.getUrlFoto1().isEmpty()){
			errors.rejectValue("urlFoto1", "notFound", "file not found");
			
		}
	}

}
