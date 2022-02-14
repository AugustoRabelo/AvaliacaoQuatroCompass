package avaliacao.partido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import avaliacao.partido.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{
	
}
