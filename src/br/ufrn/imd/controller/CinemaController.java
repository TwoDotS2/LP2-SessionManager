package br.ufrn.imd.controller;

import java.text.DecimalFormat;

import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Sessao;

public class CinemaController {
	

	 private Cinema cinema = new Cinema();
	 private CinemaUtil util = new CinemaUtil();
	 private boolean loop = false;
	 private int opcaoInt=0;
	 private DecimalFormat df = new DecimalFormat("0.00");
	
	 
	public void venderIngresso(Cinema cinema) {

        //lerSessoes();
        do {
            loop = false;
            System.out.print("\nDeseja vender um ingresso para qual sessão? ");
            opcaoInt = util.leitor(1, cinema.getSessoes().size(), 'i');
            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 1) {
                System.out.print("\nEssa sessão não possui poltronas disponíveis. ");
                loop = true;
            }
        } while (loop);

        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        System.out.println("\n------------------------------------------------------");
        System.out.println("|          -> Qual o tipo de ingresso? <-            |");
        System.out.println("|                                                    |");
        System.out.println("| 1 - Inteiro.                                       |");
        System.out.println("| 2 - Meio.                                          |");
        System.out.println("------------------------------------------------------");

        System.out.print("\nOpção: ");
        opcaoInt = util.leitor(1, 2, 'i');
        char tipoIngresso = 'm';
        if(opcaoInt == 1) {
            tipoIngresso = 'i';
        }
        
        do {
            System.out.println(sessao.poltronasLivres());

            System.out.print("\nDigite o número da poltrona: ");
            opcaoInt = util.leitor(1, sessao.getSala().getCapacidade(), 'i');

            if(cinema.venderIngresso(sessao, tipoIngresso, opcaoInt-1)) {
                break;
            } else {
                System.out.println("\nEssa poltrona já foi vendida!");
                util.pausar();
            }

        } while(true);

        System.out.println("Venda realizada com sucesso!");
        util.pausar();
    }

    public void cancelarVenda() {

        //lerSessoes();
        do {
            loop = false;
            System.out.print("\nDeseja cancelar venda para qual sessão? ");
            opcaoInt = util.leitor(1, cinema.getSessoes().size(), 'i');
            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 0) {
                System.out.print("\nNenhum ingresso foi vendido para essa sessão.. ");
                loop = true;
            }
        } while (loop);

        
        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

        do {
            System.out.print(sessao.poltronasOcupadas());

            System.out.print("\nQual a poltrona a ser liberada? ");
            opcaoInt = util.leitor(1, sessao.getSala().getCapacidade(), 'i');

            if(cinema.cancelarVenda(sessao, opcaoInt-1)) {
                break;
            } else {
                System.out.println("\nEssa poltrona já foi vendida!");
                util.pausar();
            }
        } while (true);

        System.out.println("Venda Cancelada!");
        util.pausar();
    }

    public void lerFaturamento(Cinema cinema) {
        System.out.println("\n                             > Sessões 3D < ");
        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras3D());
	    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras3D()));
        System.out.println("Total de meio ingressos vendidos: " + cinema.getIngressosMeias3D());
	    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias3D()));
        System.out.println("\n                             > Sessões 2D < ");
        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras());
	    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras()));
        System.out.println("Total de meio ingresso vendidos: " + cinema.getIngressosMeias());
	    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias()));
        util.pausar();

    }
}
