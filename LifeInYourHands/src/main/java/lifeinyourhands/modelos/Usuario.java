package lifeinyourhands.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SQLDelete(sql = "update Usuario set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public abstract class Usuario {

	public Usuario() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean ativo = true;
	
	private String email;

	private String senha;
	
	private String nome;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;


	private String getNome() {
		if(this instanceof Medico) {
			Medico med = (Medico) this;
			return med.getNome();		
		}else if(this instanceof Recepcionista) {
			Recepcionista rec = (Recepcionista) this;
			return rec.getNome();
		}else {
			getEmail();
		}
		
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	


}
