package lifeinyourhands.controle;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lifeinyourhands.modelos.Endereco;
import lifeinyourhands.modelos.Especialidade;
import lifeinyourhands.modelos.Medico;
import lifeinyourhands.repositorios.EnderecoRepositorio;
import lifeinyourhands.repositorios.EspecialidadeRepositorio;
import lifeinyourhands.repositorios.MedicoRepositorio;


@Controller
public class MedicoControle {
	
	@Autowired
	private MedicoRepositorio medicoRepositorio;
	@Autowired
	private EspecialidadeRepositorio especialidadeRepositorio;	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;	
	
	
	@GetMapping("/administrativo/medicos/cadastrar")
	public ModelAndView cadastrar(Medico medico) {
		ModelAndView mv =  new ModelAndView("administrativo/medicos/cadastro");		
		if(medico == null || medico.getId() == null) {
			mv.addObject("medico",medico);
			mv.addObject("listaEspecialidades", especialidadeRepositorio.findAll());
		}else {
			mv.addObject("medico",medico);
			List<Especialidade> listaEspecialidades = especialidadeRepositorio.findAll();
			listaEspecialidades.remove(medico.getEspecialidade());
			listaEspecialidades.add(0, medico.getEspecialidade());
			mv.addObject("listaEspecialidades", listaEspecialidades );
		}
		return mv;
	}
	
	@GetMapping("/administrativo/medicos/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/medicos/lista");
		mv.addObject("listaMedicos", medicoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/medicos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Medico> medico = medicoRepositorio.findById(id);
		return cadastrar(medico.get());
	}
	
	@GetMapping("/administrativo/medicos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Medico> medico = medicoRepositorio.findById(id);
		medicoRepositorio.delete(medico.get());
		return listar();
	}
	
	@PostMapping("/administrativo/medicos/salvar")
	public ModelAndView salvar(@Valid Medico medico, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(medico);
		}
		medico.setSenha(new BCryptPasswordEncoder().encode(medico.getSenha()));
		try {
			Endereco endCadastrado = enderecoRepositorio.saveAndFlush(medico.getEndereco());
			medico.setEndereco(endCadastrado);
			medicoRepositorio.saveAndFlush(medico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Medico());
	}

}
