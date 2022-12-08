package br.ufrn.imd.manager.session.controller;

import br.ufrn.imd.manager.session.dto.FilmeDTO;
import br.ufrn.imd.manager.session.model.Filme;
import br.ufrn.imd.manager.session.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<Filme>> findAllFilmes() {

        return ResponseEntity.ok(filmeService.findAll());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Filme> addFilme(@RequestBody FilmeDTO filmeDTO){

            return new ResponseEntity<>(filmeService.save(filmeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findFilmeById(@PathVariable Integer id){

        return new ResponseEntity<>(filmeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/filme/{title}")
    public ResponseEntity<Object> findFilmeByTitulo(@PathVariable String title){

        return new ResponseEntity<>(filmeService.findByTitulo(title), HttpStatus.OK);
    }

    // FIXME: 08/12/2022 - Exception na chamada
    @PutMapping("/filme/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable Integer id,@RequestBody FilmeDTO filmeDTO){

        return new ResponseEntity<>(filmeService.updateFilme(id, filmeDTO), HttpStatus.OK);
    }
}
