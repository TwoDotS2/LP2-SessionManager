package br.ufrn.imd.manager.session.service;

import br.ufrn.imd.manager.session.dto.FilmeDTO;
import br.ufrn.imd.manager.session.dto.SalaDTO;
import br.ufrn.imd.manager.session.dto.SessaoDTO;
import br.ufrn.imd.manager.session.entity.Filme;
import br.ufrn.imd.manager.session.entity.Sala;
import br.ufrn.imd.manager.session.entity.Sessao;
import br.ufrn.imd.manager.session.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository){
        this.sessaoRepository = sessaoRepository;
    }

    /**
     * Persistir uma sala na db
     * @param sessaoDTO
     * @return sala persistida
     */
    @Transactional
    public Sessao save(SessaoDTO sessaoDTO){

        FilmeDTO filmeDTO = sessaoDTO.getFilmeDTO();
        SalaDTO salaDTO = sessaoDTO.getSalaDTO();

        //Criando o filme
        Filme filme = Filme.build(filmeDTO.getId(), filmeDTO.getTitulo(), filmeDTO.getTipoProducao(),
                filmeDTO.getDuracao(), filmeDTO.getLinkImagem(), filmeDTO.getTipoAudio(), filmeDTO.isPermite3D());

        //Criando a sala
        Sala sala = Sala.build(salaDTO.getId(), salaDTO.getNumSala(), salaDTO.getCapacidade());

        Sessao sessao = Sessao.build(sessaoDTO.getId(), filme, sala,
                sessaoDTO.getHorarioInicial(), sessaoDTO.getHorarioFinal(), sessaoDTO.getValorIngresso(),
                sessaoDTO.getPoltronas(), sessaoDTO.isExibicao3D(), sessaoDTO.getTipoAudio());

        return sessaoRepository.save(sessao);
    }

    /**
     * @return List com todas as sessoes
     */
    public List<Sessao> findAll(){
        List<Sessao> sessoes = sessaoRepository.findAll();;

        if (sessoes == null) throw new NullPointerException();
        else return sessoes;
    }

    /**
     * @param id
     * @return sessao pelo id
     */
    public Optional<Sessao> findById(Integer id){
        return sessaoRepository.findById(id);
    }
}
