package br.ufrn.imd.manager.session.service;

import br.ufrn.imd.manager.session.dto.SalaDTO;
import br.ufrn.imd.manager.session.entity.Sala;
import br.ufrn.imd.manager.session.repository.SalaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
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
    public Optional<Sala> findById(Integer id){
        return salaRepository.findById(id);
    }
}
