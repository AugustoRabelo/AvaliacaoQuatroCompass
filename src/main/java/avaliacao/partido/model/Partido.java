package avaliacao.partido.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Partido {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	private Date dataFundacao;
	
	@OneToMany
	private List<Associado> associado;
	
	public Partido() {
		
	}
	
	public Partido(String nome, String sigla, Ideologia ideologia, Date dataFundacao) {
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Ideologia getIdeologia() {
		return ideologia;
	}
	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}
	public Date getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	public List<Associado> getAssociado() {
		return associado;
	}
	public void setAssociado(List<Associado> associado) {
		this.associado = associado;
	}
	
	@Override
	public String toString() {
		return "Partido [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", ideologia=" + ideologia
				+ ", dataFundacao=" + dataFundacao + "]";
	}
	
	
	
}
