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

import lifeinyourhands.modelos.Consulta;
import lifeinyourhands.modelos.Exame;
import lifeinyourhands.repositorios.ConsultaRepositorio;
import lifeinyourhands.repositorios.ExameRepositorio;
import lifeinyourhands.repositorios.TipoRepositorio;


@Controller
public class ExameControle {
	
	@Autowired
	private ExameRepositorio exameRepositorio;
	
	@Autowired
	private TipoRepositorio tipoRepositorio;
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
		
	@GetMapping("/administrativo/exames/cadastrar")
	public ModelAndView cadastrar(Exame exame) {
		ModelAndView mv =  new ModelAndView("administrativo/exames/cadastro");		
		
		//CORRIGIR
		Consulta consulta = consultaRepositorio.findAll().get(0);
		exame.setConsulta(consulta);
		//
		mv.addObject("listaTipos", tipoRepositorio.findAll());
		mv.addObject("exame",exame);
		return mv;
	}
	
	@GetMapping("/administrativo/exames/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/exames/lista");
		mv.addObject("listaExames", exameRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/exames/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Exame> exame = exameRepositorio.findById(id);
		return cadastrar(exame.get());
	}
	
	@GetMapping("/administrativo/exames/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Exame> exame = exameRepositorio.findById(id);
		exameRepositorio.delete(exame.get());
		return listar();
	}
	
	@PostMapping("/administrativo/exames/salvar")
	public ModelAndView salvar(@Valid Exame exame, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(exame);
		}
		try {
			
			Consulta consulta =  (consultaRepositorio.findById(exame.getConsulta().getId())).get();
			
			Exame exameCadastrado = exameRepositorio.saveAndFlush(exame);			
			
			consulta.getExamesRealizados().add(exameCadastrado);
			consultaRepositorio.saveAndFlush(consulta);
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Exame());
	}

}
