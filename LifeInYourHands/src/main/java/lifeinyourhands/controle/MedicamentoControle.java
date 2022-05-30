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
import lifeinyourhands.modelos.FichaGeral;
import lifeinyourhands.modelos.Medicamento;
import lifeinyourhands.repositorios.ConsultaRepositorio;
import lifeinyourhands.repositorios.MedicamentoRepositorio;


@Controller
public class MedicamentoControle {
	
	@Autowired
	private MedicamentoRepositorio medicamentoRepositorio;
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
		
	@GetMapping("/administrativo/medicamentos/cadastrar/{idConsulta}")
	public ModelAndView cadastrar(Medicamento medicamento, @PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv =  new ModelAndView("administrativo/medicamentos/cadastro");		
		
		Consulta consulta = consultaRepositorio.findById(idConsulta).get();
		medicamento.setConsulta(consulta);
		
		mv.addObject("idConsulta", idConsulta);
		mv.addObject("medicamento",medicamento);
		return mv;
	}
	
	@GetMapping("/administrativo/medicamentos/listar/{idConsulta}")
	public ModelAndView listar(@PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv=new ModelAndView("administrativo/medicamentos/lista");	
		if(idConsulta != null) {			
			List<Medicamento> listaMedicamentos = medicamentoRepositorio.findByConsulta(idConsulta);
			mv.addObject("idConsulta", idConsulta);
			mv.addObject("listaMedicamentos", listaMedicamentos);		
		
		}	
		return mv;
	}
	
	@GetMapping("/administrativo/medicamentos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Medicamento> medicamento = medicamentoRepositorio.findById(id);
		return cadastrar(medicamento.get(), medicamento.get().getConsulta().getId());
	}
	
	@GetMapping("/administrativo/medicamentos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Medicamento> medicamento = medicamentoRepositorio.findById(id);
		medicamentoRepositorio.delete(medicamento.get());
		return listar(medicamento.get().getConsulta().getId());
	}
	
	@PostMapping("/administrativo/medicamentos/salvar")
	public ModelAndView salvar(@Valid Medicamento medicamento, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(medicamento, medicamento.getConsulta().getId());
		}
		try {
			
			Consulta consulta =  (consultaRepositorio.findById(medicamento.getConsulta().getId())).get();
			
			Medicamento medicamentoCadastrado = medicamentoRepositorio.saveAndFlush(medicamento);			
			
			consulta.getMedicamentosReceitados().add(medicamentoCadastrado);
			consultaRepositorio.saveAndFlush(consulta);
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Medicamento(), medicamento.getConsulta().getId());
	}

}
