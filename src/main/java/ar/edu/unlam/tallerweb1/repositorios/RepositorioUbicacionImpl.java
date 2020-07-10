package ar.edu.unlam.tallerweb1.repositorios;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

@Repository("repositorioUbicacion")
@Transactional
public class RepositorioUbicacionImpl implements RepositorioUbicacion {
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioUbicacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardarUbicacion(Ubicacion ubicacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnimalUbicacion> obtenerUbicacionesRecientes() {

		String subtabla = 
				  "(SELECT animal_id as idAnimal, MAX(fecha) as fecha"
			    +"   FROM AnimalUbicacion"
			    +"   GROUP BY animal_id"	
				+"  ) as ultimas_posiciones";
		
		String query = "SELECT a.id, t.nombre, u.latitud, u.longitud, au.fecha"
				+ " FROM " + subtabla
				+ " JOIN AnimalUbicacion au ON au.fecha = ultimas_posiciones.fecha"
				+ " JOIN AnimalDeGranja a ON au.animal_id = a.id AND a.id = ultimas_posiciones.idAnimal"
				+ " JOIN TipoAnimal t ON t.id = a.tipo_id"
				+ " JOIN Ubicacion u ON u.id = au.ultimaubicacion_id";
		
		List<Object[]> registros =  sessionFactory.getCurrentSession()
				.createNativeQuery(query)
				.getResultList();

		List<AnimalUbicacion> ubicaciones = new LinkedList<>();
		for(Object[] registro : registros) {

			Long id = ((Number)registro[0]).longValue();
			String nombre = (String) registro[1];
			double latitud = (double) registro[2];
			double longitud = (double) registro[3];
			LocalDateTime fecha = ((Timestamp)registro[4]).toLocalDateTime();

			AnimalDeGranja animal = new AnimalDeGranja();
			animal.setId(id);
			TipoAnimal tipo = new TipoAnimal();
			tipo.setNombre(nombre);
			animal.setTipo(tipo);

			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setLatitud(latitud);
			ubicacion.setLongitud(longitud);

			AnimalUbicacion animalUbicacion = new AnimalUbicacion();
			animalUbicacion.setFecha(fecha);
			animalUbicacion.setAnimal(animal);
			animalUbicacion.setUltimaUbicacion(ubicacion);

			ubicaciones.add(animalUbicacion);
		}

		return ubicaciones;
	}
}
