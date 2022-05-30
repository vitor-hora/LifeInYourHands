package lifeinyourhands.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Medico;
import lifeinyourhands.modelos.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
	@Query("SELECT p from Paciente p  WHERE upper(p.nome) like %?1% ")
	List<Paciente> findByNome(String nome);
	
	@Query("SELECT p from Paciente p  WHERE p.cpf = ?1")
	List<Paciente> findByCPF(String CPF);
	
	@Query("SELECT p from Paciente p  WHERE p.dataNasc between ?1 and ?2")
	List<Paciente> findByDataNasc(Date data1, Date data2);
	
}
