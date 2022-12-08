package br.ufrn.imd.manager.session.controller;

import br.ufrn.imd.manager.session.dto.SalaDTO;
import br.ufrn.imd.manager.session.entity.Sala;
import br.ufrn.imd.manager.session.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    SalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> findAllSalas() {

        return ResponseEntity.ok(salaService.findAll());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Sala> addSala(@RequestBody SalaDTO salaDTO){

        return new ResponseEntity<>(salaService.save(salaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findFilmeById(@PathVariable Integer id){

        return new ResponseEntity<>(salaService.findById(id), HttpStatus.OK);
    }

}
