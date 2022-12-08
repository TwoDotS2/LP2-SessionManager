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
	            System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhum filme foi registrado, � necess�rio registrar o primeiro.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");
	            
	            fc.criarFilme();
	            
	     }
		 if(cinema.getSalas().size() == 0) {
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhuma sala foi registrada, � necess�rio registrar a primeira.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");

	            sc.criarSalas();
	          
	     }
		
		 fc.lerFilmes(cinema.getFilmes());
		 Filme filme;
		 
		 do{ 
	            System.out.print("\nDigite o n�mero correspondente ao filme que ser� exibido nesta sess�o: ");
	            opcaoInt = util.leitor(1, cinema.getFilmes().size(), 'i');
	            filme = cinema.getFilmes().get(opcaoInt-1);
	            if((LocalTime.now().toSecondOfDay() + filme.getDuracao() * 60) > 86399){
	                System.out.print("\nAs sess�es n�o devem ultrapassar as 23:59.");
	                System.out.print("\nDigite 0 para definir um novo filme ou 1 para continuar: ");
	                opcaoInt = util.leitor(0,1, 'i');
	                if(opcaoInt==0) {
	                    fc.criarFilme();
	                }
	                loop = false;
	            }
	        } while (loop);
		 
		 //sc.lerSalas();
		 
		 System.out.print("\nDigite o n�mero correspondente � sala que ocorrer� a exibi��o da sess�o: ");
		 opcaoInt = util.leitor(1, cinema.getSalas().size(), 'i');
	     Sala sala = cinema.getSalas().get(opcaoInt-1);
	     
	     //Formata��o t�tulo
        String titulo = filme.getTitulo() + " ";
        for(int f=filme.getTitulo().length(); f < 68; f++) {
            titulo += " ";
        }

        //Formata��o n�mero da sala
        String numSala = sala.getNumSala() + " ";
        for(int s = numSala.length(); s < 70; s++) {
            numSala += " ";
        }
        
        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo                                                        + "|");
        System.out.println("| Sala: " + numSala                                                        + "|");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\n� necess�rio definir o hor�rio da sess�o.");
        int hora;
        int minuto;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        do {
            loop = false;
            System.out.print("\nPor favor, informe a hora que a sess�o ir� acontecer (0 - 23): ");
            opcaoInt = util.leitor(0, 23, 'i');
            hora = opcaoInt;

            System.out.print("\nAgora, informe os minutos (0 - 59): ");
            opcaoInt = util.leitor(0, 59, 'i');
            minuto = opcaoInt;

            horarioInicial = LocalTime.of(hora, minuto, 0, 0);
            if((horarioInicial.toSecondOfDay() + filme.getDuracao() * 60) > 86399){
                System.out.print("\nAs sess�es n�o devem ultrapassar as 23:59.");
                loop = true;
                horarioFinal = horarioInicial;
            } else {
                horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + filme.getDuracao() * 60);
            }

            if(horarioInicial.toSecondOfDay() < LocalTime.now().toSecondOfDay()) {
                System.out.println("\nAinda n�o podemos voltar no tempo. Por favor, defina um hor�rio depois das " + LocalTime.now().format(formataHora));
                loop = true;
            }

            for (Sessao sessao : cinema.getSessoes()) { //Verifica o hor�rio que est� sendo criado com todos os hor�rios j� definidos.
                int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Hor�rio que a sess�o come�a.
                int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Hor�rio que a sess�o acaba.

                if(horarioFinal == horarioInicial) {
                    break;
                }

                if(sessao.getSala() == sala) { //Sess�o que ocorre na mesma sala.
                    if(horarioInicialDefinido - horarioInicial.toSecondOfDay() == 0){ //Hor�rio encontrado � igual o hor�rio que est� sendo definido.
                        System.out.print("\nJ� existe uma sess�o definida para este hor�rio, defina outro.");
                        loop = true;
                        break;
                    } else if(horarioInicial.toSecondOfDay() > horarioInicialDefinido && horarioInicial.toSecondOfDay() < horarioFinalDefinido) { //O usu�rio est� tentando definir uma sess�o pra um hor�rio que j� est� ocorrendo uma outra sess�o.
                        System.out.println("\nUma sess�o estar� sendo exibida neste hor�rio. O hor�rio definido � conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if(Math.abs(horarioFinalDefinido - horarioInicial.toSecondOfDay()) < 1200 || Math.abs(horarioInicialDefinido - horarioFinal.toSecondOfDay()) < 1200) { //O intervalo entre cada sess�o precisa ser de 20 minutos.
                        System.out.println("\nUma sess�o s� pode ocorrer 20 minutos ap�s a outra. O hor�rio definido � conflitante, defina outro.");
                        loop = true;
                        break;
                    } else if (horarioInicialDefinido > horarioInicial.toSecondOfDay() && horarioInicialDefinido < horarioFinal.toSecondOfDay()){ //O hor�rio inicial da sess�o + o tempo do filme ultrapassam o hor�rio inicial da pr�xima sess�o.
                        System.out.println("\nEssa sess�o n�o vai acabar antes da pr�xima. O hor�rio definido � conflitante, defina outro.");
                        loop = true;
                        break;
                    }
                }
            }
        } while(loop);

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("|               Qual tipo de �udio ser� reproduzido nesta sess�o?             |");
        System.out.println("|                                                                             |");

        for(int i = 0; i < filme.getTipoAudio().length; i++) {
            //Formata��o �udio
            String tipoAudio = filme.getTipoAudio()[i];
            for(int a = tipoAudio.length(); a < 72; a++) {
                tipoAudio += " ";
            }

            System.out.println("| " + (i+1) + " - " + tipoAudio + "|");
        }

        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("Op��o: ");
        opcaoInt = util.leitor(1, filme.getTipoAudio().length, 'i');
        String tipoAudio = filme.getTipoAudio()[opcaoInt-1];

        boolean exibicao3D = false;
        if(filme.isPermite3D()){
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.println("|                    O filme ser� reproduzido em 3D?                          |");
            System.out.println("|                                                                             |");
            System.out.println("| 1 - Sim                                                                     |");
            System.out.println("| 2 - N�o                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");

            System.out.print("Op��o: ");
            opcaoInt = util.leitor(1, 2, 'i');
            if(opcaoInt == 1) {
                exibicao3D = true;
            }
        }

        //Formata��o hor�rio in�cio
        String horaInicio = horarioInicial.format(formataHora) + "                                                    ";

        //Formata��o hor�rio final
        String horaFinal = horarioFinal.format(formataHora) + "                                                       ";

        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Filme: " + titulo +                                                        "|");
        System.out.println("| Sala: " + numSala +                                                        "|");
        System.out.println("| Horario de in�cio: " + horaInicio +                                        "|");
        System.out.println("| Horario de fim: " + horaFinal +                                            "|");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\n*Lembre-se, se o filme for reproduzido em 3D, haver� um incremento de 25% no valor digitado.");

        System.out.print("\nQual o valor do ingresso da sess�o? ");
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
	            System.out.println("|                                                 SESS�ES DISPON�VEIS                                                              |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	            System.out.println("| N� |                 FILME                 | SALA | IN�CIO |  FIM  |        �UDIO         |  3D  |   VALOR   |  TAXA DE OCUPA��O |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	            for(int i = 0; i < sessoes.size(); i++) {
	                Sessao sessao = sessoes.get(i);
	                
	                //Formata��o n�mero
	                String numero;
	                if(i < 9) {
	                    numero = "  " + (i+1)  + " ";
	                } else  {
	                    numero = " " + (i+1) + " ";
	                }

	                //Formata��o filme
	                String filme = " " + sessao.getFilme().getTitulo() + " ";
	                for(int f=filme.length(); f < 39; f++) {
	                    filme += " ";
	                }

	                //Formata��o sala
	                String sala;
	                if(sessao.getSala().getNumSala() < 10) {
	                    sala = "   " + sessao.getSala().getNumSala() + "  ";
	                } else {
	                    sala = "  " + sessao.getSala().getNumSala() + "  ";
	                }

	                //Formata��o inicio
	                String inicio = "  " + sessao.getHorarioInicial() + " ";

	                //Formata��o fim
	                String fim = " " + sessao.getHorarioFinal() + " ";

	                //Formata��o �udio
	                String audio = " " + sessao.getTipoAudio() + " ";
	                for(int a = audio.length(); a < 22; a++) {
	                    audio += " ";
	                }

	                String permite3D = " N�o  ";
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
	            System.out.println("|                                              SESS�ES DISPON�VEIS                                             |");
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            System.out.println("| N� |                 FILME                 | SALA | IN�CIO |  FIM  |        �UDIO         |  3D  |   VALOR   |");
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            for(int i = 0; i < sessoes.size(); i++) {
	                Sessao sessao = sessoes.get(i);

	                //Formata��o n�mero
	                String numero;
	                    if(i < 9) {
	                        numero = "  " + (i+1)  + " ";
	                    } else  {
	                        numero = " " + (i+1) + " ";
	                    }

	                //Formata��o filme
	                String filme = " " + sessao.getFilme().getTitulo() + " ";
	                    for(int f=filme.length(); f < 39; f++) {
	                        filme += " ";
	                    }

	                //Formata��o sala
	                String sala;
	                    if(sessao.getSala().getNumSala() < 10) {
	                        sala = "   " + sessao.getSala().getNumSala() + "  ";
	                    } else {
	                        sala = "  " + sessao.getSala().getNumSala() + "  ";
	                    }

	                //Formata��o inicio
	                String inicio = "  " + sessao.getHorarioInicial() + " ";

	                //Formata��o fim
	                String fim = " " + sessao.getHorarioFinal() + " ";

	                //Formata��o audio
	                String audio = " " + sessao.getTipoAudio() + " ";
	                    for(int a = audio.length(); a < 22; a++) {
	                        audio += " ";
	                    }
	                
	                //Formata��o 3d
	                String permite3D = " N�o  ";
	                    if(sessao.isExibicao3D()) {
	                        permite3D = " Sim  ";
	                    }

	                //Formata��o valor
	                String valor = " R$" + df.format(sessao.getValorIngresso()) + "  ";
	                    for(int v=valor.length(); v < 11; v++) {
	                        valor += " ";
	                    }
	            
	                System.out.println("|" + numero + "|" + filme + "|" + sala + "|" + inicio + "|" + fim + "|" + audio + "|" + permite3D + "|" + valor + "|");
	            }
	        }    
	    }
}

