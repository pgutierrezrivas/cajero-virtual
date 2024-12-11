package cajeroweb.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.PrestamoDao;
import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

	@Autowired
	private PrestamoDao pdao;

	@GetMapping("/") //vista de la pagina con todos los prestamos
	public String verPrestamos(Model model, HttpSession sesion) {

		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta"); //recupero el objeto cuenta guardado en la sesion
		//opcion2: Integer idCuenta = ((Cuenta) sesion.getAttribute("cuenta")).getIdCuenta(); //recupero el idCuenta del objeto cuenta guardado en la sesion

		model.addAttribute("prestamos", pdao.buscarPorCuenta(cuenta.getIdCuenta())); //muestro los prestamos de esa cuenta
		//opcion2: model.addAttribute("prestamos", pdao.buscarPorCuenta(idCuenta));

		return "prestamos";
	}

	@GetMapping("/altaPrestamo")
	public String procesarAltaPrestamo() {
		return "formAltaPrestamo"; //devuelvo el formulario donde el usuario ingresa el nuevoi prestamo que quiere dar de alta
	}

	@PostMapping("/altaPrestamo")
	public String procesarFormAltaPrestamo(Prestamo prestamo, HttpSession sesion, RedirectAttributes ratt) {
		/* En lugar de usar = @RequestParam String descripcion, @RequestParam double cantidadPrestamo, 
		@RequestParam double tasaInteresAnual, @RequestParam int plazoMeses, @RequestParam String tipoCuota, usamos = Prestamo prestamo*/

		prestamo.setCuenta((Cuenta) sesion.getAttribute("cuenta")); //al objeto prestamo le establecemos la cuenta de la sesion
		prestamo.setFechaPrestamo(new Date()); //al objeto prestamo le establecemos la fecha actual

		String mensaje = ""; //para simplificar el codigo

		if (pdao.altaPrestamo(prestamo) != null) {
			mensaje = "Alta préstamo realizada correctamente";
		} else {
			mensaje = "Alta préstamo no realizada";
		}
		ratt.addFlashAttribute("mensaje", mensaje); //aqui defino el mensaje flash con la variable mensaje que cree al incio
		return "redirect:/prestamos/";
	}
	
	@GetMapping("/eliminar/{idPrestamo}")
	public String eliminarPrestamo(@PathVariable int idPrestamo, Model model) {

		String mensaje = ""; //para simplificar el codigo

		if (pdao.eliminarPrestamo(idPrestamo) == 1) {
			mensaje = "Préstamo eliminado correctamente";
		} else {
			mensaje = "Préstamo no eliminado";
		}
		model.addAttribute("mensaje", mensaje); //aqui defino el mensaje con la variable mensaje que cree al incio
		return "forward:/prestamos/";
	}

}
