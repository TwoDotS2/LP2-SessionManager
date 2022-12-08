package br.ufrn.imd.manager.session.repositories;

import br.ufrn.imd.manager.session.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
