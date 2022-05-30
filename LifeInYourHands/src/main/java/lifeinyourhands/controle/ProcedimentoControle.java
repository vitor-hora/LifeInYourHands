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

import lifeinyourhands.modelos.Consulta;
import lifeinyourhands.modelos.Exame;
import lifeinyourhands.modelos.Procedimento;
import lifeinyourhands.repositorios.ConsultaRepositorio;
import lifeinyourhands.repositorios.ProcedimentoRepositorio;


@Controller
public class ProcedimentoControle {
	
	@Autowired
	private ProcedimentoRepositorio procedimentoRepositorio;
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
		
	@GetMapping("/administrativo/procedimentos/cadastrar/{idConsulta}")
	public ModelAndView cadastrar(Procedimento procedimento, @PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv =  new ModelAndView("administrativo/procedimentos/cadastro");		
		
		Consulta consulta = consultaRepositorio.findById(idConsulta).get();
		procedimento.setConsulta(consulta);
	
		mv.addObject("idConsulta", idConsulta);
		mv.addObject("procedimento",procedimento);
		return mv;
	}
	
	@GetMapping("/administrativo/procedimentos/listar/{idConsulta}")
	public ModelAndView listar(@PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv=new ModelAndView("administrativo/procedimentos/lista");	
		if(idConsulta != null) {			
			List<Procedimento> listaProcedimentos = procedimentoRepositorio.findByConsulta(idConsulta);
			mv.addObject("idConsulta", idConsulta);
			mv.addObject("listaProcedimentos", listaProcedimentos);		
		
		}	
		return mv;
	}
	
	@GetMapping("/administrativo/procedimentos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Procedimento> procedimento = procedimentoRepositorio.findById(id);
		return cadastrar(procedimento.get(), procedimento.get().getConsulta().getId());
	}
	
	@GetMapping("/administrativo/procedimentos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Procedimento> procedimento = procedimentoRepositorio.findById(id);
		procedimentoRepositorio.delete(procedimento.get());
		return listar(procedimento.get().getConsulta().getId());
	}
	
	@PostMapping("/administrativo/procedimentos/salvar")
	public ModelAndView salvar(@Valid Procedimento procedimento, BindingResult result) {
		if(result.hasErrors()) {			
			return cadastrar(procedimento, procedimento.getConsulta().getId());
		}
		try {
			
			Consulta consulta =  (consultaRepositorio.findById(procedimento.getConsulta().getId())).get();
			
			Procedimento procedimentoCadastrado = procedimentoRepositorio.saveAndFlush(procedimento);			
			
			consulta.getProcedimentosReceitados().add(procedimentoCadastrado);
			consultaRepositorio.saveAndFlush(consulta);
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Procedimento(), procedimento.getConsulta().getId());
	}

}
