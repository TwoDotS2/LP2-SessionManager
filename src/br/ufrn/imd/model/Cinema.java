package br.ufrn.imd.model;

import java.util.ArrayList;

public class Cinema {
	
    private double faturamentoInteiras, faturamentoInteiras3D, faturamentoMeias, faturamentoMeias3D;
    private int ingressosInteiras, ingressosInteiras3D, ingressosMeias, ingressosMeias3D; 
    private ArrayList<Sala> salas;
    private ArrayList<Filme> filmes;
    private ArrayList<Sessao> sessoes;
    
    
	public Cinema(){
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
	
	public double getFaturamentoInteiras() {
		return faturamentoInteiras;
	}
	public void setFaturamentoInteiras(double faturamentoInteiras) {
		this.faturamentoInteiras = faturamentoInteiras;
	}
	public double getFaturamentoInteiras3D() {
		return faturamentoInteiras3D;
	}
	public void setFaturamentoInteiras3D(double faturamentoInteiras3D) {
		this.faturamentoInteiras3D = faturamentoInteiras3D;
	}
	public double getFaturamentoMeias() {
		return faturamentoMeias;
	}
	public void setFaturamentoMeias(double faturamentoMeias) {
		this.faturamentoMeias = faturamentoMeias;
	}
	public double getFaturamentoMeias3D() {
		return faturamentoMeias3D;
	}
	public void setFaturamentoMeias3D(double faturamentoMeias3D) {
		this.faturamentoMeias3D = faturamentoMeias3D;
	}
	public int getIngressosInteiras() {
		return ingressosInteiras;
	}
	public void setIngressosInteiras(int ingressosInteiras) {
		this.ingressosInteiras = ingressosInteiras;
	}
	public int getIngressosInteiras3D() {
		return ingressosInteiras3D;
	}
	public void setIngressosInteiras3D(int ingressosInteiras3D) {
		this.ingressosInteiras3D = ingressosInteiras3D;
	}
	public int getIngressosMeias() {
		return ingressosMeias;
	}
	public void setIngressosMeias(int ingressosMeias) {
		this.ingressosMeias = ingressosMeias;
	}
	public int getIngressosMeias3D() {
		return ingressosMeias3D;
	}
	public void setIngressosMeias3D(int ingressosMeias3D) {
		this.ingressosMeias3D = ingressosMeias3D;
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
	
	
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}
	public void addFilmes(Filme filme) {
		filmes.add(filme);
	}
	public void removerFilme(Filme filme) {
	        filmes.remove(filme);
	    }
	
	
	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}
	public void addSessoes(Sessao sessao) {
		sessoes.add(sessao);
	}
	public void removerSessao(Sessao sessao) {
        sessoes.remove(sessao);
    }
    
	
    

   }