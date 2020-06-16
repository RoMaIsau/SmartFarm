package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;

@Service("servicioalimento")
@Transactional
public class ServicioAlimentoImpl implements ServicioAlimento {

	private RepositorioTipoAlimento repositorioTipoAlimento;
	private RepositorioAlimento repositorioAlimento;
	
	@Autowired
	public ServicioAlimentoImpl(RepositorioTipoAlimento repositorioTipoAlimento, RepositorioAlimento repositorioAlimento) {

		this.repositorioTipoAlimento = repositorioTipoAlimento;
		this.repositorioAlimento = repositorioAlimento;
	}

	@Override
	public List<TipoAlimento> obtenerTiposDeAlimentos() {
		return (List<TipoAlimento>) repositorioTipoAlimento.obtenerTiposDeAlimentos();
	}

	@Override
	public Long registrarAlimento(Alimento alimento) {
		return repositorioAlimento.registrarAlimento(alimento);
	}

	@Override
	public Alimento consultarAlimento(Alimento alimento) {
		return repositorioAlimento.consultarAlimento(alimento);
	}

	@Override
	public List<Alimento> listarAlimentos() {
		return repositorioAlimento.listarAlimentos();
	}

	@Override
	public Alimento consultarAlimentoPorId(Long id) {
		return repositorioAlimento.consultarAlimentoPorId(id);
	}

	@Override
	public void actualizarAlimento(Alimento alimento) {
		repositorioAlimento.actualizarAlimento(alimento);
	}

	@Override
	public Double consultarStock(Alimento alimento) {
		alimento = this.repositorioAlimento.consultarAlimento(alimento);
		return alimento.getCantidad() - alimento.getStockMinimo();
	}

	@Override
	public void decrementarStock(Alimento alimento, int cantidad) {
		alimento = this.repositorioAlimento.consultarAlimento(alimento);
		Double nuevoStock = alimento.getCantidad() - cantidad;
		alimento.setCantidad(nuevoStock);
		this.repositorioAlimento.actualizarAlimento(alimento);
	}

}
