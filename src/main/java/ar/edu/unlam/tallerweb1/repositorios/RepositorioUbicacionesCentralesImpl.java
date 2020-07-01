package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.UbicacionesCentrales;

@Repository
@Transactional
public class RepositorioUbicacionesCentralesImpl implements RepositorioUbicacionesCentrales {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public UbicacionesCentrales obtenerUbicacionesCenrtales() {
		return (UbicacionesCentrales) sessionFactory.getCurrentSession().createCriteria(UbicacionesCentrales.class).uniqueResult();
	}
	
	
	
	@Override
	public void caprinoModificarCoordenadas(Double latitud, Double longitud) {
		UbicacionesCentrales ubicacionesCentrales = obtenerUbicacionesCenrtales();
		ubicacionesCentrales.setLatitudCaprinoCentral(-latitud);
		ubicacionesCentrales.setLongitudCaprinoCentral(-longitud);
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacionesCentrales);
	}

	@Override
	public void equinoModificarCoordenadas(Double latitud, Double longitud) {
		UbicacionesCentrales ubicacionesCentrales = obtenerUbicacionesCenrtales();
		ubicacionesCentrales.setLatitudEquinoCentral(-latitud);
		ubicacionesCentrales.setLongitudEquinoCentral(-longitud);
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacionesCentrales);
	}

	@Override
	public void ovinoModificarCoordenadas(Double latitud, Double longitud) {
		UbicacionesCentrales ubicacionesCentrales = obtenerUbicacionesCenrtales();
		ubicacionesCentrales.setLatitudOvinoCentral(-latitud);
		ubicacionesCentrales.setLongitudOvinoCentral(-longitud);
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacionesCentrales);
	}

	@Override
	public void porcinoModificarCoordenadas(Double latitud, Double longitud) {
		UbicacionesCentrales ubicacionesCentrales = obtenerUbicacionesCenrtales();
		ubicacionesCentrales.setLatitudPorcinoCentral(-latitud);
		ubicacionesCentrales.setLongitudPorcinoCentral(-longitud);
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacionesCentrales);
	}

	@Override
	public void vacunoModificarCoordenadas(Double latitud, Double longitud) {
		UbicacionesCentrales ubicacionesCentrales = obtenerUbicacionesCenrtales();
		ubicacionesCentrales.setLatitudVacunoCentral(-latitud);
		ubicacionesCentrales.setLongitudVacunoCentral(-longitud);
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacionesCentrales);
	}

}
