package avaliacao.partido.controller.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import avaliacao.partido.model.Associado;
import avaliacao.partido.model.CargoPolitico;
import avaliacao.partido.model.Partido;
import avaliacao.partido.model.Sexo;

public class AssociadoDto {
	
	private Long id;
	private String nome;
	private CargoPolitico cargoPolitico;
	private Date dataNascimento;
	private Sexo sexo;
	private Partido partido;
	
	public AssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = associado.getDataNascimento();
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public CargoPolitico getCargoPolitico() {
		return cargoPolitico;
	}
	public String getDataNascimento() {
		return new SimpleDateFormat("dd-MM-yyyy").format(dataNascimento);
	}
	public Sexo getSexo() {
		return sexo;
	}
	public Partido getPartido() {
		return partido;
	}
	
	public static List<AssociadoDto> converter(List<Associado> associados) {
		return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
	}
}
