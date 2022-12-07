package br.ufrn.imd.manager.session.services;

import br.ufrn.imd.manager.session.dtos.FilmeDTO;
import br.ufrn.imd.manager.session.dtos.SalaDTO;
import br.ufrn.imd.manager.session.models.Filme;
import br.ufrn.imd.manager.session.models.Sala;
import br.ufrn.imd.manager.session.repositories.FilmeRepository;
import br.ufrn.imd.manager.session.repositories.SalaRepository;

import javax.transaction.Transactional;
import java.util.List;

public class SalaService {
    final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    /**
     * Persistir uma sala na db
     * @param salaDTO
     * @return sala persistida
     */
    @Transactional
    public Sala save(SalaDTO salaDTO){

        Sala sala = Sala.build(salaDTO.getId(), salaDTO.getNumSala(), salaDTO.getCapacidade());

        return salaRepository.save(sala);
    }

    /**
     * @return List com todas as salas
     */
    public List<Sala> findAll(){
        List<Sala> salas = salaRepository.findAll();;

        if (salas == null) throw new NullPointerException();
        else return salas;
    }

    /**
     * @param id
     * @return sala pelo id
     */
    public Sala findById(Integer id){
        return salaRepository.findById(id);
    }
}
