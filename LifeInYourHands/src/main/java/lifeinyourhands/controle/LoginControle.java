package lifeinyourhands.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControle {
	

	@GetMapping("/login")
	public String logar() {
		return "login";
	}
	
	@GetMapping("/")
	public String redireciona() {
		return logar();
	}
	
	

}
