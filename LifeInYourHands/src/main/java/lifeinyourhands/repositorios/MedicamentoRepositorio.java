package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Medicamento;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Long> {
	@Query("SELECT m from Medicamento m WHERE m.consulta.id = ?1")
	List<Medicamento> findByConsulta(Long consulta_id);
}

