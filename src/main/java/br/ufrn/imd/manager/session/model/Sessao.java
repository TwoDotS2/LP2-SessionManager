package br.ufrn.imd.manager.session.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Arrays;

@Data
@Entity
@Table(name = "sessoes")
@AllArgsConstructor(staticName = "build")
public class Sessao {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_filme",referencedColumnName = "id")
	private Filme filme;
    @ManyToOne
    @JoinColumn(name = "id_sala",referencedColumnName = "id")
    private Sala sala;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private double valorIngresso;
    private char[] poltronas; //l = livre; m = meia; i = inteira
    private boolean exibicao3D;
    private String tipoAudio;

    public Sessao(){}

	public Sessao(Filme filme, Sala sala, LocalTime horarioInicial, LocalTime horarioFinal, double valorIngresso, boolean exibicao3d, String tipoAudio) {
		this.filme = filme;
		this.sala = sala;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.valorIngresso = valorIngresso;
		exibicao3D = exibicao3d;
		this.tipoAudio = tipoAudio;

		poltronas = new char[sala.getCapacidade()];
        for(int i=0; i < poltronas.length; i++) { //Inicializando todas as poltronas como livres.
            poltronas[i] = 'l';
        }
	}


	@Override
	public String toString() {
		return "Sessao [filme=" + filme + ", horarioInicial=" + horarioInicial + ", horarioFinal=" + horarioFinal
				+ ", valorIngresso=" + valorIngresso + ", poltronas=" + Arrays.toString(poltronas) + ", exibicao3D="
				+ exibicao3D + ", tipoAudio=" + tipoAudio + "]";
	}


    
    
}
