package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Consulta;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {

	@Query("SELECT c from Consulta c WHERE c.paciente.id = ?1")
	List<Consulta> findByPaciente(Long paciente_id);
	
}
