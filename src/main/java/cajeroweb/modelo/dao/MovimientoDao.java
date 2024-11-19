package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoDao { //del Igenerico cojo el buscartodos
	
	List<Movimiento> buscarPorCuenta(int idCuenta); //lista movimientos de una cuenta
	
	Movimiento altaMovimiento (Movimiento movimiento);
}