package lifeinyourhands.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.FichaGeral;

public interface FichaGeralRepositorio extends JpaRepository<FichaGeral, Long> {
	@Query("SELECT fg from FichaGeral fg WHERE fg.paciente.id = ?1")
	FichaGeral findByPaciente(Long paciente_id);
}
