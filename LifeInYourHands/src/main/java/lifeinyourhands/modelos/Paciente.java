package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paciente")
@SQLDelete(sql = "update Paciente set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public class Paciente extends Usuario implements Serializable {

	public Paciente() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	//private Boolean ativo = true;
	
	private String cpf;

	private String rg;

	private String nome;

	private String sexo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	private String celular;
	
	//CORRIGIR
	//private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private ContatoEmergencia contatoEmergencia;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FichaGeral fichaGeral;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Consulta> consultas;
	/*
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private Endereco endereco;
	*/
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public ContatoEmergencia getContatoEmergencia() {
		return contatoEmergencia;
	}

	public void setContatoEmergencia(ContatoEmergencia contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}

	public FichaGeral getFichaGeral() {
		return fichaGeral;
	}

	public void setFichaGeral(FichaGeral fichaGeral) {
		this.fichaGeral = fichaGeral;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	/*
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	*/

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", rg=" + rg + ", nome=" + nome + ", sexo=" + sexo
				+ ", dataNasc=" + dataNasc + ", celular=" + celular + "]";
	}

}
