package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "especialidade")
@SQLDelete(sql = "update Especialidade set ativo = 0 where id = ?") 
@Where(clause = "ativo = 1")
public class Especialidade implements Serializable {


	private static final long serialVersionUID = 1L;

	public Especialidade() {
		super();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Boolean ativo = true;
	
	private String nome;
	
	@OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL)
	private List<Medico> medicos;

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

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	@Override
	public String toString() {
		return "Especialidade [id=" + id + ", nome=" + nome + ", medicos=" + medicos + "]";
	}

	
}
