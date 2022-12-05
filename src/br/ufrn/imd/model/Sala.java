package br.ufrn.imd.model;

public class Sala {
	
	private int numSala;
	private int capacidade;
	
	public Sala() {}
	public Sala(int numSala, int capacidade) {
		this.numSala = numSala;
		this.capacidade = capacidade;
	}
	public int getNumSala() {
		return numSala;
	}
	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	@Override
	public String toString() {
		return "Sala [numSala=" + numSala + ", capacidade=" + capacidade + "]";
	}
	
	
	
}
