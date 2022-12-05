package br.ufrn.imd.controller;

import java.time.LocalTime;
import java.util.Scanner;

import br.ufrn.imd.model.Cinema;

public class CinemaUtil {
		private static Cinema cinema = new Cinema();
	 	private static Scanner scanner = new Scanner(System.in);
	 	private static CinemaUtil util = new CinemaUtil();
	    private static int opcaoInt=0;
	    private static double opcaoDouble=0.0;
	    private static String temp="";
	    
	public int leitor(int opcaoMin, int opcaoMax, Character tipoEntrada) {
		 if(tipoEntrada.equals('i')) { //Quero receber um inteiro.
	            do {
	                temp = scanner.nextLine();
	                
	                try {
	                    opcaoInt = Integer.parseInt(temp);

	                    if(opcaoInt >= opcaoMin && opcaoInt <= opcaoMax) {
	                    	return opcaoInt;
	                    } else {
	                        System.out.print("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
	                    }

	                } catch (NumberFormatException e) {
	                    System.out.print("\nValor inválido, tente novamente: "); //Não é um inteiro.
	                }
	                
	            } while(true);

	        } else { //Quero receber um double.
	            do {
	                temp = scanner.nextLine();

	                try {
	                    opcaoDouble = Double.parseDouble(temp);

	                    if(opcaoDouble >= 0 && opcaoDouble <= opcaoMax) { 
	                    	return (int) opcaoDouble;
	                    } else {
	                        System.out.print("\nOpção inválida, tente novamente: "); //O valor digitado não está entre 1-quantidadeOpcoes.
	                    }

	                } catch (NumberFormatException e) {
	                    System.out.print("\nValor inválido, tente novamente: "); //Não é um double.
	                }

	            } while(true);

	        }
	}
	 public String[] tipoAudio(){
	        String[] tipoAudio;

	        System.out.println("\n-------------------------------------------------------------------------------");
	        System.out.println("|           O filme possui qual(is) tipo(s) de audio disponível(is)?          |");
	        System.out.println("|                                                                             |");
	        System.out.println("| 1 - Original                                                                |");
	        System.out.println("| 2 - Original com legenda                                                    |");
	        System.out.println("| 3 - Dublado                                                                 |");
	        System.out.println("| 4 - Original e original com legenda                                         |");
	        System.out.println("| 5 - Original e dublado                                                      |");
	        System.out.println("| 6 - Original com legenda e dublado                                          |");
	        System.out.println("| 7 - Original, original com legenda e dublado                                |");
	        System.out.println("-------------------------------------------------------------------------------");
	        
	        System.out.print("\nDigite o número da opção desejada: ");
	        util.leitor(1, 7, 'i');
	        if(opcaoInt < 4) {
	            tipoAudio = new String[1];
	        } else if (opcaoInt < 7){
	            tipoAudio = new String[2];
	        } else {
	            tipoAudio = new String[3];
	        }

	        switch (opcaoInt) {
	            case 1:
	                tipoAudio[0] = "Original";
	                break;
	            case 2:
	                tipoAudio[0] = "Original com legenda";
	                break;
	            case 3:
	                tipoAudio[0] = "Dublado";
	                break;
	            case 4:
	                tipoAudio[0] = "Original";
	                tipoAudio[1] = "Original com legenda";
	                break;
	            case 5:
	                tipoAudio[0] = "Original";
	                tipoAudio[1] = "Dublado";
	                break;
	            case 6:
	                tipoAudio[0] = "Original com legenda";
	                tipoAudio[1] = "Dublado";
	                break;
	            case 7:
	                tipoAudio[0] = "Original";
	                tipoAudio[1] = "Original com legenda";
	                tipoAudio[2] = "Dublado";
	        }

	        return tipoAudio;
	    }
	 
	 public void atualizarSessoes() {
	        LocalTime horarioAtual = LocalTime.now();

	        for (int i = 0; i < cinema.getSessoes().size(); i++) {
	            if(cinema.getSessoes().get(i).getHorarioFinal().toSecondOfDay() < horarioAtual.toSecondOfDay()) {
	                cinema.removerSessao(cinema.getSessoes().get(i));
	            }
	        }

	    }

	    public void limparTela() {
	        for(int i=0; i<100; i++) {
	            System.out.println();
	        }
	    }

	    public void pausar() {
	        System.out.print("\nAperte enter para continuar...");
	        temp = scanner.nextLine();
	    }
}
