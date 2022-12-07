package br.ufrn.imd.view;

import br.ufrn.imd.controller.CinemaController;
import br.ufrn.imd.controller.FilmeController;
import br.ufrn.imd.controller.SalaController;
import br.ufrn.imd.controller.SessaoController;
import br.ufrn.imd.model.Cinema;


public class CinemaView {

	public static void main(String[] args) {
		
		//MenuGerenciamento mg = new MenuGerenciamento();
		//mg.menuGeral();
		//mg.gerenciarFilmes();
		//mg.gerenciarSalas();
		
		//CinemaController cc = new CinemaController();
		//cc.venderIngresso();
		
		
		FilmeController ctl = new FilmeController();
		//ctl.criarFilme();
		ctl.criarFilme();
		ctl.lerFilmes();
		//ctl.modificarFilme();
		
		SalaController scl = new SalaController();
		//scl.criarSalas();
		scl.criarSalas();
		scl.lerSalas();
		//scl.modificarSala();
	
		
		SessaoController sscl = new SessaoController();
		sscl.criarSessao();
		//sscl.lerSessoes();
		
		//cc.lerFaturamento();
	
	}

}
