package br.ufrn.imd.controller;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Filme;
import br.ufrn.imd.model.Sala;
import br.ufrn.imd.model.Sessao;

public class SessaoController {
	
	 private boolean aberto = false;
	 private Cinema cinema = new Cinema();
	 private CinemaUtil util = new CinemaUtil();
	 private FilmeController fc = new FilmeController();
	 private SalaController sc = new SalaController();
	 private boolean loop = false;
	 private int opcaoInt=0;
	 private double opcaoDouble=0.0;
	 private DecimalFormat df = new DecimalFormat("0.00");
	 private DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("HH:mm");
	 
	public Sessao criarSessao(Cinema cinema) {
		
		
		
		 if(cinema.getFilmes().size() == 0) {
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhum filme foi registrado, é necessário registrar o primeiro.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");
	            
	            fc.criarFilme();
	            
	     }
		 if(cinema.getSalas().size() == 0) {
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhuma sala foi registrada, é necessário registrar a primeira.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");

	            sc.criarSalas();
	          
	     }
		
		 fc.lerFilmes(cinema.getFilmes());
		 Filme filme;
		 
		 do{ 
	            System.out.print("\nDigite o número correspondente ao filme que será exibido nesta sessão: ");
	            opcaoInt = util.leitor(1, cinema.getFilmes().size(), 'i');
	            filme = cinema.getFilmes().get(opcaoInt-1);
	            if((LocalTime.now().toSecondOfDay() + filme.getDuracao() * 60) > 86399){
	                System.out.print("\nAs sessões não devem ultrapassar as 23:59.");
	                System.out.print("\nDigite 0 para definir um novo filme ou 1 para continuar: ");
	                opcaoInt = util.leitor(0,1, 'i');
	                if(opcaoInt==0) {
	                    fc.criarFilme();
	                }
	                loop = false;
	            }
	        } while (loop);
		 
		 //sc.lerSalas();
		 
		 System.out.print("\nDigite o número correspondente à sala que ocorrerá a exibição da sessão: ");
		 opcaoInt = util.leitor(1, cinema.getSalas().size(), 'i');
	     Sala sala = cinema.getSalas().get(opcaoInt-1);
	     
	     //Formatação título
        String titulo = filme.getTitulo() + " ";
        for(int f=filme.getTitulo().length(); f < 68; f++) {
            titulo += " ";
        }

        //Formatação número da sala
        String numSala = sala.getNumSala() + " ";
        for(int s = numSala.length(); s < 70; s++) {
            numSala += " ";
        }
        
        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo                                                        + "|");
        System.out.println("| Sala: " + numSala                                                        + "|");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\nÉ necessário definir o horário da sessão.");
        int hora;
        int minuto;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        do {
            loop = false;
            System.out.print("\nPor favor, informe a hora que a sessão irá acontecer (0 - 23): ");
            opcaoInt = util.leitor(0, 23, 'i');
            hora = opcaoInt;

            System.out.print("\nAgora, informe os minutos (0 - 59): ");
            opcaoInt = util.leitor(0, 59, 'i');
            minuto = opcaoInt;

            horarioInicial = LocalTime.of(hora, minuto, 0, 0);
            if((horarioInicial.toSecondOfDay() + filme.getDuracao() * 60) > 86399){
                System.out.print("\nAs sessões não devem ultrapassar as 23:59.");
                loop = true;
                horarioFinal = horarioInicial;
            } else {
                horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + filme.getDuracao() * 60);
            }

            if(horarioInicial.toSecondOfDay() < LocalTime.now().toSecondOfDay()) {
                System.out.println("\nAinda não podemos voltar no tempo. Por favor, defina um horário depois das " + LocalTime.now().format(formataHora));
                loop = true;
            }

