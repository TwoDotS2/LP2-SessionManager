package br.ufrn.imd.controller;

import java.util.ArrayList;

import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Sala;
import br.ufrn.imd.model.Sessao;

public class SalaController {
	
	 private static Cinema cinema = new Cinema();
	 private static CinemaUtil util = new CinemaUtil();
	 private static boolean loop = false;
	 private static int opcaoInt=0;
	 private static int ponteiro=-1;
	 
	public void criarSalas() {

        System.out.print("\nDigite o número da sala: ");
        int numSala = 0;
        do{
        	opcaoInt = util.leitor(1, 1000, 'i');
            numSala = opcaoInt;
            
            loop = false;
            for (Sala sala : cinema.getSalas()) {
                if(sala.getNumSala() == numSala){
                    System.out.print("\nEsta sala já foi definida, defina outra: ");
                    loop = true;
                    break;
                }
            }
        }while(loop);

        System.out.print("\nDigite a capacidade da sala: ");
        opcaoInt = util.leitor(1, 1000, 'i');
        int capacidade = opcaoInt;

        Sala sala = new Sala(numSala, capacidade);
        cinema.addSala(sala);
    }
	
	 public void lerSalas() {
	        ArrayList<Sala> salas = cinema.getSalas();

	        System.out.println("\n-------------------------------------------");
	        System.out.println("|               SALAS DISPONÍVEIS         |");
	        System.out.println("-------------------------------------------");
	        System.out.println("| OPCAO | Nº DA SALA | CAPACIDADE DA SALA |");
	        for (int i=0; i < salas.size(); i++ ) {
	            Sala sala = salas.get(i);

	            //Formatação número
	            String numero = "  " + (i+1) + " "; 
	            for(int n = numero.length(); n < 7; n++) {
	                numero += " ";
	            }
	            
	            //Formatação sala
	            String numSala = "    " + sala.getNumSala() + " ";
	            for(int s=numSala.length(); s < 12; s++) {
	                numSala += " ";
	            }

	            //Formatação capacidade
	            String capacidade = "        " + sala.getCapacidade() + " ";
	            for(int c = capacidade.length(); c < 20; c++) {
	                capacidade += " ";
	            }

	            System.out.println("|" + numero + "|" + numSala + "|" + capacidade + "|");
	            System.out.println("-------------------------------------------");
	        }
	    }
	 
	 public void modificarSala() {

	        if(ponteiro == -1){
	            System.out.print("\nQual sala deseja modificar? ");
	            opcaoInt = util.leitor(0, cinema.getSalas().size(), 'i');
	            ponteiro = opcaoInt-1;
	        }

	        Sala sala = cinema.getSalas().get(ponteiro);

	        //Formatação número da sala
	        String numSala = sala.getNumSala() + " ";
	        for(int s = numSala.length(); s < 35; s++){
	            numSala += " ";
	        }

	        //Formatação capacidade
	        String capacidade = sala.getCapacidade() + " ";
	        for(int c = capacidade.length(); c < 31; c++){
	            capacidade += " ";
	        }

	        System.out.println("\n------------------------------------------------------");
	        System.out.println("|               -> Modificando sala <-               |");
	        System.out.println("|                                                    |");
	        System.out.println("| Número da sala: " + numSala + "|");
	        System.out.println("| Capacidade da sala: " + capacidade + "|");
	        System.out.println("|                                                    |");
	        System.out.println("| 1 - Alterar número da sala.                        |");
	        System.out.println("| 2 - Alterar capacidade da sala.                    |");
	        System.out.println("| 3 - Voltar.                                        |");
	        System.out.println("------------------------------------------------------");
	        
	        System.out.print("\nOpção: ");
	        opcaoInt = util.leitor(1, 3, 'i');

	        switch(opcaoInt) {
	            case 1:
	                System.out.print("\nDigite o novo número: ");
	                do{
	                	opcaoInt = util.leitor(1, 1000, 'i');

	                    loop = false;        
	                    for (Sala salaCriada : cinema.getSalas()) {
	                        if(salaCriada.getNumSala() == opcaoInt && salaCriada != sala){ //Já existe uma sala com esse número e não é a que está sendo modificada.
	                            System.out.println("\nEsta sala já foi definida, defina outra: ");
	                            loop = true;
	                            break;
	                        }
	                    }

	                }while(loop);

	                sala.setNumSala(opcaoInt);
	                
	                modificarSala();
	                break;
	            case 2:
	                System.out.print("\nDigite a nova capacidade: ");
	                opcaoInt = util.leitor(1, 1000, 'i');

	                sala.setCapacidade(opcaoInt);

	                modificarSala();
	                break;
	            case 3:
	                ponteiro = -1; //Reseta ponteiro global.
	        }
	    }
	 
	 public static void removerSala() {

	        System.out.print("\nDigite a opção correspondente à sala que deseja remover: ");
	        opcaoInt = util.leitor(1, cinema.getSalas().size(), 'i');
	        Sala sala = cinema.getSalas().get(opcaoInt-1);

	        opcaoInt = 1; //A sala será removida se opcaoInt == 1.

	        boolean primeiraSessaoAfetada=false;
	        for (Sessao sessao : cinema.getSessoes()) {

	            if(sessao.getSala() == sala) { //Verifica se existem sessões com esta sala.

	                if(primeiraSessaoAfetada==false){ //Isso faz ele perguntar apenas uma vez (o loop pode encontrar várias sessões).
	                    System.out.println("Existe(m) sessão(ões) que acontecerá(ão) nesta sala. Ao remover a sala, a(s) sessão(ões) também será(ão) excluida(s).");
	                    System.out.println("\n1 - Sim\n2 - Não");
	                    System.out.print("Tem certeza que deseja continuar?");
	                    opcaoInt = util.leitor(1, 2, 'i');

	                    if(opcaoInt == 2) { //opcaoInt == 2, a sala não será removida.
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
	            cinema.removerSala(sala);
	        }
	    }
}
