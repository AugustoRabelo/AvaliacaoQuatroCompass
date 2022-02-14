package avaliacao.partido.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import avaliacao.partido.model.Associado;
import avaliacao.partido.model.CargoPolitico;
import avaliacao.partido.model.Sexo;
import avaliacao.partido.repository.AssociadoRepository;

public class AssociadoForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull
	private CargoPolitico cargoPolitico;
	@NotNull
	private Date dataNascimento;
	@NotNull
	private Sexo sexo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public CargoPolitico getCargoPolitico() {
		return cargoPolitico;
	}
	public void setCargoPolitico(CargoPolitico cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Associado converter(AssociadoRepository associadoRepository) {
		return new Associado(nome, cargoPolitico, dataNascimento, sexo);
	}
	
	public Associado atualizar(Long id, AssociadoRepository associadoRepository) {
		
		Associado associado = associadoRepository.getOne(id);
		
		associado.setNome(this.nome);
		associado.setCargoPolitico(this.cargoPolitico);
		associado.setDataNascimento(this.dataNascimento);
		associado.setSexo(this.sexo);
		
		return associado;
	}
}
