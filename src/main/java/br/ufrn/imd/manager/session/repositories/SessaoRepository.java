package br.ufrn.imd.manager.session.repositories;

import br.ufrn.imd.manager.session.models.Sala;
import br.ufrn.imd.manager.session.models.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
