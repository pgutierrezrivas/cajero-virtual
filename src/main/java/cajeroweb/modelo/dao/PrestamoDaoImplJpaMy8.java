package cajeroweb.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.repository.PrestamoRepository;

@Repository
public class PrestamoDaoImplJpaMy8 implements PrestamoDao{

	@Autowired
	private PrestamoRepository prepo;
	
	@Override
	public List<Prestamo> buscarPorCuenta(int idCuenta) { //buscar prestamos por cuenta
		return prepo.findByPrestamosPorCuenta(idCuenta);
	}

	@Override
	public Prestamo altaPrestamo(Prestamo prestamo) {
		try{
			return prepo.save(prestamo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int eliminarPrestamo(int idPrestamo) {
		try {
			if(prepo.existsById(idPrestamo)) {
				prepo.deleteById(idPrestamo);
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
