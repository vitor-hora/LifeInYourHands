package lifeinyourhands.controle;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lifeinyourhands.modelos.ContatoEmergencia;
import lifeinyourhands.modelos.Endereco;
import lifeinyourhands.modelos.Paciente;
import lifeinyourhands.repositorios.ContatoEmergenciaRepositorio;
import lifeinyourhands.repositorios.EnderecoRepositorio;
import lifeinyourhands.repositorios.PacienteRepositorio;


@Controller
public class PacienteControle {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;	
	@Autowired
	private ContatoEmergenciaRepositorio contatoEmergenciaRepositorio;
	
	@GetMapping("/administrativo/pacientes/cadastrar")
	public ModelAndView cadastrar(Paciente paciente) {
		ModelAndView mv =  new ModelAndView("administrativo/pacientes/cadastro");		
		mv.addObject("paciente",paciente);
		return mv;
	}
	
	@GetMapping("/administrativo/pacientes/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/pacientes/lista");
		mv.addObject("listaPacientes", pacienteRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/pacientes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Paciente> paciente = pacienteRepositorio.findById(id);
		return cadastrar(paciente.get());
	}
	
	@GetMapping("/administrativo/pacientes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Paciente> paciente = pacienteRepositorio.findById(id);
		pacienteRepositorio.delete(paciente.get());
		return listar();
	}
	
	@PostMapping("/administrativo/pacientes/salvar")
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(paciente);
		}
	//	Paciente.setSenha(new BCryptPasswordEncoder().encode(Paciente.getSenha()));
		try {
			Endereco endCadastrado = enderecoRepositorio.saveAndFlush(paciente.getEndereco());
			paciente.setEndereco(endCadastrado);
			ContatoEmergencia ceCadastrado = contatoEmergenciaRepositorio.saveAndFlush(paciente.getContatoEmergencia());
			paciente.setContatoEmergencia(ceCadastrado);
			pacienteRepositorio.saveAndFlush(paciente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Paciente());
	}

}
