package lifeinyourhands.controle;

import java.text.SimpleDateFormat;
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
import lifeinyourhands.modelos.Especialidade;
import lifeinyourhands.modelos.Exame;
import lifeinyourhands.modelos.GradeHorario;
import lifeinyourhands.modelos.Medicamento;
import lifeinyourhands.modelos.Medico;
import lifeinyourhands.modelos.Tipo;
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
		
	@GetMapping("/administrativo/exames/cadastrar/{idConsulta}")
	public ModelAndView cadastrar(@PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv =  new ModelAndView("administrativo/exames/cadastro");		
		
		Consulta consulta = consultaRepositorio.findById(idConsulta).get();
		Exame exame = new Exame();
		exame.setConsulta(consulta);
		//
		mv.addObject("idConsulta", idConsulta);
		mv.addObject("listaTipos", tipoRepositorio.findAll());
		mv.addObject("exame",exame);
		return mv;
	}
	
	@GetMapping("/administrativo/exames/listar/{idConsulta}")
	public ModelAndView listar(@PathVariable("idConsulta") Long idConsulta) {
		ModelAndView mv=new ModelAndView("administrativo/exames/lista");	
		if(idConsulta != null) {			
			List<Exame> listaExames = exameRepositorio.findByConsulta(idConsulta);
			mv.addObject("idConsulta", idConsulta);
			mv.addObject("listaExames", listaExames);		
		
		}	
		return mv;
	}
	/*
	@GetMapping("/administrativo/consultas/buscarByConsulta/{idConsulta}")
	public ModelAndView buscarByPaciente(@PathVariable("idPaciente") Long idPaciente) {
		ModelAndView mv=new ModelAndView("administrativo/consultas/lista");
		mv.addObject("listaConsultas", consultaRepositorio.findByPaciente(idPaciente));
		mv.addObject("idPaciente", idPaciente);
		return mv;
	}*/
	
	@GetMapping("/administrativo/exames/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Exame> exame = exameRepositorio.findById(id);
		return cadastrar(exame.get().getConsulta().getId());
	}
	
	@GetMapping("/administrativo/exames/editarExame/{id}")
	public ModelAndView editarExame(@PathVariable("id") Long id) {
		
		ModelAndView mv =  new ModelAndView("administrativo/exames/edicao");
		
		Exame exame = exameRepositorio.findById(id).get();	
		
		List<Tipo> listaTipos = tipoRepositorio.findAll();
		listaTipos.remove(exame.getTipo());
		listaTipos.add(0, exame.getTipo());
		mv.addObject("listaTipos", listaTipos);
	
	
		mv.addObject("idConsulta", exame.getConsulta().getId());
		//SimpleDateFormat fmtDataCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		//String dataCompleta = fmtDataCompleta.format(consulta.getDataConsulta());
		//consulta.setDataConsultaStr(dataCompleta);
		mv.addObject("exame",exame);
		return mv;
	}
	
	@GetMapping("/administrativo/exames/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Exame> exame = exameRepositorio.findById(id);
		exameRepositorio.delete(exame.get());
		return listar(exame.get().getConsulta().getId());
	}
	
	@PostMapping("/administrativo/exames/salvar")
	public ModelAndView salvar(@Valid Exame exame, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(exame.getConsulta().getId());
		}
		Exame exameCadastrado = new Exame();
		try {
			
			Consulta consulta =  (consultaRepositorio.findById(exame.getConsulta().getId())).get();
			
			exameCadastrado = exameRepositorio.saveAndFlush(exame);			
			
			consulta.getExamesRealizados().add(exameCadastrado);
			consultaRepositorio.saveAndFlush(consulta);
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cadastrar(exameCadastrado.getConsulta().getId());
	}

}
