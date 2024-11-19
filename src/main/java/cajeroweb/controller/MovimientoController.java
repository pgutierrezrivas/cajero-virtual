package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoDao mdao;

	@Autowired
	private CuentaDao cdao;

	@GetMapping("/") //vista de la pagina con todos los movimientos
	public String verMovimientos(Model model, HttpSession sesion) {

		Integer idCuenta = ((Cuenta) sesion.getAttribute("cuenta")).getIdCuenta(); //recupero el idCuenta del objeto cuenta guardado en la sesion

		model.addAttribute("movimientos", mdao.buscarPorCuenta(idCuenta)); //muestro los movimientos de esa cuenta

		return "listadoMovimientos";
	}
	
	@GetMapping("/alta") //al pinchar en 'Ingresar/Extraer'
	public String procesarAltaMovimiento() {
		return "formAltaMovimiento"; //devuelvo el formulario donde el usuario ingresa la operacion y cantidad
	}

	@PostMapping("/alta") //al pinchar en el boton 'Realizar operacion' del formulario alta de movimiento
	public String procesarFormAltaMovimiento(@RequestParam String operacion, 
			@RequestParam double cantidad, HttpSession sesion, RedirectAttributes ratt) {

		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta"); //recupero el objeto cuenta entero guardado en la sesion

		String mensaje = ""; //para simplificar el codigo
		String dondeRedirigir = "redirect:/movimientos/"; //para que solo haya un punto de retorno y me lie menos

		if (cuenta != null) {
			try {
				Movimiento movimiento = Movimiento.crearMovimiento(cuenta, cantidad, operacion); //creo movimiento
	
				switch(operacion) {
					case "INGRESO": //si valor de la operacion es 'ingreso'
						cuenta.ingresar(cantidad); //uso metodo propio ingresar de la clase cuenta
						cdao.modificarSaldo(cuenta); //modifico el saldo de la cuenta
						mdao.altaMovimiento(movimiento); //creo el movimiento
						mensaje = "Ingreso realizado correctamente"; //muestro mensaje ok
						break;
					case "EXTRACCION": //si valor de la operacion es 'extraccion', igual que en ingreso pero uso metodo extraer
						if (cuenta.extraer(cantidad)) {
							cdao.modificarSaldo(cuenta);
		                    mdao.altaMovimiento(movimiento);
							mensaje = "Extracción realizada correctamente";
						} else { //si sale por aqui tenemos saldo insuficiente
							mensaje = "Saldo insuficiente"; //muestro mensaje
							dondeRedirigir = "redirect:/movimientos/alta"; //y me quedo en la pagina de alta
						}
						break;
					default: //si la operacion no coincide con ninguna de las anteriores (por si se manipula manualmente por ejemplo)
						mensaje = "Operación no válida";
						dondeRedirigir = "redirect:/movimientos/alta"; //me quedo en la pagina de alta
						break;
				}
			} catch (IllegalArgumentException e) {
		        mensaje = e.getMessage(); //capturo el mensaje de la excepcion
		        dondeRedirigir = "redirect:/movimientos/alta"; //y redirijo de nuevo al alta de movimiento
		    }
		} else {
			mensaje = "Cuenta no existe"; //si la cuenta no existe redirijo al login
			dondeRedirigir = "redirect:/login";
		}

		ratt.addFlashAttribute("mensaje", mensaje); //aqui defino el mensaje flash con la variable mensaje que cree al incio

		return dondeRedirigir;
	}

}
