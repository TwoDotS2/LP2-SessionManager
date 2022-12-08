package br.ufrn.imd.view;

import java.util.ArrayList;

import br.ufrn.imd.controller.CinemaController;
import br.ufrn.imd.controller.CinemaUtil;
import br.ufrn.imd.controller.FilmeController;
import br.ufrn.imd.controller.SalaController;
//import br.ufrn.imd.controller.SessaoController;
import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Filme;
import br.ufrn.imd.model.Sala;

public class MenuGerenciamento {
	
	 private boolean aberto = false;
	 private Cinema cinema = new Cinema();
	 private CinemaUtil util = new CinemaUtil();
	 //private SessaoController ssc = new SessaoController();
	 private FilmeController fc = new FilmeController();
	 private CinemaController cc = new CinemaController();
	 private SalaController sc = new SalaController();
	 private int opcaoInt=0;
	 
	 
	 public void menuGeral(){

	        do {
	            util.limparTela();

	            if(aberto) {
	                System.out.println("--------------------------------------------");
	                System.out.println("| -> A venda de ingressos foi iniciada! <- |");
	                System.out.println("|                                          |");
	                System.out.println("| 1 - Vender ingresso.                     |");
	                System.out.println("| 2 - Cancelar a venda de um ingresso      |");
	                System.out.println("| 3 - Visualizar sess�es.                  |");
	                System.out.println("| 4 - Visualizar faturamento.              |");
	                System.out.println("| 5 - Finalizar venda de Ingressos.        |");
	                System.out.println("| 6 - Encerrar.                            |");
	                System.out.println("--------------------------------------------");

	                System.out.print("\nDigite o n�mero da op��o desejada: ");
	                opcaoInt = util.leitor(1, 6, 'i');

	                switch(opcaoInt) {
	                    case 1:
	                        //cc.venderIngresso();
	                        opcaoInt = 0; //Evita do programa ser encerrado, caso o caixa venda a poltrona 5.
	                        break;
	                    case 2:
	                        //cancelarVenda();
	                        opcaoInt = 0; //Evita do program ser encerrado, caso o caixa cancele a venda da poltrona 5.
	                        break;
	                    case 3:
	                        //lerSessoes();
	                        break;
	                    case 4:
	                        //lerFaturamento();
	                        break;
	                    case 5:
	                        System.out.println("\n-------------------------------------------------------------------------------");
	                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
	                        System.out.println("|                                                                             |");
	                        System.out.println("| 1 - Sim                                                                     |");
	                        System.out.println("| 2 - N�o                                                                     |");
	                        System.out.println("-------------------------------------------------------------------------------");

	                        System.out.println("Ao finalizar a venda de ingressos, todas as sess�es ser�o apagadas e o faturamento ser� reiniciado.");
	                        System.out.print("Op��o: ");
	                        opcaoInt = util.leitor(1, 2, 'i');
	                        if(opcaoInt == 1){ 
	                            aberto = false;
	                            cinema.fechar();
	                        }
	                        break;
	                    case 6:
	                        System.out.println("\n-------------------------------------------------------------------------------");
	                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
	                        System.out.println("|                                                                             |");
	                        System.out.println("| 1 - Sim                                                                     |");
	                        System.out.println("| 2 - N�o                                                                     |");
	                        System.out.println("-------------------------------------------------------------------------------");

	                        System.out.println("Ao encerrar o programa, todos os dados ser�o apagados.");
	                        System.out.print("Op��o: ");
	                        opcaoInt = util.leitor(1, 2, 'i');
	                        if(opcaoInt == 1) {
	                            opcaoInt = 5;
	                        }
	                }       

	            } else {

	                System.out.println("------------------------------------------------------");
	                System.out.println("| -> A venda de ingressos ainda n�o foi iniciada! <- |");
	                System.out.println("|                                                    |");
	                System.out.println("| 1 - Gerenciar sess�es.                             |");
	                System.out.println("| 2 - Gerenciar salas.                               |");
	                System.out.println("| 3 - Gerenciar filmes.                              |");
	                System.out.println("| 4 - Iniciar venda de ingressos.                    |");
	                System.out.println("| 5 - Encerrar.                                      |");
	                System.out.println("------------------------------------------------------");

	                System.out.print("\nDigite o n�mero da op��o desejada: ");
	                opcaoInt = util.leitor(1, 5, 'i');

	                switch(opcaoInt) {
	                    case 1:
	                        //gerenciarSessoes();
	                        break;
	                    case 2:
	                        gerenciarSalas();
	                        break;
	                    case 3:
	                        //gerenciarFilmes();
	                        break;
	                    case 4:
	                        if(cinema.getSessoes().size() == 0) { //Nenhuma sess�o criada.
	                            System.out.println("� necess�rio criar uma sess�o antes de iniciar as vendas.");
	                            util.pausar(); 
	                              
	                        } else {
	                            System.out.println("\n-------------------------------------------------------------------------------");
	                            System.out.println("|                    Tem certeza que deseja continuar?                        |");
	                            System.out.println("|                                                                             |");
	                            System.out.println("| 1 - Sim                                                                     |");
	                            System.out.println("| 2 - N�o                                                                     |");
	                            System.out.println("-------------------------------------------------------------------------------");

	                            System.out.println("\nAp�s iniciar as vendas, n�o ser� poss�vel modificar e nem criar sess�es.");

	                            System.out.print("Op��o: ");
	                            opcaoInt = util.leitor(1, 2, 'i');
	                            if(opcaoInt == 1){
	                                aberto = true;
	                            }
	                        

	                        }
	                        break;
	                    case 5:
	                        System.out.println("\n-------------------------------------------------------------------------------");
	                        System.out.println("|                    Tem certeza que deseja continuar?                        |");
	                        System.out.println("|                                                                             |");
	                        System.out.println("| 1 - Sim                                                                     |");
	                        System.out.println("| 2 - N�o                                                                     |");
	                        System.out.println("-------------------------------------------------------------------------------");

	                        System.out.println("Ao encerrar o programa, todos os dados ser�o apagados.");
	                        System.out.print("Op��o: ");
	                        opcaoInt = util.leitor(1, 2, 'i');
	                        if(opcaoInt == 1){
	                            opcaoInt = 5;
	                        }
	                }
	            }

	        } while(opcaoInt!=5);
	    }

