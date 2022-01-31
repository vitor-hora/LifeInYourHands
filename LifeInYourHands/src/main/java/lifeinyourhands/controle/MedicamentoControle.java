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
import lifeinyourhands.modelos.Medicamento;
import lifeinyourhands.repositorios.ConsultaRepositorio;
import lifeinyourhands.repositorios.MedicamentoRepositorio;


@Controller
public class MedicamentoControle {
	
	@Autowired
	private MedicamentoRepositorio medicamentoRepositorio;
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
		
	@GetMapping("/administrativo/medicamentos/cadastrar")
	public ModelAndView cadastrar(Medicamento medicamento) {
		ModelAndView mv =  new ModelAndView("administrativo/medicamentos/cadastro");		
		
		//CORRIGIR
		Consulta consulta = consultaRepositorio.findAll().get(0);
		medicamento.setConsulta(consulta);
		//
		
		mv.addObject("medicamento",medicamento);
		return mv;
	}
	
	@GetMapping("/administrativo/medicamentos/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/medicamentos/lista");
		mv.addObject("listaMedicamentos", medicamentoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/medicamentos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Medicamento> medicamento = medicamentoRepositorio.findById(id);
		return cadastrar(medicamento.get());
	}
	
	@GetMapping("/administrativo/medicamentos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Medicamento> medicamento = medicamentoRepositorio.findById(id);
		medicamentoRepositorio.delete(medicamento.get());
		return listar();
	}
	
	@PostMapping("/administrativo/medicamentos/salvar")
	public ModelAndView salvar(@Valid Medicamento medicamento, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(medicamento);
		}
		try {
			
			Consulta consulta =  (consultaRepositorio.findById(medicamento.getConsulta().getId())).get();
			
			Medicamento medicamentoCadastrado = medicamentoRepositorio.saveAndFlush(medicamento);			
			
			consulta.getMedicamentosReceitados().add(medicamentoCadastrado);
			consultaRepositorio.saveAndFlush(consulta);
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Medicamento());
	}

}
