package lifeinyourhands.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import lifeinyourhands.modelos.Medicamento;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Long> {

}
