package lifeinyourhands.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lifeinyourhands.modelos.Consulta;
import lifeinyourhands.modelos.Especialidade;
import lifeinyourhands.modelos.GradeHorario;
import lifeinyourhands.modelos.Medico;
import lifeinyourhands.modelos.Paciente;
import lifeinyourhands.repositorios.ConsultaRepositorio;
import lifeinyourhands.repositorios.EspecialidadeRepositorio;
import lifeinyourhands.repositorios.GradeHorarioRepositorio;
import lifeinyourhands.repositorios.MedicoRepositorio;
import lifeinyourhands.repositorios.PacienteRepositorio;

@Controller
public class ConsultaControle {
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
	
	@Autowired
	private MedicoRepositorio medicoRepositorio;
	@Autowired
	private EspecialidadeRepositorio especialidadeRepositorio;
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	@Autowired
	private GradeHorarioRepositorio gradeHorarioRepositorio;
		
	
	@GetMapping("/administrativo/consultas/cadastrar/{idPaciente}")
	public ModelAndView cadastrar(@PathVariable("idPaciente") Long idPaciente, Consulta consulta) {
		ModelAndView mv =  new ModelAndView("administrativo/consultas/cadastro");
			
			if(idPaciente != null) {
				Paciente paciente = pacienteRepositorio.findById(idPaciente).get();
				consulta.setPaciente(paciente);
			}
			
			List<Especialidade> listaEspecialidades =  especialidadeRepositorio.findAll();
			Especialidade espVazio = new Especialidade();
			espVazio.setNome("Selecione uma Especialidade");
			listaEspecialidades.add(0, espVazio);		
			mv.addObject("listaEspecialidades", listaEspecialidades);
			
			List<Medico> listaMedicos = new ArrayList<Medico>();
			Medico medVazio = new Medico();
			medVazio.setNome("Selecione uma Especialidade");
			listaMedicos.add(0, medVazio);		
			mv.addObject("listaMedicos", listaMedicos);
			
			List<GradeHorario> gradesHorario = new ArrayList<GradeHorario>();
			mv.addObject("gradesHorario", gradesHorario);
		
		

		mv.addObject("consulta",consulta);
		return mv;
	}
	
	
	@GetMapping("/administrativo/consultas/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		ModelAndView mv =  new ModelAndView("administrativo/consultas/edicao");
		
		Consulta consulta = consultaRepositorio.findById(id).get();		
	
		SimpleDateFormat fmtDataCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataCompleta = fmtDataCompleta.format(consulta.getDataConsulta());
		consulta.setDataConsultaStr(dataCompleta);
		
		mv.addObject("consulta",consulta);
		return mv;
	}
	
