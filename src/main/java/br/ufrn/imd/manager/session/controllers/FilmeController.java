package br.ufrn.imd.manager.session.controllers;

import br.ufrn.imd.manager.session.dtos.FilmeDTO;
import br.ufrn.imd.manager.session.models.Filme;
import br.ufrn.imd.manager.session.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/filmes/")
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
}