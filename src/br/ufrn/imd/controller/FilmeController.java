package br.ufrn.imd.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Filme;
import br.ufrn.imd.model.Sessao;

public class FilmeController {
	 private static Scanner scanner = new Scanner(System.in);
	 private static Cinema cinema = new Cinema();
	 private static CinemaUtil util = new CinemaUtil();
	 private static String temp="";
	 private static boolean loop = false;
	 private static int opcaoInt=0;
	 private static int ponteiro=-1;
	
	public void criarFilme() {
		
		 System.out.print("\nDigite o nome do filme: ");
		 
		 do{
	            temp = scanner.nextLine();

	            loop=false;
	            for (Filme filme : cinema.getFilmes()){
	                if(filme.getTitulo().equals(temp)){
	                    System.out.print("\nJá existe um filme com esse nome, digite outro: ");
	                    loop=true;
	                    break;
	                }
	            }

	        } while(loop);
		 
			 String tituloFilme = temp;
	
			 System.out.print("\nDigite a duração do filme (em minutos): ");
			 opcaoInt = util.leitor(1, 1000, 'i');
			 int duracao = opcaoInt;
			 
			 String[] tipoAudio = util.tipoAudio();            // tipoAudio();
			 
			 System.out.println("\n-------------------------------------------------------------------------------");
		        System.out.println("|                    Qual o tipo de produção do filme?                        |");
		        System.out.println("|                                                                             |");
		        System.out.println("| 1 - Nacional                                                                |");
		        System.out.println("| 2 - Estrangeira                                                             |");
		        System.out.println("-------------------------------------------------------------------------------");

		        System.out.print("\nDigite o número da opção desejada: ");
		        opcaoInt = util.leitor(1, 2, 'i');
		        String tipoProducao = "Estrangeira";
		        if(opcaoInt == 1){
		            tipoProducao = "Nacional";
		        }

		        System.out.println("\n-------------------------------------------------------------------------------");
		        System.out.println("|                    O filme permite reprodução em 3D?                        |");
		        System.out.println("|                                                                             |");
		        System.out.println("| 1 - Sim                                                                     |");
		        System.out.println("| 2 - Não                                                                     |");
		        System.out.println("-------------------------------------------------------------------------------");

		        System.out.print("\nDigite o número da opção desejada: ");
		        opcaoInt = util.leitor(1, 2, 'i');
		        boolean permite3D = false;
		        if(opcaoInt == 1){
		            permite3D = true;
		        }
		        
		        Filme filme = new Filme(tituloFilme, tipoProducao, duracao, tipoAudio, permite3D);
		        cinema.addFilmes(filme);
	}
	
	public void lerFilmes() {
        ArrayList<Filme> filmes = cinema.getFilmes();

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                              FILMES DISPONÍVEIS                                                              |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Nº |                 TITULO                 |                TIPO DE ÁUDIO               |  TIPO DE PRODUÇÃO  |  DURAÇÃO  |        3D        |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0; i < filmes.size(); i++){ 
            Filme filme = filmes.get(i);

            //Formatação número
            String numero;
            if(i < 9) {
                numero = " " + (i+1)  + "  ";
            } else  {
                numero = " " + (i+1) + " ";
            }

            //Formatação título
            String titulo = " " + filme.getTitulo() + " ";
            for(int f=titulo.length(); f < 40; f++) {
                titulo += " ";
            }

            //Formatação áudio
            String audio = " " + Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
            for(int a = audio.length(); a < 44; a++) {
                audio += " ";
            }

            //Formatação produção
            String producao = " " + filme.getTipoProducao() + " ";
            for(int p=producao.length(); p < 20;p++){
                producao += " ";
            }
            
            //Formatação 3D
            String permite3D = "  Não disponível  ";
            if(filme.isPermite3D()) {
                permite3D = "    Disponível    ";
            }

            //Formatação duração
            String duracao = " " + filme.getDuracao() + " ";
            for(int d = duracao.length(); d < 11; d++){
                duracao += " ";
            }

            System.out.println("|" + numero + "|" + titulo + "|" + audio + "|" + producao + "|" + duracao + "|" + permite3D + "|");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }
	
