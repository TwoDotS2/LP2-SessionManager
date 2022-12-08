package br.ufrn.imd.manager.session.repository;

import br.ufrn.imd.manager.session.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
