package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Medico;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
	
	@Query("SELECT m from Medico m  WHERE m.especialidade.id = ?1")
	List<Medico> findByEspecialidade(Long especialidade_id);
	
	
	@Query("SELECT m from Medico m WHERE upper(m.nome) like %?1% ")
	List<Medico> findByNome(String nome);
	
	@Query("SELECT m from Medico m  WHERE m.crm = ?1")
	List<Medico> findByCRM(String crm);
	
	
	@Query("SELECT m from Medico m  WHERE m.especialidade.nome = ?1")
	List<Medico> findByNomeEspecialidade(String nome);
	

}
