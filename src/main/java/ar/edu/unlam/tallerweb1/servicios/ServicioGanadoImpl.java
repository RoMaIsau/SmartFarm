package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Tratamiento;
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
	public void guardarHC(HistoriaClinica hc) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardarHC(hc);
		
	}

	@Override
	public HistoriaClinica verHC(AnimalDeGranja animal) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.verHC(animal);
	}

	@Override
	public String diagnosticar(List<SignosVitales>signos,Sintomas sintomas) {
		// TODO Auto-generated method stub
		String enfermedad= "No hay suficientes sintomas de enfermedad";
		
		 Date Prueba= new Date(2020-01-01);
		 Date signosDate= new Date();
		 Date actual= new Date();
		int signosFiebreAftosa = 0;
		int signosLeptospirosis =0;
		int signosMiocardiopatia =0;
		int signosIntoxicacion = 0;
		int signosIBR = 0;
		
		if(sintomas.getTiempo().equals("Una semana")) {
	   int anio= actual.getYear();
	   int mes= actual.getMonth();
	   int dia= actual.getDay();
		int nuevoDia=(dia-7);	
      signosDate=new Date(anio-mes-nuevoDia);
	    
		}else if(sintomas.getTiempo().equals("Un mes")) {
			
			  int anio= actual.getYear();
			   int mes= actual.getMonth();
			   int dia= actual.getDay();
				int nuevomes=(mes-1);	
		      signosDate=new Date(anio-nuevomes-dia);
			
		}else if(sintomas.getTiempo().equals("Un año")) {
			
			  int anio= actual.getYear();
			   int mes= actual.getMonth();
			   int dia= actual.getDay();
				int nuevoanio=(anio-1);	
		      signosDate=new Date(nuevoanio-mes-dia);
			
		}
	   
		
		for(SignosVitales sv: signos) {
			
		
		if(sv.getTemperatura()> 40.0 && (sv.getFecha().after(Prueba))) {
			
			signosFiebreAftosa+=1;
			
		}
		
		if(sv.getTemperatura()> 39.0 && (sv.getFecha().after(Prueba))&& sv.getFrecuenciaCardiaca()> 70.0) {
		
			signosLeptospirosis+=1;
	} 
		if((sv.getTemperatura()> 39.0 && (sv.getFecha().after(Prueba)) && sv.getFrecuenciaCardiaca()< 60.0)) {
		
		signosIntoxicacion+=1;
		
	
   } 
		if((sv.getFecha().after(Prueba)) && sv.getFrecuenciaCardiaca()< 60.0) {
			
			signosMiocardiopatia+=1;
			
		
	   }
	if(sv.getFecha().after(Prueba)&& sv.getFrecuenciaRespiratoria()> 40.0 && sv.getTemperatura()> 39.0) {
			
			signosIBR+=1;
			
		
	   }


}
	if(signosFiebreAftosa > 3 && sintomas.isUlceras()== true && sintomas.isBajaProduccionLeche()==true ) 	
	{	enfermedad="Fiebre Aftosa";
	}
	if(signosLeptospirosis > 3 && sintomas.isDebilidad()== true && sintomas.isAnorexia()==true ) {	
		enfermedad="Leptospirosis";
	}
	if(signosMiocardiopatia > 3 && sintomas.isDebilidad()==true) {	
		enfermedad="Miocardiopatia congenita";
	}
	if(signosIntoxicacion > 3 && sintomas.isDiarrea() && sintomas.isSalivacionEspumosa()) {	
		enfermedad="Intoxicacion por consumo de plantas toxicas";
	}
	if(signosIBR > 3 && sintomas.isTos()== true && sintomas.isSecrecionNasal()== true) {	
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
		
	}

	@Override
	public List<SignosVitales> signos(HistoriaClinica h) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.signos(h);
	}

	@Override
	public void guardarEnfermedad(Enfermedad enfermedad) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardarEnfermedad(enfermedad);
		
	}

	@Override
	public SignosVitales signosFecha(HistoriaClinica hc) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.signosFecha(hc);
	}

	

@Override
public Boolean alarmaSV(SignosVitales signos) {
	
	
	if(signos.getTemperatura()> 40.0 || signos.getTemperatura()< 37.0 || 
	signos.getFrecuenciaCardiaca()> 70.0 || signos.getFrecuenciaCardiaca()< 60.0 
	|| signos.getFrecuenciaRespiratoria()> 40.0 || signos.getFrecuenciaRespiratoria()< 20.0 
	||signos.getPulso()> 130.0 || signos.getPulso()< 70.0) {
	
  return true;


}else {




return false;
}}

