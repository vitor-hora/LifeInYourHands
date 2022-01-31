package lifeinyourhands.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {
		
	public Consulta() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Boolean necessidadeAfastamento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date afastamentoInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date afastamentoFim;



	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataConsulta;
	
	@Transient
	private String dataConsultaStr;
	
	@OneToMany(mappedBy = "consulta")
	private List<Exame> ExamesRealizados;

	@OneToMany(mappedBy = "consulta")
	private List<Medicamento> medicamentosReceitados;

	@OneToMany(mappedBy = "consulta")
	private List<Procedimento> procedimentosReceitados;

	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getNecessidadeAfastamento() {
		return necessidadeAfastamento;
	}

	public void setNecessidadeAfastamento(Boolean necessidadeAfastamento) {
		this.necessidadeAfastamento = necessidadeAfastamento;
	}

	public Date getAfastamentoInicio() {
		return afastamentoInicio;
	}

	public void setAfastamentoInicio(Date afastamentoInicio) {
		this.afastamentoInicio = afastamentoInicio;
	}

	public Date getAfastamentoFim() {
		return afastamentoFim;
	}

	public void setAfastamentoFim(Date afastamentoFim) {
		this.afastamentoFim = afastamentoFim;
	}

	public List<Exame> getExamesRealizados() {
		return ExamesRealizados;
	}

	public void setExamesRealizados(List<Exame> examesRealizados) {
		ExamesRealizados = examesRealizados;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDataConsultaStr() {
		return dataConsultaStr;
	}

	public void setDataConsultaStr(String dataConsultaStr) {
		this.dataConsultaStr = dataConsultaStr;
	}

	public List<Medicamento> getMedicamentosReceitados() {
		return medicamentosReceitados;
	}

	public void setMedicamentosReceitados(List<Medicamento> medicamentosReceitados) {
		this.medicamentosReceitados = medicamentosReceitados;
	}

	public List<Procedimento> getProcedimentosReceitados() {
		return procedimentosReceitados;
	}

	public void setProcedimentosReceitados(List<Procedimento> procedimentosReceitados) {
		this.procedimentosReceitados = procedimentosReceitados;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", necessidadeAfastamento=" + necessidadeAfastamento + ", afastamentoInicio="
				+ afastamentoInicio + ", afastamentoFim=" + afastamentoFim + ", dataConsulta=" + dataConsulta
				+ ", dataConsultaStr=" + dataConsultaStr + ", paciente=" + paciente + ", medico=" + medico + "]";
	}


}
