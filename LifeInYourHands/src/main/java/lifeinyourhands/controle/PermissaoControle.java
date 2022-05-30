package lifeinyourhands.controle;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lifeinyourhands.modelos.Medico;
import lifeinyourhands.modelos.Permissao;
import lifeinyourhands.modelos.Usuario;
import lifeinyourhands.repositorios.MedicoRepositorio;
import lifeinyourhands.repositorios.PapelRepositorio;
import lifeinyourhands.repositorios.PermissaoRepositorio;
import lifeinyourhands.repositorios.RecepcionistaRepositorio;
import lifeinyourhands.repositorios.UsuarioRepositorio;

@Controller
public class PermissaoControle {
	
	@Autowired
	private PermissaoRepositorio permissaoRepositorio;
	
	@Autowired
	private MedicoRepositorio medicoRepositorio;
	
	@Autowired
	private RecepcionistaRepositorio recepcionistaRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PapelRepositorio papelRepositorio;
	
	
	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv =  new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao",permissao);
		mv.addObject("listaUsuarios",usuarioRepositorio.findAll());
		mv.addObject("listaPapeis", papelRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/permissoes/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/permissoes/lista");
		
		List<Permissao> listaPermissoes =  permissaoRepositorio.findAll();
		
		mv.addObject("listaPermissoes", listaPermissoes);
		return mv;
	}
	
	@GetMapping("/administrativo/permissoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		return cadastrar(permissao.get());
	}
	
	@GetMapping("/administrativo/permissoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		permissaoRepositorio.delete(permissao.get());
		return listar();
	}
	
	@PostMapping("/administrativo/permissoes/salvar")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(permissao);
		}
		permissaoRepositorio.saveAndFlush(permissao);
		
		return cadastrar(new Permissao());
	}

}
