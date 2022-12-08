package br.ufrn.imd.manager.session.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class SalaDTO {
	@Id
	@GeneratedValue
	private Integer id;
	private int numSala;
	private int capacidade;
	
	public SalaDTO() {}
	public SalaDTO(int numSala, int capacidade) {
		this.numSala = numSala;
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [numSala=" + numSala + ", capacidade=" + capacidade + "]";
	}
}
