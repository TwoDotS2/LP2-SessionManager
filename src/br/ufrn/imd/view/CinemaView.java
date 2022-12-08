package br.ufrn.imd.view;

import br.ufrn.imd.controller.CinemaController;
import br.ufrn.imd.controller.FilmeController;
import br.ufrn.imd.controller.SalaController;
import br.ufrn.imd.controller.SessaoController;
import br.ufrn.imd.model.Cinema;


public class CinemaView {
	
	public static void main(String[] args) {
		Cinema cinema = new Cinema();
		//MenuGerenciamento mg = new MenuGerenciamento();
		
		//mg.gerenciarFilmes();
		//mg.gerenciarSalas();
		
		CinemaController cc = new CinemaController();
		
		
		
		FilmeController ctl = new FilmeController();
		//ctl.criarFilme();
		cinema.addFilmes(ctl.criarFilme());
		ctl.lerFilmes(cinema.getFilmes());
		cinema.addFilmes(ctl.criarFilme());
		ctl.lerFilmes(cinema.getFilmes());
		
		ctl.modificarFilme(cinema);
		ctl.lerFilmes(cinema.getFilmes());
		
		SalaController scl = new SalaController();
		//scl.criarSalas();
		cinema.addSala(scl.criarSalas());
		scl.lerSalas(cinema.getSalas());
		//scl.modificarSala();
	
		
		SessaoController sscl = new SessaoController();
		cinema.addSessoes(sscl.criarSessao(cinema));
		sscl.lerSessoes(cinema.getSessoes());
		
		cinema.addSessoes(sscl.criarSessao(cinema));
		sscl.lerSessoes(cinema.getSessoes());
		
		cc.venderIngresso(cinema);
		sscl.lerSessoes(cinema.getSessoes());
		cc.venderIngresso(cinema);
		//mg.menuGeral();
		cc.lerFaturamento(cinema);
	
	}

}
