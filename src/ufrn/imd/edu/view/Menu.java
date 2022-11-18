package ufrn.imd.edu.view;

import ufrn.imd.edu.controller.Cinema;
import ufrn.imd.edu.controller.Sala;
import ufrn.imd.edu.controller.Stringfy;
import ufrn.imd.edu.model.Sessao;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Stringfy stringfy;

    public Menu(){
        stringfy = new Stringfy();
    }
    void runMenu(Cinema cinema){

        Scanner menu = new Scanner (System.in);

        while (true) {
            System.out.print("##------Gestão de Sessão------##\n\n");
            System.out.print("|--------------------------------------------|\n");
            System.out.print("|  1 - Ver Todas Sessões Por Sala            |\n");
            System.out.print("|  2 - Ver Todos Filmes em catálogo (wip)    |\n");
            System.out.print("|  3 - Buscar Filme (wip)                    |\n");
            System.out.print("|  0 - Sair                                  |\n");
            System.out.print("|--------------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.print("\nAté logo!");
                System.exit(1);
            }

            switch (opcao) {

                case 1:
                    System.out.println("Todas as Sessões Disponíveis em cada sala");
                    System.out.println(stringfy.sessoesEmCadaSala(cinema));
                    break;

                case 2:
                    System.out.print("\nWork In Progress!\n");
                    break;

                case 3:
                    System.out.print("\nWork In Progress!\n");
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;
            }
        }
    }
}

