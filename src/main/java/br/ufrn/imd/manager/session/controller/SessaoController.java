package br.ufrn.imd.manager.session.controller;

import br.ufrn.imd.manager.session.dto.SessaoDTO;
import br.ufrn.imd.manager.session.entity.Sessao;
import br.ufrn.imd.manager.session.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    @Autowired
    SessaoService sessaoService;

    @GetMapping
    public ResponseEntity<List<Sessao>> findAllSessoes() {

        return ResponseEntity.ok(sessaoService.findAll());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Sessao> addSessao(@RequestBody SessaoDTO sessaoDTO){

        return new ResponseEntity<>(sessaoService.save(sessaoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findSessao(@PathVariable Integer id){

        return new ResponseEntity<>(sessaoService.findById(id), HttpStatus.OK);
    }
}
