package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Medico;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
	
	//List<Medico> findByEspecialidade(Especialidade especialidade);
	
	@Query("SELECT m from Medico m  WHERE m.especialidade.id = ?1")
	List<Medico> findByEspecialidade(Long especialidade_id);
	
}