@Override
public List<Enfermedad> enfermedadesComunes(HistoriaClinica hc) {
	// TODO Auto-generated method stub
	return servicioGanadoDao.enfermedadesComunes(hc);
}

@Override
public List<Enfermedad> enfermedadesComunesRanking(List<Enfermedad> enfermedades) {
	// TODO Auto-generated method stub
	Collections.sort(enfermedades);
	
	return enfermedades;
}

@Override
public List<Enfermedad> todasEnfermedades() {
	// TODO Auto-generated method stub
	return servicioGanadoDao.todasEnfermedades();
}

@Override
public String tratamientoA(String enfermedad) {
	// TODO Auto-generated method stub
	String tratamiento = "";
	switch (enfermedad) {
	case "Miocardiopatia congenita":
		tratamiento="Cardiosina";
		break;
	case "Fiebre Aftosa":
		tratamiento="Gotas aftosicas";
		break;
	case "Leptospirosis":
		tratamiento="Leptospirina";
		break;
	case "Rinotraqueitis infecciosa":
		tratamiento="Nebulizaciones";
		break;
	case "Intoxicacion por consumo de plantas toxicas":
		tratamiento="Buscapina";
	break;
	default: tratamiento="No se conoce un tratamiento";
	break;
		
	}
		
	
	return tratamiento;
}

@Override
public Enfermedad buscarEnfermedad(Long id) {
	
	return servicioGanadoDao.buscarEnfermedad(id);
}

@Override
public void updateEnfermedad(Enfermedad e) {
	// TODO Auto-generated method stub
	
	servicioGanadoDao.updateEnfermedad(e);
	
}

@Override
public boolean alarmaTratamientoA(HistoriaClinica historia) {
	// TODO Auto-generated method stub
	List<Enfermedad>enfermedades=servicioGanadoDao.enfermedadesComunes(historia);
	Enfermedad enfermedad=null;
	
	for(Enfermedad e: enfermedades) {
		if(e.getFinTratamiento()== null && e.getInicioTratamiento() != null) {
			
			enfermedad=e;
		}
		
	
	}
	
	
	Date actual= new Date();
	int dia=(actual.getDay()-4);
	int mes= actual.getMonth();
	int anio= actual.getYear();
	
	Date prueba=new Date(anio-mes-dia);
	//(Hacer de cuenta q pasaron cuatro dias):
	if(enfermedad != null &&(prueba.before(enfermedad.getInicioTratamiento()))){
		return true;
	}else {
		return false;
	}
	
}

@Override
public Enfermedad tipoTratamientoA(HistoriaClinica historia) {
	// TODO Auto-generated method stub
	List<Enfermedad>enfermedades=servicioGanadoDao.enfermedadesComunes(historia);
	
	Enfermedad A= null;
	
	for(Enfermedad e: enfermedades) {
		
  if(e.getTratamientoA() != null && e.getTratamientoA().equals("Iniciado")) {
			
			A=e;
		}

	
	}
	

	
		return A;
	
	
}

@Override
public String tratamientoB(String enfermedad) {
	// TODO Auto-generated method stub
		String tratamiento = "";
		switch (enfermedad) {
		case "Miocardiopatia congenita":
			tratamiento="Cirugia";
			break;
		case "Fiebre Aftosa":
			tratamiento="Fiebrol";
			break;
		case "Leptospirosis":
			tratamiento="Vitamina D";
			break;
		case "Rinotraqueitis infecciosa":
			tratamiento="Anticuerpos";
			break;
		case "Intoxicacion por consumo de plantas toxicas":
			tratamiento="Hepatalgina";
			break;
	   default : tratamiento="No hay un tratamiento conocido";
		break;
			
		}
			
		
		return tratamiento;
}

@Override
public HistoriaClinica verHC(Long id) {
	// TODO Auto-generated method stub
	return servicioGanadoDao.verHC(id);
}

@Override
public Tratamiento buscarTratamiento(String nombre) {
	// TODO Auto-generated method stub
	return servicioGanadoDao.verTratamiento(nombre);
}

@Override
public Enfermedad tipoTratamientoB(HistoriaClinica historia) {
	List<Enfermedad>enfermedades=servicioGanadoDao.enfermedadesComunes(historia);
	
	Enfermedad B= null;
	
	for(Enfermedad e: enfermedades) {
		
if(e.getTratamientoB() != null && e.getTratamientoB().equals("Iniciado")) {
			
			B=e;
		}

	
	}
	

	
		return B;
	
}

}
