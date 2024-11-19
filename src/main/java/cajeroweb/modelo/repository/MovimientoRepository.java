package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> { //se pone en contacto con la bbdd
	
	@Query("select m from Movimiento m where m.cuenta.idCuenta = ?1")
	public List<Movimiento> findByMovimientosPorCuenta(int idCuenta); //buscamos movimientos por cuenta
	
}
