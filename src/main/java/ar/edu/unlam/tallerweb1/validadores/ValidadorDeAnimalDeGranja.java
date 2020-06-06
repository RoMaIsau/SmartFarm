package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

@Component
public class ValidadorDeAnimalDeGranja implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ValidadorDeAnimalDeGranja.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object objectoParaValidar, Errors errores) {
		
		AnimalDeGranja animal = AnimalDeGranja.class.cast(objectoParaValidar);
		
		if (animal.getPeso() == null) {
			errores.rejectValue("peso", "peso.incompleto");
		} else if(animal.getPeso() <= 0) {
			errores.rejectValue("peso", "peso.valorNegativo");
		}
	}

}
