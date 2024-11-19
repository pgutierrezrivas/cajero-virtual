package cajeroweb.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.entidades.Movimiento;

@RestController //devuelve datos
@CrossOrigin (origins = "*") //cualquier origen de dato
@RequestMapping("/api/movimientos")
public class MovimientoRestController {

	@Autowired
	private MovimientoDao mdao;

	@GetMapping("/cuenta/{idCuenta}")
	public List<Movimiento> buscarPorCuenta(@PathVariable int idCuenta) {
		return mdao.buscarPorCuenta(idCuenta);
	}

}
