package br.ufrn.imd.manager.session.models;

import br.ufrn.imd.manager.session.dtos.FilmeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Filmes")
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo, tipoProducao;
	private int duracao;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipos_de_audio")
	private List<String> tipoAudio = new ArrayList<String>();
	@Column(name = "permite_3d")
	private boolean permite3D;

	
	public Filme(String titulo, String tipoProducao, int duracao, ArrayList<String> tipoAudio, boolean permite3d) {
		this.titulo = titulo;
		this.tipoProducao = tipoProducao;
		this.duracao = duracao;
		this.tipoAudio = tipoAudio;
		permite3D = permite3d;
	}

	public Filme(FilmeDTO filmeDTO) {
		this.titulo = filmeDTO.getTitulo();
		this.tipoProducao = filmeDTO.getTipoProducao();
		this.duracao = filmeDTO.getDuracao();
		this.tipoAudio = filmeDTO.getTipoAudio();
		permite3D = filmeDTO.isPermite3D();
	}

	@Override
	public String toString() {
		return "Filme [titulo=" + titulo + ", tipoProducao=" + tipoProducao + ", duracao=" + duracao + ", tipoAudio="
				+ tipoAudio + ", permite3D=" + permite3D + "]";
	}



}
