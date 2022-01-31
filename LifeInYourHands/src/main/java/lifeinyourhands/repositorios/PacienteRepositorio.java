package lifeinyourhands.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import lifeinyourhands.modelos.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

}
