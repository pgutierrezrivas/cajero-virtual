package cajeroweb.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.repository.CuentaRepository;

@Repository
public class CuentaDaoImplJpaMy8 implements CuentaDao {

	@Autowired
	private CuentaRepository crepo;

	@Override
	public Cuenta buscarUno(int idCuenta) {
		return crepo.findById(idCuenta).orElse(null);
	}

	@Override
	public void modificarSaldo(Cuenta cuenta) {
		try {
			Cuenta cuentaAntigua = buscarUno(cuenta.getIdCuenta());
			/*esto lo hago asi para que no se pueda modificar otros campos
			 * de cuenta cuando se llame a este metodo desde otros lugares*/

			cuentaAntigua.setSaldo(cuenta.getSaldo());
			crepo.save(cuentaAntigua);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
