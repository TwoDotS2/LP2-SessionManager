package br.ufrn.imd.manager.session.dtos;

import br.ufrn.imd.manager.session.models.Filme;
import br.ufrn.imd.manager.session.models.Sala;
import br.ufrn.imd.manager.session.models.Sessao;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CinemaDTO {
    private double faturamentoInteiras, faturamentoInteiras3D, faturamentoMeias, faturamentoMeias3D;
    private int ingressosInteiras, ingressosInteiras3D, ingressosMeias, ingressosMeias3D;
	private ArrayList<Sala> salas;
    private ArrayList<Filme> filmes;
    private ArrayList<Sessao> sessoes;
    
    
	public CinemaDTO(){
        faturamentoInteiras = 0;
        faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        ingressosInteiras = 0;
        ingressosInteiras3D = 0;
        ingressosMeias = 0;
        ingressosMeias3D =  0;
        salas = new ArrayList<Sala>();
        filmes = new ArrayList<Filme>();
        sessoes = new ArrayList<Sessao>();
    }
	
	public void fechar(){
		faturamentoInteiras = 0;
		faturamentoInteiras3D = 0;
        faturamentoMeias = 0;
        faturamentoMeias3D = 0;
        ingressosInteiras = 0;
        ingressosInteiras3D = 0;
        ingressosMeias = 0;
        ingressosMeias3D =  0;
        sessoes.clear();
	}
	
	public ArrayList<Sala> getSalas() {
		return salas;
	}
	public void addSala(Sala sala) {
		salas.add(sala);
	}
	public void removerSala(Sala sala) {
		salas.remove(sala);
	}
	

	public void addFilmes(Filme filme) {
		filmes.add(filme);
	}
	public void removerFilme(Filme filme) {
	        filmes.remove(filme);
	    }

	public void addSessoes(Sessao sessao) {
		sessoes.add(sessao);
	}
	public void removerSessao(Sessao sessao) {
        sessoes.remove(sessao);
    }
    
	
    

   }