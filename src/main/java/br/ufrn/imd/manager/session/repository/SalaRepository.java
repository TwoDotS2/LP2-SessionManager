package br.ufrn.imd.manager.session.repository;

import br.ufrn.imd.manager.session.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}