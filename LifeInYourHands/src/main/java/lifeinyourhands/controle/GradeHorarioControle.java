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

import lifeinyourhands.modelos.Especialidade;
import lifeinyourhands.modelos.GradeHorario;
import lifeinyourhands.modelos.Medico;
import lifeinyourhands.repositorios.EspecialidadeRepositorio;
import lifeinyourhands.repositorios.GradeHorarioRepositorio;
import lifeinyourhands.repositorios.MedicoRepositorio;

@Controller
public class GradeHorarioControle {
	
	@Autowired
	private GradeHorarioRepositorio gradeHorarioRepositorio;
	@Autowired
	private MedicoRepositorio medicoRepositorio;
	@Autowired
	private EspecialidadeRepositorio especialidadeRepositorio;
	
	@GetMapping("/administrativo/grade_horarios/cadastrar")
	public ModelAndView cadastrar(GradeHorario gradeHorario) {
		ModelAndView mv =  new ModelAndView("administrativo/grade_horarios/cadastro");
		
		mv.addObject("gradeHorario",gradeHorario);
		
		
		
		if(gradeHorario == null || gradeHorario.getId() == null) {
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
		}else{
			List<Especialidade> listaEspecialidades =  new ArrayList<Especialidade>();
			listaEspecialidades.add(0, gradeHorario.getMedico().getEspecialidade());
			mv.addObject("listaEspecialidades", listaEspecialidades);
			
			List<Medico> listaMedicos = new ArrayList<Medico>();
			listaMedicos.add(0, gradeHorario.getMedico());
			mv.addObject("listaMedicos", listaMedicos);
		}

		

		return mv;
	}
	
	@GetMapping("/administrativo/grade_horarios/listar")
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/grade_horarios/lista");
		mv.addObject("gradeHorarios", gradeHorarioRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/grade_horarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<GradeHorario> gradeHorario = gradeHorarioRepositorio.findById(id);
		return cadastrar(gradeHorario.get());
	}
	
	@GetMapping("/administrativo/grade_horarios/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<GradeHorario> gradeHorario = gradeHorarioRepositorio.findById(id);
		gradeHorarioRepositorio.delete(gradeHorario.get());
		return listar();
	}
	
	@PostMapping("/administrativo/grade_horarios/salvar")
	public ModelAndView salvar(@Valid GradeHorario gradeHorario, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(gradeHorario);
		}
		

		SimpleDateFormat fmtDataCompleta = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date dataCompleta = fmtDataCompleta.parse(gradeHorario.getDataAtendimentoStr().replace("T", " "));
			gradeHorario.setDataDeAtendimento(dataCompleta);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gradeHorarioRepositorio.saveAndFlush(gradeHorario);
		
		return cadastrar(new GradeHorario());
	}
	
	@PostMapping("/administrativo/grade_horarios/preencher_medicos_grd")
	@ResponseBody
	public ResponseEntity<List<Medico>> preencherMedicosByEspecialidade(@RequestBody Object idEspecialidade) {
		String[] idEspecialidadeString = idEspecialidade.toString().split("=");
		Long idEspecialidadeLong = Long.parseLong(idEspecialidadeString[1].replace("}", ""));
		List<Medico> listaMedicos = medicoRepositorio.findByEspecialidade(idEspecialidadeLong);
		
		return new ResponseEntity<List<Medico>>(listaMedicos, HttpStatus.CREATED);
	}

}
