package br.ufrn.imd.manager.session.services;

import br.ufrn.imd.manager.session.dtos.FilmeDTO;
import br.ufrn.imd.manager.session.models.Filme;
import br.ufrn.imd.manager.session.repositories.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class FilmeService {

    final FilmeRepository filmeRepository;


    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Filme save(FilmeDTO filmeDTO){

        Filme filme = Filme.build(filmeDTO.getId(), filmeDTO.getTitulo(), filmeDTO.getTipoProducao(),
                filmeDTO.getDuracao(), filmeDTO.getLinkImagem(), filmeDTO.getTipoAudio(), filmeDTO.isPermite3D());

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

    public Optional<Filme> findById(Integer id){

        return filmeRepository.findById(id);
    }

    @Transactional
    public Filme updateFilme(Integer id, FilmeDTO filmeDTO){
        Filme filmeUpdate = filmeRepository.getReferenceById(id);

        if (filmeUpdate == null) throw new NullPointerException();

        //Verificações para checar se os valores foram informados

        if(!isNull(filmeDTO.getTitulo())){
            filmeUpdate.setTitulo(filmeDTO.getTitulo());
        }

        if(!isNull(filmeDTO.getDuracao())){
            filmeUpdate.setDuracao(filmeDTO.getDuracao());
        }

        if (!isNull(filmeDTO.getLinkImagem())){
            filmeUpdate.setLinkImagem(filmeDTO.getLinkImagem());
        }

        if(!isNull(filmeDTO.getTipoAudio())){
            filmeUpdate.setTipoAudio(filmeDTO.getTipoAudio());
        }

        if ( !isNull(filmeDTO.isPermite3D())){
            filmeUpdate.setPermite3D(filmeDTO.isPermite3D());
        }

        //Salvar
        filmeRepository.save(filmeUpdate);

        return filmeUpdate;
    }
}