	@GetMapping("/administrativo/consultas/editarConsulta/{id}")
	public ModelAndView editarConsulta(@PathVariable("id") Long id) {
		
		ModelAndView mv =  new ModelAndView("administrativo/consultas/edicao");
		
		Consulta consulta = consultaRepositorio.findById(id).get();		
		
		List<Especialidade> listaEspecialidades =  especialidadeRepositorio.findAll();
		listaEspecialidades.remove(consulta.getMedico().getEspecialidade());	
		listaEspecialidades.add(0, consulta.getMedico().getEspecialidade());		
		mv.addObject("listaEspecialidades", listaEspecialidades);
		
		List<Medico> listaMedicos = medicoRepositorio.findByEspecialidade(consulta.getMedico().getEspecialidade().getId());
		listaMedicos.remove(consulta.getMedico());	
		listaMedicos.add(0, consulta.getMedico());		
		
		mv.addObject("listaMedicos", listaMedicos);
		
		List<GradeHorario> gradesHorario = gradeHorarioRepositorio.findByMedico(consulta.getMedico().getId());
		mv.addObject("gradesHorario", gradesHorario);
		
	
		SimpleDateFormat fmtDataCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataCompleta = fmtDataCompleta.format(consulta.getDataConsulta());
		consulta.setDataConsultaStr(dataCompleta);
		
		mv.addObject("consulta",consulta);
		return mv;
	}
	
	
	/*
	@GetMapping("/administrativo/consultas/cadastrar/{idPaciente}")
	public ModelAndView cadastrar(@PathVariable("idPaciente") Long idPaciente, Consulta consulta) {
		ModelAndView mv =  new ModelAndView("administrativo/consultas/cadastro");
		
		
		if(idPaciente == null && consulta.getPaciente()!= null ) {		
			List<Especialidade> listaEspecialidades =  especialidadeRepositorio.findAll();
			listaEspecialidades.remove(consulta.getMedico().getEspecialidade());	
			listaEspecialidades.add(0, consulta.getMedico().getEspecialidade());		
			mv.addObject("listaEspecialidades", listaEspecialidades);
			
			List<Medico> listaMedicos = medicoRepositorio.findByEspecialidade(consulta.getMedico().getEspecialidade().getId());
			listaMedicos.remove(consulta.getMedico());	
			listaMedicos.add(0, consulta.getMedico());		
			
			mv.addObject("listaMedicos", listaMedicos);
			
			List<GradeHorario> gradesHorario = gradeHorarioRepositorio.findByMedico(consulta.getMedico().getId());
			mv.addObject("gradesHorario", gradesHorario);
		
		}else {
			
			if(idPaciente != null) {
				Paciente paciente = pacienteRepositorio.findById(idPaciente).get();
				consulta.setPaciente(paciente);
			}
			
			List<Especialidade> listaEspecialidades =  especialidadeRepositorio.findAll();
			Especialidade espVazio = new Especialidade();
			espVazio.setNome("Selecione uma Especialidade");
			listaEspecialidades.add(0, espVazio);		
			mv.addObject("listaEspecialidades", listaEspecialidades);
			
			List<Medico> listaMedicos = new ArrayList<Medico>();
			Medico medVazio = new Medico();
			medVazio.setNome("Selecione uma Especialidade");
			listaMedicos.add(0, medVazio);		
			mv.addObject("listaMedicos", listaMedicos);
			
			List<GradeHorario> gradesHorario = new ArrayList<GradeHorario>();
			mv.addObject("gradesHorario", gradesHorario);
		}
		

		mv.addObject("consulta",consulta);
		return mv;
	}
	
	
	@GetMapping("/administrativo/consultas/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Consulta> consulta = consultaRepositorio.findById(id);		
		
		
		
		return cadastrar(null, consulta.get());
	}
	*/
	
	
	@GetMapping("/administrativo/consultas/buscarByPaciente/{idPaciente}")
	public ModelAndView buscarByPaciente(@PathVariable("idPaciente") Long idPaciente) {
		ModelAndView mv=new ModelAndView("administrativo/consultas/lista");
		mv.addObject("listaConsultas", consultaRepositorio.findByPaciente(idPaciente));
		mv.addObject("idPaciente", idPaciente);
		return mv;
	}

	
	@GetMapping("/administrativo/consultas/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/consultas/lista");
		mv.addObject("listaConsultas", consultaRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/consultas/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Consulta> consulta = consultaRepositorio.findById(id);
	//	Long idPaciente = consulta.get().getPaciente().getId();
		consultaRepositorio.delete(consulta.get());
		//return listar(idPaciente);
		return listar();
	}
	
	@PostMapping("/administrativo/consultas/salvar")
	public ModelAndView salvar(@Valid Consulta consulta, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(consulta.getPaciente().getId(), consulta);
		}
		SimpleDateFormat fmtDataCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			Date dataCompleta = fmtDataCompleta.parse(consulta.getDataConsultaStr());
			consulta.setDataConsulta(dataCompleta);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Para evitar erro transient instance
		Paciente p = pacienteRepositorio.findById(consulta.getPaciente().getId()).get();
		Medico m = medicoRepositorio.findById(consulta.getMedico().getId()).get();
		consulta.setPaciente(p);
		consulta.setMedico(m);
		//
		consultaRepositorio.saveAndFlush(consulta);
		
		return cadastrar(consulta.getPaciente().getId(), new Consulta());
	}
	
	
	
	@PostMapping("/administrativo/consultas/preencher_medicos_consulta")
	@ResponseBody
	public ResponseEntity<List<Medico>> preencherMedicosByEspecialidade(@RequestBody Object idEspecialidade) {
		String[] idEspecialidadeString = idEspecialidade.toString().split("=");
		Long idEspecialidadeLong = Long.parseLong(idEspecialidadeString[1].replace("}", ""));
		List<Medico> listaMedicos = medicoRepositorio.findByEspecialidade(idEspecialidadeLong);
		
		return new ResponseEntity<List<Medico>>(listaMedicos, HttpStatus.CREATED);
	}
	
	@PostMapping("/administrativo/consultas/preencher_grd_consulta")
	@ResponseBody
	public ResponseEntity<List<GradeHorario>> preencherGrdByMedico(@RequestBody Object idMedico) {
		String[] idMedicoString = idMedico.toString().split("=");
		Long idMedicoLong = Long.parseLong(idMedicoString[1].replace("}", ""));
		List<GradeHorario> gradeHorarios = gradeHorarioRepositorio.findByMedico(idMedicoLong);
		
		return new ResponseEntity<List<GradeHorario>>(gradeHorarios, HttpStatus.CREATED);
	}

}
