package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.GradeHorario;

public interface GradeHorarioRepositorio extends JpaRepository<GradeHorario, Long> {
	//Somente horários possíveis e disponíveis 
	@Query("SELECT grd from GradeHorario grd  WHERE grd.medico.id = ?1 and grd.dataDeAtendimento > SYSDATE() ")
	List<GradeHorario> findByMedico(Long medico_id);
	
}
