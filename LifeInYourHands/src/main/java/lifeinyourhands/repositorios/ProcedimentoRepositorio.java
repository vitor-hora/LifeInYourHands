package lifeinyourhands.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import lifeinyourhands.modelos.Procedimento;

public interface ProcedimentoRepositorio extends JpaRepository<Procedimento, Long> {

}
