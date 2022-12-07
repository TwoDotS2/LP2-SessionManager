package br.ufrn.imd.manager.session.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class FilmeDTO {

	private Integer id;
	private String titulo;
	private String tipoProducao;
	private int duracao;

	private List<String> tipoAudio = new ArrayList<String>();
	private boolean permite3D;
	
	public FilmeDTO() {}
	
	public FilmeDTO(String titulo, String tipoProducao, int duracao, ArrayList<String> tipoAudio, boolean permite3d) {
		this.titulo = titulo;
		this.tipoProducao = tipoProducao;
		this.duracao = duracao;
		this.tipoAudio = tipoAudio;
		permite3D = permite3d;
	}

	@Override
	public String toString() {
		return "Filme [titulo=" + titulo + ", tipoProducao=" + tipoProducao + ", duracao=" + duracao + ", tipoAudio="
				+ tipoAudio + ", permite3D=" + permite3D + "]";
	}
	
	
	
}
