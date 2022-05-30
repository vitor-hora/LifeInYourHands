package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "medicamento")
@SQLDelete(sql = "update Medicamento set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public class Medicamento implements Serializable{
	
	public Medicamento() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean ativo = true;

	private String nome;

	private String dosagem;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date periodoInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date periodoFim;
	
	private String intervalo;

	//private String unidadeMedidaDosagem;
	
	@ManyToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;



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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date periodoFim) {
		this.periodoFim = periodoFim;
	}

	@Override
	public String toString() {
		return "Medicamento [id=" + id + ", nome=" + nome + ", dosagem=" + dosagem + ", intervalo=" + intervalo
				+ ", periodoInicio=" + periodoInicio + ", periodoFim=" + periodoFim + ", consulta=" + consulta + "]";
	}


	
}
