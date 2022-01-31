package lifeinyourhands.controle;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lifeinyourhands.modelos.Tipo;
import lifeinyourhands.repositorios.TipoRepositorio;

@Controller
public class TipoControle {
	
	@Autowired
	private TipoRepositorio tipoRepositorio;
		
	
	@GetMapping("/administrativo/tipos/cadastrar")
	public ModelAndView cadastrar(Tipo tipo) {
		ModelAndView mv =  new ModelAndView("administrativo/tipos/cadastro");
		mv.addObject("tipo",tipo);
		return mv;
	}
	
	@GetMapping("/administrativo/tipos/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/tipos/lista");
		mv.addObject("listaTipos", tipoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/tipos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Tipo> tipo = tipoRepositorio.findById(id);
		return cadastrar(tipo.get());
	}
	
	@GetMapping("/administrativo/tipos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Tipo> tipo = tipoRepositorio.findById(id);
		tipoRepositorio.delete(tipo.get());
		return listar();
	}
	
	@PostMapping("/administrativo/tipos/salvar")
	public ModelAndView salvar(@Valid Tipo tipo, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(tipo);
		}
		tipoRepositorio.saveAndFlush(tipo);
		
		return cadastrar(new Tipo());
	}

}