            for (Sessao sessao : cinema.getSessoes()) { //Verifica o horário que está sendo criado com todos os horários já definidos.
                int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Horário que a sessão começa.
                int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Horário que a sessão acaba.

                if(horarioFinal == horarioInicial) {
                    break;
                }

                if(sessao.getSala() == sala) { //Sessão que ocorre na mesma sala.
                    if(horarioInicialDefinido - horarioInicial.toSecondOfDay() == 0){ //Horário encontrado é igual o horário que está sendo definido.
                        System.out.print("\nJá existe uma sessão definida para este horário, defina outro.");
                        loop = true;
                        break;
                    } else if(horarioInicial.toSecondOfDay() > horarioInicialDefinido && horarioInicial.toSecondOfDay() < horarioFinalDefinido) { //O usuário está tentando definir uma sessão pra um horário que já está ocorrendo uma outra sessão.
                        System.out.println("\nUma sessão estará sendo exibida neste horário. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if(Math.abs(horarioFinalDefinido - horarioInicial.toSecondOfDay()) < 1200 || Math.abs(horarioInicialDefinido - horarioFinal.toSecondOfDay()) < 1200) { //O intervalo entre cada sessão precisa ser de 20 minutos.
                        System.out.println("\nUma sessão só pode ocorrer 20 minutos após a outra. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if (horarioInicialDefinido > horarioInicial.toSecondOfDay() && horarioInicialDefinido < horarioFinal.toSecondOfDay()){ //O horário inicial da sessão + o tempo do filme ultrapassam o horário inicial da próxima sessão.
                        System.out.println("\nEssa sessão não vai acabar antes da próxima. O horário definido é conflitante, defina outro.");
                        loop = true;
                        break;
                    }
                }
            }
        } while(loop);

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("|               Qual tipo de áudio será reproduzido nesta sessão?             |");
        System.out.println("|                                                                             |");

        for(int i = 0; i < filme.getTipoAudio().length; i++) {
            //Formatação áudio
            String tipoAudio = filme.getTipoAudio()[i];
            for(int a = tipoAudio.length(); a < 72; a++) {
                tipoAudio += " ";
            }

            System.out.println("| " + (i+1) + " - " + tipoAudio + "|");
        }

        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("Opção: ");
        opcaoInt = util.leitor(1, filme.getTipoAudio().length, 'i');
        String tipoAudio = filme.getTipoAudio()[opcaoInt-1];

        boolean exibicao3D = false;
        if(filme.isPermite3D()){
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("|                    O filme será reproduzido em 3D?                          |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Sim                                                                     |");
            System.out.println("| 2 - Não                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("Opção: ");
            opcaoInt = util.leitor(1, 2, 'i');
            if(opcaoInt == 1) {
                exibicao3D = true;
            }
        }

        //Formatação horário início
        String horaInicio = horarioInicial.format(formataHora) + "                                                    ";

        //Formatação horário final
        String horaFinal = horarioFinal.format(formataHora) + "                                                       ";

        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Você está criando uma nova sessão <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo +                                                        "|");
        System.out.println("| Sala: " + numSala +                                                        "|");
        System.out.println("| Horario de início: " + horaInicio +                                        "|");
        System.out.println("| Horario de fim: " + horaFinal +                                            "|");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\n*Lembre-se, se o filme for reproduzido em 3D, haverá um incremento de 25% no valor digitado.");

        System.out.print("\nQual o valor do ingresso da sessão? ");
        opcaoDouble = util.leitor(1, 1000, 'd');
        double valorIngresso = opcaoDouble;
        if(exibicao3D) {
            valorIngresso *= 1.25;
        }

        Sessao sessao = new Sessao(filme, sala, horarioInicial, horarioFinal, valorIngresso, exibicao3D, tipoAudio);
       // cinema.addSessoes(sessao);
        return sessao;

    }
	
	public void atualizarSessoes() {
        LocalTime horarioAtual = LocalTime.now();

        for (int i = 0; i < cinema.getSessoes().size(); i++) {
            if(cinema.getSessoes().get(i).getHorarioFinal().toSecondOfDay() < horarioAtual.toSecondOfDay()) {
                cinema.removerSessao(cinema.getSessoes().get(i));
            }
        }

    }
	
