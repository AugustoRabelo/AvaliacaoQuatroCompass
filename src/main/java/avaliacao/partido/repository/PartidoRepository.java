package avaliacao.partido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import avaliacao.partido.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
	
}
