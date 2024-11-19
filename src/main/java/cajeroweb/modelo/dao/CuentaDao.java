package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Cuenta;

public interface CuentaDao {

	Cuenta buscarUno(int idCuenta);

	void modificarSaldo(Cuenta cuenta);

}