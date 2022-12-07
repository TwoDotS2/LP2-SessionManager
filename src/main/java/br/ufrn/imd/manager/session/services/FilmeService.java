package br.ufrn.imd.manager.session.services;

import br.ufrn.imd.manager.session.dtos.FilmeDTO;
import br.ufrn.imd.manager.session.models.Filme;
import br.ufrn.imd.manager.session.repositories.FilmeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmeService {

    final FilmeRepository filmeRepository;


    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Filme save(FilmeDTO filmeDTO){

        Filme filme = Filme.build(filmeDTO.getId(), filmeDTO.getTitulo(), filmeDTO.getTipoProducao(),
                filmeDTO.getDuracao(), filmeDTO.getTipoAudio(), filmeDTO.isPermite3D());

       return filmeRepository.save(filme);
    }

    public List<Filme> findAll(){
        List<Filme> filmes = filmeRepository.findAll();;

        if (filmes == null) throw new NullPointerException();
        else return filmes;
    }

    public Filme findByTitulo(String titulo){
        return filmeRepository.findByTitulo(titulo);
    }
}