	 public void gerenciarFilmes(ArrayList<Filme> filmes) {
	       

		    util.limparTela();
	        
	        if(filmes.size() == 0){ //GERENCIAR FILMES - PRIMEIRA VEZ
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                  -> Voc� est� gerenciando os filmes <-                      |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Nenhum filme foi definido.                                                  |");
	            System.out.println("|                                                                             |");
	            System.out.println("| 1 - Criar um novo filme.                                                    |");
	            System.out.println("| 2 - Voltar.                                                                 |");
	            System.out.println("-------------------------------------------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            opcaoInt = util.leitor(1, 2, 'i');

	            if(opcaoInt == 1){
	                fc.criarFilme();
	                //gerenciarFilmes();
	            }
	        } else { //GERENCIAR FILMES - >0 FILMES
	            //fc.lerFilmes();
	            System.out.println("|                                                                                                                                              |");
	            System.out.println("| 1 - Criar uma novo filme.                                                                                                                    |");
	            System.out.println("| 2 - Modificar um filme.                                                                                                                      |");
	            System.out.println("| 3 - Remover um filme.                                                                                                                        |");
	            System.out.println("| 4 - Voltar.                                                                                                                                  |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            opcaoInt = util.leitor(1, 4, 'i');

	            switch(opcaoInt) {
	                case 1:
	                    fc.criarFilme();
	                    //gerenciarFilmes();
	                    break;
	                case 2:
	                    fc.modificarFilme(cinema);
	                    //gerenciarFilmes();
	                    break;
	                case 3:
	                    fc.removerFilme();
	                    //gerenciarFilmes();
	            }
	        }
	    }
	 
	 public void gerenciarSalas(){
	        ArrayList<Sala> salas = cinema.getSalas();
	        
	        //limparTela();

	        if(salas.size() == 0){ //GERENCIAR SALAS - PRIMEIRA VEZ
	            System.out.println("-------------------------------------------");
	            System.out.println("|   -> Voc� est� gerenciando as salas <-  |");
	            System.out.println("|                                         |");
	            System.out.println("| Nenhuma sala foi definida.              |");
	            System.out.println("|                                         |");
	            System.out.println("| 1 - Criar uma nova sala.                |");
	            System.out.println("| 2 - Voltar.                             |");
	            System.out.println("-------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            opcaoInt = util.leitor(1, 2, 'i');

	            if(opcaoInt == 1){
	                sc.criarSalas();
	                gerenciarSalas();
	            }
	        } else { //GERENCIAR SALAS - >0 SALAS
	            //sc.lerSalas();
	            System.out.println("|                                         |");
	            System.out.println("| 1 - Criar uma nova sala.                |");
	            System.out.println("| 2 - Modificar uma sala.                 |");
	            System.out.println("| 3 - Remover uma sala.                   |");
	            System.out.println("| 4 - Voltar.                             |");
	            System.out.println("-------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            opcaoInt = util.leitor(1, 4, 'i');

	            switch(opcaoInt) {
	                case 1:
	                    sc.criarSalas();
	                    gerenciarSalas();
	                    break;
	                case 2:
	                    sc.modificarSala();
	                    gerenciarSalas();
	                    break;
	                case 3:
	                    sc.removerSala();
	                    gerenciarSalas();

	            }
	        }
	    }
	 
}
