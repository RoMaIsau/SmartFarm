package ar.edu.unlam.tallerweb1.validadores;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

public class ValidadorDeAnimalDeGranjaTest {
	
	private Validator validador;
	
	@Before
	public void crearValidador() {
		
		this.validador = new ValidadorDeAnimalDeGranja();
	}
	
	@Test
	public void cuandoElPesoEsNullDeberiaFallarLaValidacion() {
		
		AnimalDeGranja animalInvalido = new AnimalDeGranja();
		
		Errors errors = new BeanPropertyBindingResult(animalInvalido, "animal");
		
		validador.validate(animalInvalido, errors);
		
		assertThat(errors.hasErrors()).isEqualTo(true);
		assertThat(errors.getFieldError("peso").getCode()).isEqualTo("peso.incompleto");
	}
	
	@Test
	public void cuandoElPesoEsNegativoDeberiaFallarLaValidacion() {
		
		AnimalDeGranja animalConPesoNegativo = new AnimalDeGranja();
		animalConPesoNegativo.setPeso(-100d);
		
		Errors errors = new BeanPropertyBindingResult(animalConPesoNegativo, "animal");
		
		validador.validate(animalConPesoNegativo, errors);
		
		assertThat(errors.hasErrors()).isEqualTo(true);
		assertThat(errors.getFieldError("peso").getCode()).isEqualTo("peso.valorNegativo");
	}
	
	@Test
	public void cuandoElPesoEsMayorQueCeroNoDeberiaFallarLaValidacion() {
		
		AnimalDeGranja animalValido = new AnimalDeGranja();
		animalValido.setPeso(500d);
		
		Errors errors = new BeanPropertyBindingResult(animalValido, "animal");
		
		validador.validate(animalValido, errors);
		
		assertThat(errors.hasErrors()).isEqualTo(false);
	}

}
