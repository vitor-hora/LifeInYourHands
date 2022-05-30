package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Procedimento;

public interface ProcedimentoRepositorio extends JpaRepository<Procedimento, Long> {
	@Query("SELECT p from Procedimento p WHERE p.consulta.id = ?1")
	List<Procedimento> findByConsulta(Long consulta_id);
}
