package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Exame;

public interface ExameRepositorio extends JpaRepository<Exame, Long> {
	@Query("SELECT ex from Exame ex WHERE ex.consulta.id = ?1")
	List<Exame> findByConsulta(Long consulta_id);
}
