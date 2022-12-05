package br.ufrn.imd.model;

import java.util.Arrays;

public class Filme {
	private String titulo, tipoProducao;
	private int duracao;
	private String[] tipoAudio;
	private boolean permite3D;
	
	public Filme() {}
	
	public Filme(String titulo, String tipoProducao, int duracao, String[] tipoAudio, boolean permite3d) {
		this.titulo = titulo;
		this.tipoProducao = tipoProducao;
		this.duracao = duracao;
		this.tipoAudio = tipoAudio;
		permite3D = permite3d;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoProducao() {
		return tipoProducao;
	}

	public void setTipoProducao(String tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String[] getTipoAudio() {
		return tipoAudio;
	}

	public void setTipoAudio(String[] tipoAudio) {
		this.tipoAudio = tipoAudio;
	}

	public boolean isPermite3D() {
		return permite3D;
	}

	public void setPermite3D(boolean permite3d) {
		permite3D = permite3d;
	}

	@Override
	public String toString() {
		return "Filme [titulo=" + titulo + ", tipoProducao=" + tipoProducao + ", duracao=" + duracao + ", tipoAudio="
				+ Arrays.toString(tipoAudio) + ", permite3D=" + permite3D + "]";
	}
	
	
	
}
