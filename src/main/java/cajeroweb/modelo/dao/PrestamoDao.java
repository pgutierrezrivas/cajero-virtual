package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Prestamo;

public interface PrestamoDao {
	
	List<Prestamo> buscarPorCuenta(int idCuenta); //lista prestamos de una cuenta
	
	Prestamo altaPrestamo (Prestamo prestamo);

	int eliminarPrestamo (int idPrestamo);

}
