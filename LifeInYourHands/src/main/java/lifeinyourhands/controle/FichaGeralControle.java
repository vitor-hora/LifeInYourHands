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

import lifeinyourhands.modelos.FichaGeral;
import lifeinyourhands.modelos.Paciente;
import lifeinyourhands.repositorios.FichaGeralRepositorio;
import lifeinyourhands.repositorios.PacienteRepositorio;


@Controller
public class FichaGeralControle {
	
	@Autowired
	private FichaGeralRepositorio fichaGeralRepositorio;
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;

	
	@GetMapping("/administrativo/ficha_geral/cadastrar")
	public ModelAndView cadastrar(FichaGeral fichaGeral) {
		ModelAndView mv =  new ModelAndView("administrativo/ficha_geral/cadastro");		
		
		//CORRIGIR
		Paciente paciente = pacienteRepositorio.findAll().get(0);
		fichaGeral.setPaciente(paciente);
		//
		
		mv.addObject("fichaGeral",fichaGeral);
		return mv;
	}
	
	/*
	@GetMapping("/administrativo/ficha_geral/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/ficha_geral/lista");
		mv.addObject("listaFichaGerals", fichaGeralRepositorio.findAll());
		return mv;
	}
	*/
	
	@GetMapping("/administrativo/ficha_geral/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<FichaGeral> fichaGeral = fichaGeralRepositorio.findById(id);
		return cadastrar(fichaGeral.get());
	}
	
	/*
	@GetMapping("/administrativo/ficha_geral/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<FichaGeral> fichaGeral = fichaGeralRepositorio.findById(id);
		fichaGeralRepositorio.delete(fichaGeral.get());
		return listar();
	}
	*/
	
	@PostMapping("/administrativo/ficha_geral/salvar")
	public ModelAndView salvar(@Valid FichaGeral fichaGeral, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(fichaGeral);
		}
		try {
			
			Paciente paciente =   (pacienteRepositorio.findById(fichaGeral.getPaciente().getId())).get();
			
			FichaGeral fichaGeralCadastrada = fichaGeralRepositorio.saveAndFlush(fichaGeral);			
			
			paciente.setFichaGeral(fichaGeralCadastrada);
		    pacienteRepositorio.saveAndFlush(paciente);
		    
		    return cadastrar(fichaGeralCadastrada);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new FichaGeral());
	}

}
