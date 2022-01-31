package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paciente")
public class Paciente extends Usuario implements Serializable {

	public Paciente() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	@OneToOne
	private ContatoEmergencia contatoEmergencia;
	
	@OneToOne
	private FichaGeral fichaGeral;

	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", cpf=" + cpf + ", rg=" + rg + ", nome=" + nome + ", sexo=" + sexo
				+ ", dataNasc=" + dataNasc + ", celular=" + celular + ", contatoEmergencia=" + contatoEmergencia
				+ ", fichaGeral=" + fichaGeral + ", endereco=" + endereco + "]";
	}

	








	
	
}
