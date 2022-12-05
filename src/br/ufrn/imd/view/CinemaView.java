package br.ufrn.imd.view;

import br.ufrn.imd.controller.FilmeController;
import br.ufrn.imd.controller.SalaController;
import br.ufrn.imd.controller.SessaoController;


public class CinemaView {

	public static void main(String[] args) {
		
		
		FilmeController ctl = new FilmeController();
		ctl.criarFilme();
		ctl.lerFilmes();
		
		SalaController scl = new SalaController();
		scl.criarSalas();
		scl.lerSalas();

		SessaoController sscl = new SessaoController();
		sscl.criarSessao();
		sscl.lerSessoes();
		
		
	}

}
