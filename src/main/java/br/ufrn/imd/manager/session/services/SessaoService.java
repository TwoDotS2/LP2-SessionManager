package br.ufrn.imd.manager.session.services;

import br.ufrn.imd.manager.session.models.Sessao;
import br.ufrn.imd.manager.session.repositories.SessaoRepository;

import javax.transaction.Transactional;
import java.util.List;

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
    public Sessao save(Sessao sessaoDTO){

        Sessao sessao = Sessao.build(sessaoDTO.getId(), sessaoDTO.getFilme(), sessaoDTO.getSala(),
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
    public Sessao findById(Integer id){
        return sessaoRepository.findById(id);
    }
}
