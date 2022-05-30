package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medico")
@SQLDelete(sql = "update Medico set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public class Medico extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	public Medico() {
		super();
	}
	
	//private Boolean ativo = true;
	
	private String nome;

	private String sexo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	private String celular;

	private String cpf;

	private String rg;

	private String crm;
	
/*
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private Endereco endereco;
*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Consulta> consultas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<GradeHorario> gradeHorarios;

	
	/*
	 @JoinTable(name="medico_especialidade",
    joinColumns={@JoinColumn(name="especialidade_id",
     referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="medico_id",
     referencedColumnName="id")})
	 */
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;

	
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
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

/*
	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
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


	public String getCrm() {
		return crm;
	}


	public void setCrm(String crm) {
		this.crm = crm;
	}


	public List<Consulta> getConsultas() {
		return consultas;
	}


	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}


	public List<GradeHorario> getGradeHorarios() {
		return gradeHorarios;
	}


	public void setGradeHorarios(List<GradeHorario> gradeHorarios) {
		this.gradeHorarios = gradeHorarios;
	}


	public Especialidade getEspecialidade() {
		return especialidade;
	}


	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}


	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", sexo=" + sexo + ", dataNasc=" + dataNasc + ", celular=" + celular
				+ ", cpf=" + cpf + ", rg=" + rg + ", crm=" + crm + "]";
	}



	
	
	

	
	
}
