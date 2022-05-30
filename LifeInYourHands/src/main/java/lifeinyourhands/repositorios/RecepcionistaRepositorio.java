package lifeinyourhands.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifeinyourhands.modelos.Medico;
import lifeinyourhands.modelos.Recepcionista;

public interface RecepcionistaRepositorio extends JpaRepository<Recepcionista, Long> {
	
	
}
