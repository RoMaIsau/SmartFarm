package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVacunas;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Vacunar;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;


// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioVacunas")
@Transactional
public class ServicioVacunaImpl implements ServicioVacunas {

	private RepositorioVacunas serviciovacunasDao;

	@Autowired
	public ServicioVacunaImpl(RepositorioVacunas serviciovacunasDao){
		this.serviciovacunasDao = serviciovacunasDao;
	}

	@Override
	public void vacunar(AnimalDeGranja animal, Vacuna vacuna) {
		// TODO Auto-generated method stub
		this.serviciovacunasDao.vacunar(animal, vacuna);
		
	}

	@Override
	public void guardar(Vacuna vacuna) {
		serviciovacunasDao.guardar(vacuna);
		
	}
	@Override
	public List<Vacuna> alarmaVacuna(AnimalDeGranja animal) {
	
			
			Calendar actual= Calendar.getInstance();
			actual.getTime();
			Calendar fechaNacimiento= Calendar.getInstance();
			 fechaNacimiento=animal.getFechaNacimiento();
			 int edadVacaMeses=0;
			 
			if (fechaNacimiento != null) {
			int años = actual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
			edadVacaMeses= (años*12)+actual.get(Calendar.MONTH)-fechaNacimiento.get(Calendar.MONTH);
			}
			
			List<Vacuna> vencidas= new ArrayList<Vacuna>();
			
			for(Vacuna v : animal.getVacunasParaAplicar()){
				Vacunar av= this.getAnimalVacuna(v, animal);
				if(av != null) {
			if(edadVacaMeses >= v.getEdadAplicacionMeses() && (av.getVacuna().getId() != v.getId()) ){
			         vencidas.add(v);
				}}else if(av == null){
					if(edadVacaMeses >= v.getEdadAplicacionMeses() ){
				         vencidas.add(v);}
				}else {
					vencidas = null;
				}
			}
				
			
		
			
		return vencidas ;
	}
	

	@Override
	public Vacuna getVacuna(String nombre) {
		// TODO Auto-generated method stub
		return serviciovacunasDao.getVacuna(nombre);
	}

	@Override
	public Vacunar getAnimalVacuna(Vacuna v, AnimalDeGranja a) {
		// TODO Auto-generated method stub
		return serviciovacunasDao.getAnimalVacuna(v,a);
	}

	

	

}
