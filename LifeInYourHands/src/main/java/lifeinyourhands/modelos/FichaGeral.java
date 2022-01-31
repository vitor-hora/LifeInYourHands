package lifeinyourhands.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ficha_geral")
public class FichaGeral implements Serializable{
	
	public FichaGeral() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String planoSaude;

	private String tipoSangue;

	private Float peso;

	private Float altura;

	private Boolean consomeAlcool;

	private Boolean usaDroga;

	@OneToOne
	private Paciente paciente;

	private String fatorRH;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(String planoSaude) {
		this.planoSaude = planoSaude;
	}

	public String getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}

	public Boolean getConsomeAlcool() {
		return consomeAlcool;
	}

	public void setConsomeAlcool(Boolean consomeAlcool) {
		this.consomeAlcool = consomeAlcool;
	}

	public Boolean getUsaDroga() {
		return usaDroga;
	}

	public void setUsaDroga(Boolean usaDroga) {
		this.usaDroga = usaDroga;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getFatorRH() {
		return fatorRH;
	}

	public void setFatorRH(String fatorRH) {
		this.fatorRH = fatorRH;
	}

	@Override
	public String toString() {
		return "FichaGeral [id=" + id + ", planoSaude=" + planoSaude + ", tipoSangue=" + tipoSangue + ", peso=" + peso
				+ ", altura=" + altura + ", consomeAlcool=" + consomeAlcool + ", usaDroga=" + usaDroga + ", paciente="
				+ paciente + ", fatorRH=" + fatorRH + "]";
	}

	
	

}
