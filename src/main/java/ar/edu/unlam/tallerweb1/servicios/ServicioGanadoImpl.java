package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioGanado")
@Transactional
public class ServicioGanadoImpl implements ServicioGanado {
	
	private RepositorioGanado servicioGanadoDao;

	@Autowired
	public ServicioGanadoImpl(RepositorioGanado servicioGanadoDao){
		this.servicioGanadoDao = servicioGanadoDao;
	}

	@Override
	public List<AnimalDeGranja> listar() {
		// TODO Auto-generated method stub
		return servicioGanadoDao.listar();
	}

	@Override
	public AnimalDeGranja ver(Long id) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.ver(id);
	}

	@Override
	public void guardar(AnimalDeGranja ganado) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardar(ganado);
		
	}

	

	@Override
	public void modificarSignos(SignosVitales signos) {
		// TODO Auto-generated method stub
		
		 servicioGanadoDao.modificarSignos(signos);
	}

	@Override
	public List<SignosVitales> alarmaSignos(AnimalDeGranja v) {
		
		SignosVitales signos= v.getSignos();
		SignosVitales temperatura= null;
		SignosVitales fc= null;
		SignosVitales fr= null;
		SignosVitales pulso= null;
		if(signos.getTemperatura()> 40.0 || signos.getTemperatura()< 37.0) {
		
			temperatura=signos;
		}if(signos.getFrecuenciaCardiaca()> 70.0 || signos.getFrecuenciaCardiaca()< 60.0) {
		
			fc=signos;
	} if(signos.getFrecuenciaRespiratoria()> 40.0 || signos.getFrecuenciaRespiratoria()< 20.0) {
		
		fr=signos;
		
	
   } if(signos.getPulso()> 130.0 || signos.getPulso()< 70.0) {
		
		pulso=signos;


}
   List<SignosVitales> anormales= new ArrayList<SignosVitales>();
   
	anormales.add(temperatura);
	anormales.add(fc);
	anormales.add(fr);
	anormales.add(pulso);
	
return anormales;
	}

		@Override
	public void guardarHC(HistoriaClinica hc) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardarHC(hc);
		
	}

	@Override
	public HistoriaClinica verHC(Long id) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.verHC(id);
	}

	@Override
	public String diagnosticar(HistoriaClinica hc,Sintomas sintomas) {
		// TODO Auto-generated method stub
		List<SignosVitales>signos= hc.getSignos();
		List<SignosVitales>signosFiebreAftosa = new ArrayList<SignosVitales>();
		List<SignosVitales>signosLeptospirosis = new ArrayList<SignosVitales>();
		List<SignosVitales>signosMiocardiopatia = new ArrayList<SignosVitales>();
		List<SignosVitales>signosIntoxicacion = new ArrayList<SignosVitales>();
		List<SignosVitales>signosIBR = new ArrayList<SignosVitales>();
		String enfermedad= "No hay suficientes sintomas de enfermedad";
	
		
		for(SignosVitales sv: signos) {
			
		
		if(sv.getTemperatura()> 40.0 && sv.getFecha().after(sintomas.getFechaSignosVitales())) {
			
			signosFiebreAftosa.add(sv);
		}
		
		if(sv.getTemperatura()> 39.0 && sv.getFecha().after(sintomas.getFechaSignosVitales())&& sv.getFrecuenciaCardiaca()> 70.0) {
		
			signosLeptospirosis.add(sv);
	} 
		if((sv.getTemperatura()> 39.0 && sv.getFecha().after(sintomas.getFechaSignosVitales())&& sv.getFrecuenciaCardiaca()< 60.0)) {
		
		signosIntoxicacion.add(sv);
		
	
   } 
		if(sv.getFecha().after(sintomas.getFechaSignosVitales())&& sv.getFrecuenciaCardiaca()< 60.0) {
			
			signosMiocardiopatia.add(sv);
			
		
	   }
	if(sv.getFecha().after(sintomas.getFechaSignosVitales())&& sv.getFrecuenciaRespiratoria()> 40.0 && sv.getTemperatura()> 39.0) {
			
			signosIBR.add(sv);
			
		
	   }


}
	if(signosFiebreAftosa.size() > 4 && sintomas.isUlceras()== true && sintomas.isBajaProduccionLeche()==true ) {	
		enfermedad="Fiebre Aftosa";
	}
	if(signosLeptospirosis.size() > 4 && sintomas.isDebilidad()== true && sintomas.isAnorexia()==true ) {	
		enfermedad="Leptospirosis";
	}
	if(signosMiocardiopatia.size() > 4 && sintomas.isDebilidad()==true) {	
		enfermedad="Miocardiopatia congenita";
	}
	if(signosIntoxicacion.size() > 4 && sintomas.isDiarrea() && sintomas.isSalivacionEspumosa()) {	
		enfermedad="Intoxicacion por consumo de plantas toxicas";
	}
	if(signosIBR.size() > 4 && sintomas.isTos()== true && sintomas.isSecrecionNasal()== true) {	
		enfermedad="Rinotraqueitis infecciosa";
	}
	
	
	
		
		return enfermedad;}

	@Override
	public void guardarSV(SignosVitales sv) {
		servicioGanadoDao.guardarSV(sv);
		
	}

	@Override
	public void guardarSintomas(Sintomas sintomas) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardar(sintomas);
		
	}}
