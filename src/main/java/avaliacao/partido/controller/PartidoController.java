package avaliacao.partido.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import avaliacao.partido.controller.dto.AssociadoDto;
import avaliacao.partido.controller.dto.PartidoDto;
import avaliacao.partido.controller.form.AssociadoForm;
import avaliacao.partido.controller.form.PartidoForm;
import avaliacao.partido.model.Associado;
import avaliacao.partido.model.Partido;
import avaliacao.partido.repository.AssociadoRepository;
import avaliacao.partido.repository.PartidoRepository;

@RestController
@RequestMapping("avaliacao")
public class PartidoController {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@GetMapping("associados")
	@Cacheable(value = "listaAssociados")
	public List<AssociadoDto> listarAssociados(){
		
		List<Associado> associados = associadoRepository.findAll();
		return AssociadoDto.converter(associados);
	}
	
	@GetMapping("partidos")
	@Cacheable(value = "listaPartidos")
	public List<PartidoDto> listarPartidos(){
		
		List<Partido> partidos = partidoRepository.findAll();
		return PartidoDto.converter(partidos);
	}
	
	@PostMapping("associados")
	@Transactional
	@CacheEvict(value = "listaAssociados", allEntries = true)
	public ResponseEntity<AssociadoDto> cadastrarAssociados(@RequestBody @Valid AssociadoForm form, UriComponentsBuilder UriBuilder) {
		
		Associado associado = form.converter(associadoRepository);
		associadoRepository.save(associado);
		
		URI uri = UriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDto(associado));
		
	}
	
	@PostMapping("partidos")
	@Transactional
	@CacheEvict(value = "listaPartidos", allEntries = true)
	public ResponseEntity<PartidoDto> cadastrarPartidos(@RequestBody @Valid PartidoForm form, UriComponentsBuilder UriBuilder) {
		
		Partido partido = form.converter(partidoRepository);
		partidoRepository.save(partido);
		
		URI uri = UriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDto(partido));
		
	}
	
	@GetMapping("/associados/{id}")
	public ResponseEntity<AssociadoDto> detalharAssociado(@PathVariable Long id) {
		Optional<Associado> estado = associadoRepository.findById(id);
		if(estado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDto(estado.get()));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/partidos/{id}")
	public ResponseEntity<PartidoDto> detalharPartido(@PathVariable Long id) {
		Optional<Partido> partido = partidoRepository.findById(id);
		if(partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDto(partido.get()));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/associados/{id}")
	@Transactional
	@CacheEvict(value = "listaAssociados", allEntries = true)
	public ResponseEntity<AssociadoDto> atualizarAssociado(@PathVariable Long id, @RequestBody @Valid AssociadoForm form) {
		Optional<Associado> optional = associadoRepository.findById(id);
		if(optional.isPresent()) {
			Associado associado = form.atualizar(id, associadoRepository);
			return ResponseEntity.ok(new AssociadoDto(associado));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/partidos/{id}")
	@Transactional
	@CacheEvict(value = "listaPartidos", allEntries = true)
	public ResponseEntity<PartidoDto> atualizarPartido(@PathVariable Long id, @RequestBody @Valid PartidoForm form) {
		Optional<Partido> optional = partidoRepository.findById(id);
		if(optional.isPresent()) {
			Partido partido = form.atualizar(id, partidoRepository);
			return ResponseEntity.ok(new PartidoDto(partido));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/associados/{id}")
	@Transactional
	@CacheEvict(value = "listaAssociados", allEntries = true)
	public ResponseEntity<?> deletarAssociado(@PathVariable Long id){
		Optional<Associado> optional = associadoRepository.findById(id);
		if(optional.isPresent()) {
			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/partidos/{id}")
	@Transactional
	@CacheEvict(value = "listaPartidos", allEntries = true)
	public ResponseEntity<?> deletarPartido(@PathVariable Long id){
		Optional<Partido> optional = partidoRepository.findById(id);
		if(optional.isPresent()) {
			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/associados/{id}/partidos/{id2}")
	@Transactional
	public ResponseEntity<?> vincularAssociadoAoPartido(@PathVariable Long id, @PathVariable Long id2) {
		Optional<Associado> optional = associadoRepository.findById(id);
		Optional<Partido> optional2 = partidoRepository.findById(id2);
		if(optional.isPresent() && optional2.isPresent()) {
			Associado associado = optional.get();
			Partido partido = optional2.get();
			associado.setPartido(partido);
			return ResponseEntity.ok(new AssociadoDto(associado));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping("/partidos/{id}/associados/{id2}")
	@Transactional
	public ResponseEntity<?> vincularPartidoAoAssociado(@PathVariable Long id, @PathVariable Long id2) {
		Optional<Partido> optional = partidoRepository.findById(id);
		Optional<Associado> optional2 = associadoRepository.findById(id2);
		if(optional.isPresent() && optional2.isPresent()) {
			Partido partido = optional.get();
			Associado associado = optional2.get();
			partido.getAssociado().add(associado);
			return ResponseEntity.ok(new PartidoDto(partido));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping ("/associados/cargo")
	public List<Associado> listarPorCargo() {
		Sort sort = Sort.by("cargoPolitico").ascending();
		return this.associadoRepository.findAll(sort);
	}
	
	@DeleteMapping("/associados/{id}/partidos/{id2}")
	@Transactional
	public ResponseEntity<?> removerAssociadoDoPartido(@PathVariable Long id, @PathVariable Long id2) {
		Optional<Associado> optional = associadoRepository.findById(id);
		Optional<Partido> optional2 = partidoRepository.findById(id2);
		if(optional.isPresent() && optional2.isPresent()) {
			Associado associado = optional.get();
			Partido partido = optional2.get();
			partido.getAssociado().remove(associado);
			return ResponseEntity.ok(new PartidoDto(partido));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping ("/partidos/ideologia")
	public List<Partido> listarPorIdeologia() {
		Sort sort = Sort.by("ideologia").ascending();
		return this.partidoRepository.findAll(sort);
	}
	
}
