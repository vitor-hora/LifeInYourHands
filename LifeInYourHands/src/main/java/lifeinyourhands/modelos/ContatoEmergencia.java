package lifeinyourhands.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contato_emergencia")
public class ContatoEmergencia implements Serializable{

	public ContatoEmergencia() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String grauParentesco;

	private String celular;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getGrauParentesco() {
		return grauParentesco;
	}



	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	@Override
	public String toString() {
		return "ContatoEmergencia [id=" + id + ", nome=" + nome + ", grauParentesco=" + grauParentesco + ", celular="
				+ celular + "]";
	}




	
	
}
