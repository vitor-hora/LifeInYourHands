package lifeinyourhands.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "grade_horario")
@SQLDelete(sql = "update grade_horario set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public class GradeHorario implements Serializable{
		
	public GradeHorario() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean ativo = true;
	
	private Boolean disponibilidade;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataDeAtendimento;

	@Transient
	private String dataAtendimentoStr;

	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDisponibilidade() {
		return disponibilidade;
	}
	
	public String getDisponibilidadeStr() {
		
		String disponibilidade;
		
		if(this.disponibilidade) {
			disponibilidade = "DISPONÍVEL";
		}else {
			disponibilidade = "INDISPONÍVEL";
		}
		return disponibilidade;
	}


	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Date getDataDeAtendimento() {
		return dataDeAtendimento;
	}

	public void setDataDeAtendimento(Date dataDeAtendimento) {
		this.dataDeAtendimento = dataDeAtendimento;
	}
	
	public String getDataAtendimentoStr() {
		if(this.dataDeAtendimento!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dataAtendimentoStr = sdf.format(this.dataDeAtendimento).replace(" ", "T");		
		}
		return dataAtendimentoStr;
	}

	public String dataFormatada() {
		
		String dataFormatada = "";
		if(this.dataDeAtendimento!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dataFormatada = sdf.format(this.dataDeAtendimento);	
		}
		return dataFormatada;
	}
	
	public void setDataAtendimentoStr(String dataAtendimentoStr) {
		this.dataAtendimentoStr = dataAtendimentoStr;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public String toString() {
		return "GradeHorario [id=" + id + ", disponibilidade=" + disponibilidade + ", dataDeAtendimento="
				+ dataDeAtendimento + ", medico=" + medico + "]";
	}
	
	
}
