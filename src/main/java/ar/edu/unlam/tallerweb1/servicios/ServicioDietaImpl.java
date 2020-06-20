package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Dieta;


import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDieta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVacunas;

@Service("servicioDieta")
@Transactional
public class ServicioDietaImpl implements ServicioDieta {

	private RepositorioDieta servicioDietaDao;

	@Autowired
	public ServicioDietaImpl(RepositorioDieta servicioDietaDao){
		this.servicioDietaDao = servicioDietaDao;
	}

	@Override
	public void alimentar(AnimalDeGranja animal, Dieta dieta) {
		servicioDietaDao.alimentar(animal, dieta);
		
	}

	@Override
	public void guardarDieta(Dieta dieta) {
		// TODO Auto-generated method stub
		servicioDietaDao.guardarDieta(dieta);
		
	}
	
	@Override
	public List<Dieta> alarmaDieta(AnimalDeGranja animal) {
	
			
			Calendar actual= Calendar.getInstance();
			actual.getTime();
			
		
		Dieta dieta= servicioDietaDao.getDieta(animal);	 
		
		List<Dieta> pendientes= new ArrayList<Dieta>();	
			
			if(dieta != null && dieta.isPendiente()) {
			
			Calendar horario =dieta.getFechaYhora();
			
				if(actual.after(horario)){
			         pendientes.add(dieta);
				}else{
					pendientes = null;}
				}
		
			
		return pendientes ;
	}

	@Override
	public Dieta getDieta(String nombre) {
		// TODO Auto-generated method stub
		return servicioDietaDao.getDieta(nombre);
	}

	@Override
	public Dieta getDieta(AnimalDeGranja animal) {
		// TODO Auto-generated method stub
		return servicioDietaDao.getDieta(animal);
		
	}

	
	

}
