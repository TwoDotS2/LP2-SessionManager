package ufrn.imd.edu.controller;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private Integer numero;
    private Integer lotacao;

    //1- assentos[i][j] = Boolean.TRUE, assento disponível
    //2- assentos[i][j] = Boolean.FALSE, assento indisponível
    private ArrayList<ArrayList<Boolean>> assentos;

    //Construtor
    public Sala() {
    }

    public Sala(Integer numero) {
        this.numero = numero;
    }

    //Metódos de controle
    public void configurarSala(int linha, int coluna){
        assentos = new ArrayList<ArrayList<Boolean>>();

        for (int i = linha; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                assentos
                        .get(i)
                        .add(Boolean.FALSE);
            }
        }

        lotacao = linha * coluna;
    }

    public Boolean assentoOcupado(int linha, int coluna){
        return assentos
                .get(linha)
                .get(coluna);
    }

    public void exibirAssentos() {
    }

    //Getters e Setters
    public Integer getNumero() {
        return numero;
    }

    public Integer getLinha(){
        return assentos.size();
    }

    public Integer getColuna(){
        return assentos.get(0).size();
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }
}
