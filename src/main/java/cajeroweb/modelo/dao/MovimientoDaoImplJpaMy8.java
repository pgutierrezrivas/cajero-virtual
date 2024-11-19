package cajeroweb.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Movimiento;
import cajeroweb.modelo.repository.MovimientoRepository;

@Repository
public class MovimientoDaoImplJpaMy8 implements MovimientoDao{
	
	@Autowired
	private MovimientoRepository mrepo;

	@Override
	public List<Movimiento> buscarPorCuenta(int idCuenta) { //buscar movimientos por cuenta
		return mrepo.findByMovimientosPorCuenta(idCuenta);
	}

	@Override
	public Movimiento altaMovimiento(Movimiento movimiento) {
		return mrepo.save(movimiento);
	}

}
