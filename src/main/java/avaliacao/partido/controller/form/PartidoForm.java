package avaliacao.partido.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import avaliacao.partido.model.Ideologia;
import avaliacao.partido.model.Partido;
import avaliacao.partido.repository.PartidoRepository;

public class PartidoForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String sigla;
	@NotNull
	private Ideologia ideologia;
	@NotNull
	private Date dataFundacao;
	
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
	
	public Partido converter(PartidoRepository partidoRepository) {
		return new Partido(nome, sigla, ideologia, dataFundacao);
	}
	
	public Partido atualizar(Long id, PartidoRepository partidoRepository) {
		
		Partido partido = partidoRepository.getOne(id);
		
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setDataFundacao(this.dataFundacao);
		
		return partido;
	}
	
}