	public void modificarFilme() {

        if(ponteiro == -1){
            System.out.print("\nQual filme deseja modificar? \n");
            opcaoInt = util.leitor(1, cinema.getFilmes().size(), 'i');
            ponteiro = opcaoInt - 1;
        }
        Filme filme = cinema.getFilmes().get(ponteiro);
        
        //Formatação título
        String titulo = filme.getTitulo();
        for(int t=titulo.length(); t < 62; t++) {
            titulo += " ";
        }

        //Formatação tipo de áudio
        String audio = Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
        for(int a = audio.length(); a < 42; a++) {
            audio += " ";
        }

        //Formatação tipo de produção
        String producao = filme.getTipoProducao() ;
        for(int p = producao.length(); p < 52; p++) {
            producao += " ";
        }

        //Formatação duração
        String duracao = filme.getDuracao() + "";
        for(int d = duracao.length(); d < 61; d++) {
            duracao += " ";
        }

        //Formatação 3D
        String permite3D = "Não disponível                                 ";
        if(filme.isPermite3D()) {
            permite3D = "Disponível                                     ";
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                       -> Modificando filme <-                         |");
        System.out.println("|                                                                       |");
        System.out.println("| Título: " + titulo + "|");
        System.out.println("| Tipos de áudio disponíveis: " + audio + "|");
        System.out.println("| Tipo de produção: " + producao + "|");
        System.out.println("| Duração: " + duracao + "|");
        System.out.println("| Disponibilidade em 3D: " + permite3D + "|");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                                                                       |");
        System.out.println("| 1 - Alterar o título.                                                 |");
        System.out.println("| 2 - Alterar o tipo de áudio.                                          |");
        System.out.println("| 3 - Alterar o tipo de produção.                                       |");
        System.out.println("| 4 - Alterar a duração.                                                |");
        System.out.println("| 5 - Alterar a disponibilidade de 3D.                                  |");
        System.out.println("| 6 - Voltar.                                                           |");
        System.out.println("-------------------------------------------------------------------------");
        

        System.out.print("\nOpção: ");
        opcaoInt = util.leitor(1, 6, 'i');

        switch(opcaoInt) {
            case 1:
                System.out.print("\nDigite o novo nome do filme: ");
                boolean loop=false;
                do{
                    temp = scanner.nextLine();
                    
                    for (Filme filmeCriado : cinema.getFilmes()) {
                        if(filmeCriado.getTitulo() == temp) {
                            System.out.print("\nJá existe um filme com esse nome, digite outro: ");
                            loop=true;
                            break;
                        }
                    }

                } while(loop);

                filme.setTitulo(temp);

                modificarFilme();
                break;
            case 2: 
                System.out.println("\nATENÇÃO: Essa modificação não altera a informação já registrada em alguma sessão.");
                filme.setTipoAudio(util.tipoAudio());

                modificarFilme();
                break;
            case 3:
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.println("|                  Qual o tipo de produção do filme?                    |");
                System.out.println("|                                                                       |");
                System.out.println("| 1 - Nacional                                                          |");
                System.out.println("| 2 - Estrangeira                                                       |");
                System.out.println("-------------------------------------------------------------------------");
        
                System.out.print("\nOpção: ");
                opcaoInt = util.leitor(1, 2, 'i');
                filme.setTipoProducao("Estrangeira");
                if(opcaoInt == 1) {
                    filme.setTipoProducao("Nacional");
                }

                modificarFilme();
                break;
            case 4:
                System.out.println("\nATENÇÃO: Essa modificação não altera a informação já registrada em alguma sessão.");
                System.out.print("\nDigite a nova duração do filme (em minutos): ");
                opcaoInt = util.leitor(1, 1000, 'i');
                
                filme.setDuracao(opcaoInt);
                
                modificarFilme();
                break;
            case 5:
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("|                  O filme permite reprodução em 3D?                    |");
            System.out.println("|                                                                       |");
            System.out.println("| 1 - Sim                                                               |");
            System.out.println("| 2 - Não                                                               |");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\nATENÇÃO: Essa modificação altera a informação já registrada em alguma sessão.");

                System.out.print("\nOpção: ");
                opcaoInt = util.leitor(1, 2, 'i');

                if(opcaoInt == 1) {
                    filme.setPermite3D(true);
                } else {
                    for (Sessao sessao : cinema.getSessoes()) {
                        if(sessao.getFilme() == filme && sessao.isExibicao3D()){
                            sessao.setExibicao3D(false);
                        }
                    }
                    filme.setPermite3D(false);
                }

                modificarFilme();
                break;
            case 6:
                ponteiro = -1; //Reseta ponteiro global.
        }

    }
	
	public static void removerFilme() {

        System.out.print("\nQual filme deseja remover? ");
        opcaoInt = util.leitor(1, cinema.getFilmes().size(), 'i');
        Filme filme = cinema.getFilmes().get(opcaoInt-1);

        opcaoInt = 1; //O filme será removido se opcaoInt == 1.

        boolean primeiraSessaoAfetada=false;
        for (Sessao sessao : cinema.getSessoes()) {

            if(sessao.getFilme() == filme){ //Verifica se existem sessões com este filme.

                if(primeiraSessaoAfetada==false){ //Faz a pergunta apenas uma primeira vez (o loop pode encontrar várias sessões).
                    System.out.println("Existe(m) sessão(ões) que irá(ão) exibir este filme. Ao remover o filme, a(s) sessão(ões) também será(ão) excluida(s).");
                    System.out.println("\n1 - Sim\n2 - Não");
                    System.out.print("Tem certeza que deseja continuar?");
                    opcaoInt = util.leitor(1, 2, 'i');

                    if(opcaoInt == 2) { //O filme não será apagado, pois opcaoInt == 2
                        break;
                    }

                    primeiraSessaoAfetada=true;
                    //cinema.removerSessao(sessao);

                } else {
                    //cinema.removerSessao(sessao);
                }
            }
        }

        if(opcaoInt == 1) {
            cinema.removerFilme(filme);
        }
    }
    /*MÉTODOS DE GERENCIAMENTO*/

}
