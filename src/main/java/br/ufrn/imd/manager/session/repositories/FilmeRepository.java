package br.ufrn.imd.manager.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufrn.imd.manager.session.models.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    Filme findByTitulo(String titulo);
}
