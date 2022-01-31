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

import lifeinyourhands.modelos.Especialidade;
import lifeinyourhands.repositorios.EspecialidadeRepositorio;

@Controller
public class EspecialidadeControle {
	
	@Autowired
	private EspecialidadeRepositorio especialidadeRepositorio;
		
	
	@GetMapping("/administrativo/especialidades/cadastrar")
	public ModelAndView cadastrar(Especialidade especialidade) {
		ModelAndView mv =  new ModelAndView("administrativo/especialidades/cadastro");
		mv.addObject("especialidade",especialidade);
		return mv;
	}
	
	@GetMapping("/administrativo/especialidades/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/especialidades/lista");
		mv.addObject("listaEspecialidades", especialidadeRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/especialidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Especialidade> especialidade = especialidadeRepositorio.findById(id);
		return cadastrar(especialidade.get());
	}
	
	@GetMapping("/administrativo/especialidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Especialidade> especialidade = especialidadeRepositorio.findById(id);
		especialidadeRepositorio.delete(especialidade.get());
		return listar();
	}
	
	@PostMapping("/administrativo/especialidades/salvar")
	public ModelAndView salvar(@Valid Especialidade especialidade, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(especialidade);
		}
		especialidadeRepositorio.saveAndFlush(especialidade);
		
		return cadastrar(new Especialidade());
	}

}
