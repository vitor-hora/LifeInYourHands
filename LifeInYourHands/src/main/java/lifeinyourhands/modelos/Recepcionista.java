package lifeinyourhands.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "recepcionista")
public class Recepcionista extends Usuario implements Serializable {

	public Recepcionista() {
		super();
	}
	
	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Recepcionista [nome=" + nome + "]";
	}
	
}
