package lifeinyourhands.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	
	@GetMapping("/administrativo/pacientes/buscar/nome")
	@ResponseBody
	public ModelAndView buscarPorNome(@RequestParam String nome) {		
		ModelAndView mv=new ModelAndView("administrativo/pacientes/lista");
		mv.addObject("listaPacientes", pacienteRepositorio.findByNome(
				(nome != null) ? nome.toUpperCase() : nome
				));
		return mv;
	}
	
	@GetMapping("/administrativo/pacientes/buscar/cpf")
	@ResponseBody
	public ModelAndView buscarPorCPF(@RequestParam String cpf) {		
		ModelAndView mv=new ModelAndView("administrativo/pacientes/lista");
		mv.addObject("listaPacientes", pacienteRepositorio.findByCPF(cpf));
		return mv;
	}
	
	@GetMapping("/administrativo/pacientes/buscar/dataNasc")
	@ResponseBody
	public ModelAndView buscarPorDataNasc(@RequestParam String data1, @RequestParam String data2) {		
		ModelAndView mv=new ModelAndView("administrativo/pacientes/lista");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mv.addObject("listaPacientes", pacienteRepositorio.findByDataNasc(fmt.parse(data1), fmt.parse(data2)));
		} catch (Exception e) {
			mv.addObject("listaPacientes", pacienteRepositorio.findByDataNasc(null, null));
		}
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
		paciente.setSenha(new BCryptPasswordEncoder().encode(paciente.getSenha()));
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
