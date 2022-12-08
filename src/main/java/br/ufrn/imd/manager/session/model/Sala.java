package br.ufrn.imd.manager.session.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "salas")
@AllArgsConstructor(staticName = "build")
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int numSala;
	private int capacidade;

	public Sala() {}
	public Sala(int numSala, int capacidade) {
		this.numSala = numSala;
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [numSala=" + numSala + ", capacidade=" + capacidade + "]";
	}
}
