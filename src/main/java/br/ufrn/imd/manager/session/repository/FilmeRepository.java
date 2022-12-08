package br.ufrn.imd.manager.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufrn.imd.manager.session.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    Filme findByTitulo(String titulo);
}
