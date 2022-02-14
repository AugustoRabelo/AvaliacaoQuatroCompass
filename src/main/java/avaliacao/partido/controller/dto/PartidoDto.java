package avaliacao.partido.controller.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import avaliacao.partido.model.Associado;
import avaliacao.partido.model.Ideologia;
import avaliacao.partido.model.Partido;

public class PartidoDto {
	
	private Long id;
	private String nome;
	private String sigla;
	private Ideologia ideologia;
	private Date dataFundacao;
	private List<Associado> associado;
	
	public PartidoDto(Partido partido) {
		this.id = partido.getId();
		this.nome = partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
		this.associado = partido.getAssociado();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSigla() {
		return sigla;
	}
	public Ideologia getIdeologia() {
		return ideologia;
	}
	public String getDataFundacao() {
		return new SimpleDateFormat("dd-MM-yyyy").format(dataFundacao);
	}
	public List<Associado> getAssociado() {
		return associado;
	}

	public static List<PartidoDto> converter(List<Partido> partidos) {
		return partidos.stream().map(PartidoDto::new).collect(Collectors.toList());
	}
}
