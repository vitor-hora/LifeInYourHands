package lifeinyourhands.modelos;

import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "status")
@SQLDelete(sql = "update Status set ativo = 0 where id = ?") //Exclusão lógica Hibernate
@Where(clause = "ativo = 1")
public class Status implements Serializable{

	public Status() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean ativo = true;

	private String statusEntrega;

	private String statusExame;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;

	@OneToMany
	private List<Exame> exames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(String statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public String getStatusExame() {
		return statusExame;
	}

	public void setStatusExame(String statusExame) {
		this.statusExame = statusExame;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", statusEntrega=" + statusEntrega + ", statusExame=" + statusExame
				+ ", dataEntrega=" + dataEntrega + ", exames=" + exames + "]";
	}

	
	
}