	 public void lerSessoes(ArrayList<Sessao> sessoes) {
	        atualizarSessoes();
	     

	        if(aberto) {
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	            System.out.println("|                                                 SESSÕES DISPONÍVEIS                                                              |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	            System.out.println("| Nº |                 FILME                 | SALA | INÍCIO |  FIM  |        ÁUDIO         |  3D  |   VALOR   |  TAXA DE OCUPAÇÃO |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	            for(int i = 0; i < sessoes.size(); i++) {
	                Sessao sessao = sessoes.get(i);
	                
	                //Formatação número
	                String numero;
	                if(i < 9) {
	                    numero = "  " + (i+1)  + " ";
	                } else  {
	                    numero = " " + (i+1) + " ";
	                }

	                //Formatação filme
	                String filme = " " + sessao.getFilme().getTitulo() + " ";
	                for(int f=filme.length(); f < 39; f++) {
	                    filme += " ";
	                }

	                //Formatação sala
	                String sala;
	                if(sessao.getSala().getNumSala() < 10) {
	                    sala = "   " + sessao.getSala().getNumSala() + "  ";
	                } else {
	                    sala = "  " + sessao.getSala().getNumSala() + "  ";
	                }

	                //Formatação inicio
	                String inicio = "  " + sessao.getHorarioInicial() + " ";

	                //Formatação fim
	                String fim = " " + sessao.getHorarioFinal() + " ";

	                //Formatação áudio
	                String audio = " " + sessao.getTipoAudio() + " ";
	                for(int a = audio.length(); a < 22; a++) {
	                    audio += " ";
	                }

	                String permite3D = " Não  ";
	                if(sessao.isExibicao3D()) {
	                    permite3D = " Sim  ";
	                }

	                String valor = " R$" + df.format(sessao.getValorIngresso()) + "  ";
	                for(int v=valor.length(); v < 11; v++) {
	                    valor += " ";
	                }

	                String taxa = " " + df.format(sessao.taxaOcupacao() * 100) + "% ";
	                for(int t = taxa.length(); t < 19; t++) {
	                    taxa += " ";
	                }

	                System.out.println("|" + numero + "|" + filme + "|" + sala + "|" + inicio + "|" + fim + "|" + audio + "|" + permite3D + "|" + valor + "|" + taxa + "|");
	            }
	            util.pausar();
	        } else {
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            System.out.println("|                                              SESSÕES DISPONÍVEIS                                             |");
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            System.out.println("| Nº |                 FILME                 | SALA | INÍCIO |  FIM  |        ÁUDIO         |  3D  |   VALOR   |");
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            for(int i = 0; i < sessoes.size(); i++) {
	                Sessao sessao = sessoes.get(i);

	                //Formatação número
	                String numero;
	                    if(i < 9) {
	                        numero = "  " + (i+1)  + " ";
	                    } else  {
	                        numero = " " + (i+1) + " ";
	                    }

	                //Formatação filme
	                String filme = " " + sessao.getFilme().getTitulo() + " ";
	                    for(int f=filme.length(); f < 39; f++) {
	                        filme += " ";
	                    }

	                //Formatação sala
	                String sala;
	                    if(sessao.getSala().getNumSala() < 10) {
	                        sala = "   " + sessao.getSala().getNumSala() + "  ";
	                    } else {
	                        sala = "  " + sessao.getSala().getNumSala() + "  ";
	                    }

	                //Formatação inicio
	                String inicio = "  " + sessao.getHorarioInicial() + " ";

	                //Formatação fim
	                String fim = " " + sessao.getHorarioFinal() + " ";

	                //Formatação audio
	                String audio = " " + sessao.getTipoAudio() + " ";
	                    for(int a = audio.length(); a < 22; a++) {
	                        audio += " ";
	                    }
	                
	                //Formatação 3d
	                String permite3D = " Não  ";
	                    if(sessao.isExibicao3D()) {
	                        permite3D = " Sim  ";
	                    }

	                //Formatação valor
	                String valor = " R$" + df.format(sessao.getValorIngresso()) + "  ";
	                    for(int v=valor.length(); v < 11; v++) {
	                        valor += " ";
	                    }
	            
	                System.out.println("|" + numero + "|" + filme + "|" + sala + "|" + inicio + "|" + fim + "|" + audio + "|" + permite3D + "|" + valor + "|");
	            }
	        }    
	    }
}

