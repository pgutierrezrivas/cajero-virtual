package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.entidades.Cuenta;
import jakarta.servlet.http.HttpSession;

@Controller //devuelve vistas
public class HomeController {
	
	@Autowired
	private CuentaDao cdao;
	
	@GetMapping({"", "/", "home"}) //menu principal
	public String inicio() {
		return "home";
	}

	@GetMapping("/login") //iniciar sesion
	public String mostrarFormLogin() {
		return "formLogin"; //devuelvo vista formulario de login
	}

	@PostMapping("/login")
	public String procesarFormLogin(@RequestParam int idCuenta, 
			HttpSession sesion, RedirectAttributes ratt) {

		Cuenta cuenta = cdao.buscarUno(idCuenta); //compruebo que la cuenta existe

		if(cuenta != null) { //verifico si la cuenta no es nula
			sesion.setAttribute("cuenta", cuenta); //guardo el objeto cuenta entero en la sesion
			return "redirect:/"; //redirijo a la pagina de inicio si la cuenta es valida
		}else {
			ratt.addFlashAttribute("mensaje", "Cuenta no existe");
			return "redirect:/login"; //redirijo a la pagina de login si la cuenta no existe
		}
	}

	@GetMapping("/logout") //cerrar sesion
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("cuenta"); //elimino el objeto cuenta guardado en la sesion
		sesion.invalidate();
		return "forward:/login"; //y devuelvo a la vista de inicio de sesion
	}

}
