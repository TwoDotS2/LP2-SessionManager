/**
 * 
 */
package br.ufrn.imd.view;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import br.ufrn.imd.model.Cinema;
import br.ufrn.imd.model.Filme;
import br.ufrn.imd.model.Sala;
import br.ufrn.imd.model.Sessao;

public class CinemaUtil {
	    private static boolean aberto = false;
	    private static Scanner scanner = new Scanner(System.in);
	    private static Cinema cinema = new Cinema();
	    private static int opcaoInt=0;
	    private static double opcaoDouble=0.0;
	    private static String temp="";
	    private static boolean loop = false;
	    private static int ponteiro=-1;
	    private static DecimalFormat df = new DecimalFormat("0.00");
	    private static DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("HH:mm");

	    public static void main(String[] args){

	        do {
	            limparTela();

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
	                leitor(1, 6, 'i');

	                switch(opcaoInt) {
	                    case 1:
	                        venderIngresso();
	                        opcaoInt = 0; //Evita do programa ser encerrado, caso o caixa venda a poltrona 5.
	                        break;
	                    case 2:
	                        cancelarVenda();
	                        opcaoInt = 0; //Evita do program ser encerrado, caso o caixa cancele a venda da poltrona 5.
	                        break;
	                    case 3:
	                        lerSessoes();
	                        break;
	                    case 4:
	                        lerFaturamento();
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
	                        leitor(1, 2, 'i');
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
	                        leitor(1, 2, 'i');
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
	                leitor(1, 5, 'i');

	                switch(opcaoInt) {
	                    case 1:
	                        gerenciarSessoes();
	                        break;
	                    case 2:
	                        gerenciarSalas();
	                        break;
	                    case 3:
	                        gerenciarFilmes();
	                        break;
	                    case 4:
	                        if(cinema.getSessoes().size() == 0) { //Nenhuma sess�o criada.
	                            System.out.println("� necess�rio criar uma sess�o antes de iniciar as vendas.");
	                            pausar(); 
	                              
	                        } else {
	                            System.out.println("\n-------------------------------------------------------------------------------");
	                            System.out.println("|                    Tem certeza que deseja continuar?                        |");
	                            System.out.println("|                                                                             |");
	                            System.out.println("| 1 - Sim                                                                     |");
	                            System.out.println("| 2 - N�o                                                                     |");
	                            System.out.println("-------------------------------------------------------------------------------");

	                            System.out.println("\nAp�s iniciar as vendas, n�o ser� poss�vel modificar e nem criar sess�es.");

	                            System.out.print("Op��o: ");
	                            leitor(1, 2, 'i');
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
	                        leitor(1, 2, 'i');
	                        if(opcaoInt == 1){
	                            opcaoInt = 5;
	                        }
	                }
	            }

	        } while(opcaoInt!=5);
	    }

	    /*M�TODOS DE GERENCIAMENTO*/
	    public static void gerenciarSessoes(){
	        atualizarSessoes();
	        limparTela();

	        if(cinema.getSessoes().size() == 0) {

	            System.out.println("------------------------------------------------------");
	            System.out.println("|           -> Gerenciamento de sess�es <-           |");
	            System.out.println("|                                                    |");
	            System.out.println("| Nenhuma sess�o foi criada.                         |");
	            System.out.println("|                                                    |");
	            System.out.println("| 1 - Criar uma nova sess�o.                         |");
	            System.out.println("| 2 - Voltar.                                        |");
	            System.out.println("------------------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            leitor(1, 2, 'i');

	            if(opcaoInt == 1){
	                limparTela();
	                criarSessao();
	                gerenciarSessoes();
	            }
	                    
	        } else {
	            lerSessoes();
	            System.out.println("----------------------------------------------------------------------------------------------------------------");
	            System.out.println("|                                                                                                              |");
	            System.out.println("| 1 - Criar uma nova sess�o.                                                                                   |");
	            System.out.println("| 2 - Modificar uma sess�o.                                                                                    |");
	            System.out.println("| 3 - Remover uma sess�o.                                                                                      |");
	            System.out.println("| 4 - Voltar.                                                                                                  |");
	            System.out.println("----------------------------------------------------------------------------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            leitor(1, 4, 'i');

	            switch(opcaoInt) {
	                case 1:
	                    criarSessao();
	                    gerenciarSessoes();
	                    break;
	                case 2:
	                    modificarSessao();
	                    gerenciarSessoes();
	                    break;
	                case 3:
	                    removerSessao();
	                    gerenciarSessoes();
	            }
	        }
	    }

	    public static void lerSessoes() {
	        atualizarSessoes();
	        ArrayList<Sessao> sessoes = cinema.getSessoes();

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
	                if(sessao.getExibicao3D()) {
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
	            pausar();
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
	                    if(sessao.getExibicao3D()) {
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

	    public static void criarSessao(){

	        if(cinema.getFilmes().size() == 0) {
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhum filme foi registrado, � necess�rio registrar o primeiro.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");

	            criarFilme();
	        }

	        if(cinema.getSalas().size() == 0) {
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                 -> Voc� est� criando uma nova sess�o <-                     |");
	            System.out.println("|                                                                             |");
	            System.out.println("| Como nenhuma sala foi registrada, � necess�rio registrar a primeira.        |");
	            System.out.println("|                                                                             |");
	            System.out.println("-------------------------------------------------------------------------------");

	            criarSalas();
	        }

	        lerFilmes();

	        Filme filme;
	        do{ 
	            System.out.print("\nDigite o n�mero correspondente ao filme que ser� exibido nesta sess�o: ");
	            leitor(1, cinema.getFilmes().size(), 'i');
	            filme = cinema.getFilmes().get(opcaoInt-1);
	            if((LocalTime.now().toSecondOfDay() + filme.getDuracao() * 60) > 86399){
	                System.out.print("\nAs sess�es n�o devem ultrapassar as 23:59.");
	                System.out.print("\nDigite 0 para definir um novo filme ou 1 para continuar: ");
	                leitor(0,1, 'i');
	                if(opcaoInt==0) {
	                    criarFilme();
	                }
	                loop = true;
	            }
	        } while (loop);

	        lerSalas();

	        System.out.print("\nDigite o n�mero correspondente � sala que ocorrer� a exibi��o da sess�o: ");
	        leitor(1, cinema.getSalas().size(), 'i');
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
	            leitor(0, 23, 'i');
	            hora = opcaoInt;

	            System.out.print("\nAgora, informe os minutos (0 - 59): ");
	            leitor(0, 59, 'i');
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
	        leitor(1, filme.getTipoAudio().length, 'i');
	        String tipoAudio = filme.getTipoAudio()[opcaoInt-1];

	        boolean exibicao3D = false;
	        if(filme.getPermite3D()){
	            System.out.println("\n-------------------------------------------------------------------------------");
	            System.out.println("|                    O filme ser� reproduzido em 3D?                          |");
	            System.out.println("|                                                                             |");
	            System.out.println("| 1 - Sim                                                                     |");
	            System.out.println("| 2 - N�o                                                                     |");
	            System.out.println("-------------------------------------------------------------------------------");

	            System.out.print("Op��o: ");
	            leitor(1, 2, 'i');
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
	        leitor(1, 1000, 'd');
	        double valorIngresso = opcaoDouble;
	        if(exibicao3D) {
	            valorIngresso *= 1.25;
	        }

	        Sessao sessao = new Sessao(filme, sala, horarioInicial, horarioFinal, valorIngresso, exibicao3D, tipoAudio);
	        cinema.novaSessao(sessao);

	    }

	    public static void modificarSessao() {

	        if(ponteiro == -1){
	            System.out.print("\nQual sess�o deseja modificar? ");
	            leitor(1, cinema.getSessoes().size(), 'i');
	            ponteiro = opcaoInt-1;
	        }

	        Sessao sessao = cinema.getSessoes().get(ponteiro);

	        //Formata��o filme
	        String tituloFilme = sessao.getFilme().getTitulo();
	        for(int f = tituloFilme.length(); f < 44; f++) {
	            tituloFilme += " ";
	        }

	        //Formata��o sala
	        String sala = sessao.getSala().getNumSala() + "";
	        for(int s = sala.length(); s < 45; s++) {
	            sala += " ";
	        }

	        //Formata��o hor�rio in�cio
	        String horaInicio = sessao.getHorarioInicial().format(formataHora) + "                                      ";

	        //Formata��o hor�rio final
	        String horaFinal = sessao.getHorarioFinal().format(formataHora) + "                                         ";

	        //Formata��o tipo de �udio
	        String audio = sessao.getTipoAudio();
	        for(int a = audio.length(); a < 36; a++) {
	            audio += " ";
	        }

	        //Formata��o 3D
	        String permite3D = "Exibi��o em 2D                   ";
	        if(sessao.getExibicao3D()) {
	            permite3D = "Exibi��o em 3D                   ";
	        }

	        //Formata��o valor
	        String valor = df.format(sessao.getValorIngresso());
	        for(int v = valor.length(); v < 30; v++) {
	            valor += " ";
	        }

	        System.out.println("\n------------------------------------------------------");
	        System.out.println("|             -> Modificando sess�o <-               |");
	        System.out.println("|                                                    |");
	        System.out.println("| Filme: " + tituloFilme + "|");
	        System.out.println("| Sala: " + sala + "|");
	        System.out.println("| Inic�o: " + horaInicio + "|");
	        System.out.println("| Fim: " + horaFinal + "|");
	        System.out.println("| Tipo de �udio: " + audio + "|");
	        System.out.println("| Tipo de exibi��o: " + permite3D + "|");
	        System.out.println("| Valor do ingresso: R$" + valor + "|");
	        System.out.println("------------------------------------------------------");
	        System.out.println("|                                                    |");
	        System.out.println("| 1 - Alterar filme.                                 |");
	        System.out.println("| 2 - Alterar sala.                                  |");
	        System.out.println("| 3 - Alterar hor�rio.                               |");
	        System.out.println("| 4 - Alterar o tipo de �udio.                       |");
	        System.out.println("| 5 - Alterar o tipo de exibi��o (3D ou 2D).         |");
	        System.out.println("| 6 - Alterar valor do ingresso.                     |");
	        System.out.println("| 7 - Voltar.                                        |");
	        System.out.println("------------------------------------------------------");

	        System.out.print("Op��o: ");
	        leitor(1, 7, 'i');
	        switch(opcaoInt){
	            case 1:
	                lerFilmes();
	                System.out.print("\nDigite o n�mero correspondente ao filme que ser� exibido nesta sess�o: ");
	                leitor(1, cinema.getFilmes().size(), 'i');

	                sessao.setFilme(cinema.getFilmes().get(opcaoInt-1));

	                modificarSessao();    
	                break;
	            case 2:
	                lerSalas();
	                System.out.print("\nDigite o n�mero correspondente � sala que ocorrer� a exibi��o da sess�o: ");
	                leitor(1, cinema.getSalas().size(), 'i');

	                sessao.setSala(cinema.getSalas().get(opcaoInt-1));

	                modificarSessao();    
	                break;
	            case 3:
	                int hora;
	                int minuto;
	                LocalTime horarioInicial;
	                LocalTime horarioFinal;
	                do {
	                    loop = false;
	                    System.out.print("\nPor favor, informe a hora que a sess�o ir� acontecer (0 - 23): ");
	                    leitor(0, 23, 'i');
	                    hora = opcaoInt;
	        
	                    System.out.print("\nAgora, informe os minutos (0 - 59): ");
	                    leitor(0, 59, 'i');
	                    minuto = opcaoInt;
	        
	                    horarioInicial = LocalTime.of(hora, minuto, 0, 0);
	                    if((horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60) > 86399){
	                        System.out.print("\nAs sess�es n�o devem ultrapassar as 23:59.");
	                        loop = true;
	                        horarioFinal = horarioInicial;
	                    } else {
	                        horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + sessao.getFilme().getDuracao() * 60);
	                    }
	        
	                    if(horarioInicial.toSecondOfDay() < LocalTime.now().toSecondOfDay()) {
	                        System.out.println("\nAinda n�o podemos voltar no tempo. Por favor, defina um hor�rio depois das " + LocalTime.now().format(formataHora));
	                        loop = true;
	                    }
	        
	                    for (Sessao sessaoDefinida : cinema.getSessoes()) { //Verifica o hor�rio que est� sendo criado com todos os hor�rios j� definidos.
	                        int horarioInicialDefinido = sessao.getHorarioInicial().toSecondOfDay(); //Hor�rio que a sess�o come�a.
	                        int horarioFinalDefinido = horarioInicialDefinido + sessao.getFilme().getDuracao() * 60; //Hor�rio que a sess�o acaba.
	        
	                        if(horarioFinal == horarioInicial) {
	                            break;
	                        }
	        
	                        if(sessaoDefinida.getSala() == sessao.getSala() && sessaoDefinida != sessao) { //As sess�es ocorrem na mesma sala e s�o diferentes.
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

	                sessao.setHorarioInicial(horarioInicial);
	                sessao.setHorarioFinal(horarioFinal);
	                    
	                modificarSessao();
	                break;
	            case 4:
	                System.out.println("\n------------------------------------------------------");
	                System.out.println("|  Qual tipo de �udio ser� reproduzido nesta sess�o? |");
	                System.out.println("|                                                    |");
	        
	                for(int i = 0; i < sessao.getFilme().getTipoAudio().length; i++) {

	                    //Formata��o �udio
	                    String tipoAudio = sessao.getFilme().getTipoAudio()[i];
	                    for(int a = tipoAudio.length(); a < 48; a++) {
	                        tipoAudio += " ";
	                    }
	        
	                    System.out.println("| " + (i+1) + " - " + tipoAudio + "|");
	                }
	        
	                System.out.println("------------------------------------------------------");
	        
	                System.out.print("Op��o: ");

	                leitor(1, sessao.getFilme().getTipoAudio().length, 'i');

	                sessao.setTipoAudio(sessao.getFilme().getTipoAudio()[opcaoInt-1]);

	                modificarSessao();  
	                break;
	            case 5:
	                if(sessao.getFilme().getPermite3D()){
	                    System.out.println("\n------------------------------------------------------");
	                    System.out.println("|           O filme ser� reproduzido em 3D?          |");
	                    System.out.println("|                                                    |");
	                    System.out.println("| 1 - Sim                                            |");
	                    System.out.println("| 2 - N�o                                            |");
	                    System.out.println("------------------------------------------------------");
	                    System.out.println("ATEN��O: Essa modifica��o n�o afeta as sess�es j� registradas.");

	                    System.out.print("\nOp��o: ");
	                    leitor(1, 2, 'i');
	                    sessao.setExibicao3D(false);
	                    if(opcaoInt == 1) {
	                        sessao.setExibicao3D(true);
	                    }

	                } else {
	                    System.out.println("\nO filme que ser� exibido nesta sess�o n�o permite reprodu��o em 3D.");
	                    pausar();
	                }
	                
	                modificarSessao();
	                break;
	            case 6:
	                System.out.println("\n*Lembre-se, se o filme for reproduzido em 3D, haver� um incremento de 25% no valor digitado.");
	                System.out.print("\nDigite o novo valor: ");
	                leitor(1, 1000, 'd');

	                if(sessao.getExibicao3D()) {
	                    opcaoDouble*=1.25;
	                }

	                sessao.setValorIngresso(opcaoDouble);

	                modificarSessao();    
	                break;
	            case 7:
	                ponteiro = -1; //Reseta ponteiro global.
	        }

	    }

	    public static void removerSessao() {
	        System.out.print("\nQual sess�o deseja remover?");
	        leitor(1, cinema.getSessoes().size(), 'i');
	        cinema.removerSessao(cinema.getSessoes().get(opcaoInt-1));
	    }

	    public static void gerenciarSalas(){
	        ArrayList<Sala> salas = cinema.getSalas();
	        
	        limparTela();

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
	            leitor(1, 2, 'i');

	            if(opcaoInt == 1){
	                criarSalas();
	                gerenciarSalas();
	            }
	        } else { //GERENCIAR SALAS - >0 SALAS
	            lerSalas();
	            System.out.println("|                                         |");
	            System.out.println("| 1 - Criar uma nova sala.                |");
	            System.out.println("| 2 - Modificar uma sala.                 |");
	            System.out.println("| 3 - Remover uma sala.                   |");
	            System.out.println("| 4 - Voltar.                             |");
	            System.out.println("-------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            leitor(1, 4, 'i');

	            switch(opcaoInt) {
	                case 1:
	                    criarSalas();
	                    gerenciarSalas();
	                    break;
	                case 2:
	                    modificarSala();
	                    gerenciarSalas();
	                    break;
	                case 3:
	                    removerSala();
	                    gerenciarSalas();

	            }
	        }
	    }

	    public static void lerSalas() {
	        ArrayList<Sala> salas = cinema.getSalas();

	        System.out.println("\n-------------------------------------------");
	        System.out.println("|               SALAS DISPON�VEIS         |");
	        System.out.println("-------------------------------------------");
	        System.out.println("| OPCAO | N� DA SALA | CAPACIDADE DA SALA |");
	        for (int i=0; i < salas.size(); i++ ) {
	            Sala sala = salas.get(i);

	            //Formata��o n�mero
	            String numero = "  " + (i+1) + " "; 
	            for(int n = numero.length(); n < 7; n++) {
	                numero += " ";
	            }
	            
	            //Formata��o sala
	            String numSala = "    " + sala.getNumSala() + " ";
	            for(int s=numSala.length(); s < 12; s++) {
	                numSala += " ";
	            }

	            //Formata��o capacidade
	            String capacidade = "        " + sala.getCapacidade() + " ";
	            for(int c = capacidade.length(); c < 20; c++) {
	                capacidade += " ";
	            }

	            System.out.println("|" + numero + "|" + numSala + "|" + capacidade + "|");
	            System.out.println("-------------------------------------------");
	        }
	    }

	    public static void criarSalas() {

	        System.out.print("\nDigite o n�mero da sala: ");
	        int numSala = 0;
	        do{
	            leitor(1, 1000, 'i');
	            numSala = opcaoInt;
	            
	            loop = false;
	            for (Sala sala : cinema.getSalas()) {
	                if(sala.getNumSala() == numSala){
	                    System.out.print("\nEsta sala j� foi definida, defina outra: ");
	                    loop = true;
	                    break;
	                }
	            }
	        }while(loop);

	        System.out.print("\nDigite a capacidade da sala: ");
	        leitor(1, 1000, 'i');
	        int capacidade = opcaoInt;

	        Sala sala = new Sala(numSala, capacidade);
	        cinema.novaSala(sala);
	    }

	    public static void modificarSala() {

	        if(ponteiro == -1){
	            System.out.print("\nQual sala deseja modificar? ");
	            leitor(0, cinema.getSalas().size(), 'i');
	            ponteiro = opcaoInt-1;
	        }

	        Sala sala = cinema.getSalas().get(ponteiro);

	        //Formata��o n�mero da sala
	        String numSala = sala.getNumSala() + " ";
	        for(int s = numSala.length(); s < 35; s++){
	            numSala += " ";
	        }

	        //Formata��o capacidade
	        String capacidade = sala.getCapacidade() + " ";
	        for(int c = capacidade.length(); c < 31; c++){
	            capacidade += " ";
	        }

	        System.out.println("\n------------------------------------------------------");
	        System.out.println("|               -> Modificando sala <-               |");
	        System.out.println("|                                                    |");
	        System.out.println("| N�mero da sala: " + numSala + "|");
	        System.out.println("| Capacidade da sala: " + capacidade + "|");
	        System.out.println("|                                                    |");
	        System.out.println("| 1 - Alterar n�mero da sala.                        |");
	        System.out.println("| 2 - Alterar capacidade da sala.                    |");
	        System.out.println("| 3 - Voltar.                                        |");
	        System.out.println("------------------------------------------------------");
	        
	        System.out.print("\nOp��o: ");
	        leitor(1, 3, 'i');

	        switch(opcaoInt) {
	            case 1:
	                System.out.print("\nDigite o novo n�mero: ");
	                do{
	                    leitor(1, 1000, 'i');

	                    loop = false;        
	                    for (Sala salaCriada : cinema.getSalas()) {
	                        if(salaCriada.getNumSala() == opcaoInt && salaCriada != sala){ //J� existe uma sala com esse n�mero e n�o � a que est� sendo modificada.
	                            System.out.println("\nEsta sala j� foi definida, defina outra: ");
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
	                leitor(1, 1000, 'i');

	                sala.setCapacidade(opcaoInt);

	                modificarSala();
	                break;
	            case 3:
	                ponteiro = -1; //Reseta ponteiro global.
	        }
	    }

	    public static void removerSala() {

	        System.out.print("\nDigite a op��o correspondente � sala que deseja remover: ");
	        leitor(1, cinema.getSalas().size(), 'i');
	        Sala sala = cinema.getSalas().get(opcaoInt-1);

	        opcaoInt = 1; //A sala ser� removida se opcaoInt == 1.

	        boolean primeiraSessaoAfetada=false;
	        for (Sessao sessao : cinema.getSessoes()) {

	            if(sessao.getSala() == sala) { //Verifica se existem sess�es com esta sala.

	                if(primeiraSessaoAfetada==false){ //Isso faz ele perguntar apenas uma vez (o loop pode encontrar v�rias sess�es).
	                    System.out.println("Existe(m) sess�o(�es) que acontecer�(�o) nesta sala. Ao remover a sala, a(s) sess�o(�es) tamb�m ser�(�o) excluida(s).");
	                    System.out.println("\n1 - Sim\n2 - N�o");
	                    System.out.print("Tem certeza que deseja continuar?");
	                    leitor(1, 2, 'i');

	                    if(opcaoInt == 2) { //opcaoInt == 2, a sala n�o ser� removida.
	                        break;
	                    }

	                    primeiraSessaoAfetada=true;
	                    cinema.removerSessao(sessao);
	                } else {
	                    cinema.removerSessao(sessao);
	                }
	            }
	        }

	        if(opcaoInt == 1) {
	            cinema.removerSala(sala);
	        }
	    }

	    public static void gerenciarFilmes() {
	        ArrayList<Filme> filmes = cinema.getFilmes();

		    limparTela();
	        
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
	            leitor(1, 2, 'i');

	            if(opcaoInt == 1){
	                criarFilme();
	                gerenciarFilmes();
	            }
	        } else { //GERENCIAR FILMES - >0 FILMES
	            lerFilmes();
	            System.out.println("|                                                                                                                                              |");
	            System.out.println("| 1 - Criar uma novo filme.                                                                                                                    |");
	            System.out.println("| 2 - Modificar um filme.                                                                                                                      |");
	            System.out.println("| 3 - Remover um filme.                                                                                                                        |");
	            System.out.println("| 4 - Voltar.                                                                                                                                  |");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

	            System.out.print("\nDigite o n�mero da op��o desejada: ");
	            leitor(1, 4, 'i');

	            switch(opcaoInt) {
	                case 1:
	                    criarFilme();
	                    gerenciarFilmes();
	                    break;
	                case 2:
	                    modificarFilme();
	                    gerenciarFilmes();
	                    break;
	                case 3:
	                    removerFilme();
	                    gerenciarFilmes();
	            }
	        }
	    }

	    public static void lerFilmes() {
	        ArrayList<Filme> filmes = cinema.getFilmes();

	        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println("|                                                              FILMES DISPON�VEIS                                                              |");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println("| N� |                 TITULO                 |                TIPO DE �UDIO               |  TIPO DE PRODU��O  |  DURA��O  |        3D        |");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
	        for(int i = 0; i < filmes.size(); i++){ 
	            Filme filme = filmes.get(i);

	            //Formata��o n�mero
	            String numero;
	            if(i < 9) {
	                numero = " " + (i+1)  + "  ";
	            } else  {
	                numero = " " + (i+1) + " ";
	            }

	            //Formata��o t�tulo
	            String titulo = " " + filme.getTitulo() + " ";
	            for(int f=titulo.length(); f < 40; f++) {
	                titulo += " ";
	            }

	            //Formata��o �udio
	            String audio = " " + Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
	            for(int a = audio.length(); a < 44; a++) {
	                audio += " ";
	            }

	            //Formata��o produ��o
	            String producao = " " + filme.getTipoProducao() + " ";
	            for(int p=producao.length(); p < 20;p++){
	                producao += " ";
	            }
	            
	            //Formata��o 3D
	            String permite3D = "  N�o dispon�vel  ";
	            if(filme.getPermite3D()) {
	                permite3D = "    Dispon�vel    ";
	            }

	            //Formata��o dura��o
	            String duracao = " " + filme.getDuracao() + " ";
	            for(int d = duracao.length(); d < 11; d++){
	                duracao += " ";
	            }

	            System.out.println("|" + numero + "|" + titulo + "|" + audio + "|" + producao + "|" + duracao + "|" + permite3D + "|");
	            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
	        }

	    }

	    public static void criarFilme() {

	        System.out.print("\nDigite o nome do filme: ");
	        do{
	            temp = scanner.nextLine();

	            loop=false;
	            for (Filme filme : cinema.getFilmes()){
	                if(filme.getTitulo().equals(temp)){
	                    System.out.print("\nJ� existe um filme com esse nome, digite outro: ");
	                    loop=true;
	                    break;
	                }
	            }

	        } while(loop);

	        String tituloFilme = temp;

	        System.out.print("\nDigite a dura��o do filme (em minutos): ");
	        leitor(1, 1000, 'i');
	        int duracao = opcaoInt;

	        String[] tipoAudio = tipoAudio();

	        System.out.println("\n-------------------------------------------------------------------------------");
	        System.out.println("|                    Qual o tipo de produ��o do filme?                        |");
	        System.out.println("|                                                                             |");
	        System.out.println("| 1 - Nacional                                                                |");
	        System.out.println("| 2 - Estrangeira                                                             |");
	        System.out.println("-------------------------------------------------------------------------------");

	        System.out.print("\nDigite o n�mero da op��o desejada: ");
	        leitor(1, 2, 'i');
	        String tipoProducao = "Estrangeira";
	        if(opcaoInt == 1){
	            tipoProducao = "Nacional";
	        }

	        System.out.println("\n-------------------------------------------------------------------------------");
	        System.out.println("|                    O filme permite reprodu��o em 3D?                        |");
	        System.out.println("|                                                                             |");
	        System.out.println("| 1 - Sim                                                                     |");
	        System.out.println("| 2 - N�o                                                                     |");
	        System.out.println("-------------------------------------------------------------------------------");

	        System.out.print("\nDigite o n�mero da op��o desejada: ");
	        leitor(1, 2, 'i');
	        boolean permite3D = false;
	        if(opcaoInt == 1){
	            permite3D = true;
	        }

	        Filme filme = new Filme(tituloFilme, duracao, tipoProducao, tipoAudio, permite3D);
	        cinema.novoFilme(filme);
	    }

	    public static void modificarFilme() {

	        if(ponteiro == -1){
	            System.out.print("\nQual filme deseja modificar? ");
	            leitor(0, cinema.getFilmes().size(), 'i');
	            ponteiro = opcaoInt-1;
	        }

	        Filme filme = cinema.getFilmes().get(ponteiro);

	        //Formata��o t�tulo
	        String titulo = filme.getTitulo();
	        for(int t=titulo.length(); t < 62; t++) {
	            titulo += " ";
	        }

	        //Formata��o tipo de �udio
	        String audio = Arrays.toString(filme.getTipoAudio()).replaceAll("\\[", " ").replaceAll("\\]", " ") + " ";
	        for(int a = audio.length(); a < 42; a++) {
	            audio += " ";
	        }

	        //Formata��o tipo de produ��o
	        String producao = filme.getTipoProducao() ;
	        for(int p = producao.length(); p < 52; p++) {
	            producao += " ";
	        }

	        //Formata��o dura��o
	        String duracao = filme.getDuracao() + "";
	        for(int d = duracao.length(); d < 61; d++) {
	            duracao += " ";
	        }

	        //Formata��o 3D
	        String permite3D = "N�o dispon�vel                                 ";
	        if(filme.getPermite3D()) {
	            permite3D = "Dispon�vel                                     ";
	        }

	        System.out.println("-------------------------------------------------------------------------");
	        System.out.println("|                       -> Modificando filme <-                         |");
	        System.out.println("|                                                                       |");
	        System.out.println("| T�tulo: " + titulo + "|");
	        System.out.println("| Tipos de �udio dispon�veis: " + audio + "|");
	        System.out.println("| Tipo de produ��o: " + producao + "|");
	        System.out.println("| Dura��o: " + duracao + "|");
	        System.out.println("| Disponibilidade em 3D: " + permite3D + "|");
	        System.out.println("-------------------------------------------------------------------------");
	        System.out.println("|                                                                       |");
	        System.out.println("| 1 - Alterar o t�tulo.                                                 |");
	        System.out.println("| 2 - Alterar o tipo de �udio.                                          |");
	        System.out.println("| 3 - Alterar o tipo de produ��o.                                       |");
	        System.out.println("| 4 - Alterar a dura��o.                                                |");
	        System.out.println("| 5 - Alterar a disponibilidade de 3D.                                  |");
	        System.out.println("| 6 - Voltar.                                                           |");
	        System.out.println("-------------------------------------------------------------------------");
	        

	        System.out.print("\nOp��o: ");
	        leitor(1, 6, 'i');

	        switch(opcaoInt) {
	            case 1:
	                System.out.print("\nDigite o novo nome do filme: ");
	                boolean loop=false;
	                do{
	                    temp = scanner.nextLine();
	                    
	                    for (Filme filmeCriado : cinema.getFilmes()) {
	                        if(filmeCriado.getTitulo() == temp) {
	                            System.out.print("\nJ� existe um filme com esse nome, digite outro: ");
	                            loop=true;
	                            break;
	                        }
	                    }

	                } while(loop);

	                filme.setTitulo(temp);

	                modificarFilme();
	                break;
	            case 2: 
	                System.out.println("\nATEN��O: Essa modifica��o n�o altera a informa��o j� registrada em alguma sess�o.");
	                filme.setTipoAudio(tipoAudio());

	                modificarFilme();
	                break;
	            case 3:
	                System.out.println("\n-------------------------------------------------------------------------");
	                System.out.println("|                  Qual o tipo de produ��o do filme?                    |");
	                System.out.println("|                                                                       |");
	                System.out.println("| 1 - Nacional                                                          |");
	                System.out.println("| 2 - Estrangeira                                                       |");
	                System.out.println("-------------------------------------------------------------------------");
	        
	                System.out.print("\nOp��o: ");
	                leitor(1, 2, 'i');
	                filme.setTipoProducao("Estrangeira");
	                if(opcaoInt == 1) {
	                    filme.setTipoProducao("Nacional");
	                }

	                modificarFilme();
	                break;
	            case 4:
	                System.out.println("\nATEN��O: Essa modifica��o n�o altera a informa��o j� registrada em alguma sess�o.");
	                System.out.print("\nDigite a nova dura��o do filme (em minutos): ");
	                leitor(1, 1000, 'i');
	                
	                filme.setDuracao(opcaoInt);
	                
	                modificarFilme();
	                break;
	            case 5:
	            System.out.println("\n-----------------------------------------------------------------------");
	            System.out.println("|                  O filme permite reprodu��o em 3D?                    |");
	            System.out.println("|                                                                       |");
	            System.out.println("| 1 - Sim                                                               |");
	            System.out.println("| 2 - N�o                                                               |");
	            System.out.println("-------------------------------------------------------------------------");
	            System.out.println("\nATEN��O: Essa modifica��o altera a informa��o j� registrada em alguma sess�o.");

	                System.out.print("\nOp��o: ");
	                leitor(1, 2, 'i');

	                if(opcaoInt == 1) {
	                    filme.setPermite3D(true);
	                } else {
	                    for (Sessao sessao : cinema.getSessoes()) {
	                        if(sessao.getFilme() == filme && sessao.getExibicao3D()){
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
	        leitor(1, cinema.getFilmes().size(), 'i');
	        Filme filme = cinema.getFilmes().get(opcaoInt-1);

	        opcaoInt = 1; //O filme ser� removido se opcaoInt == 1.

	        boolean primeiraSessaoAfetada=false;
	        for (Sessao sessao : cinema.getSessoes()) {

	            if(sessao.getFilme() == filme){ //Verifica se existem sess�es com este filme.

	                if(primeiraSessaoAfetada==false){ //Faz a pergunta apenas uma primeira vez (o loop pode encontrar v�rias sess�es).
	                    System.out.println("Existe(m) sess�o(�es) que ir�(�o) exibir este filme. Ao remover o filme, a(s) sess�o(�es) tamb�m ser�(�o) excluida(s).");
	                    System.out.println("\n1 - Sim\n2 - N�o");
	                    System.out.print("Tem certeza que deseja continuar?");
	                    leitor(1, 2, 'i');

	                    if(opcaoInt == 2) { //O filme n�o ser� apagado, pois opcaoInt == 2
	                        break;
	                    }

	                    primeiraSessaoAfetada=true;
	                    cinema.removerSessao(sessao);

	                } else {
	                    cinema.removerSessao(sessao);
	                }
	            }
	        }

	        if(opcaoInt == 1) {
	            cinema.removerFilme(filme);
	        }
	    }
	    /*M�TODOS DE GERENCIAMENTO*/

	    /*M�TODOS FINANCEIROS*/
	    public static void venderIngresso() {

	        lerSessoes();
	        do {
	            loop = false;
	            System.out.print("\nDeseja vender um ingresso para qual sess�o? ");
	            leitor(1, cinema.getSessoes().size(), 'i');
	            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 1) {
	                System.out.print("\nEssa sess�o n�o possui poltronas dispon�veis. ");
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

	        System.out.print("\nOp��o: ");
	        leitor(1, 2, 'i');
	        char tipoIngresso = 'm';
	        if(opcaoInt == 1) {
	            tipoIngresso = 'i';
	        }
	        
	        do {
	            System.out.println(sessao.poltronasLivres());

	            System.out.print("\nDigite o n�mero da poltrona: ");
	            leitor(1, sessao.getSala().getCapacidade(), 'i');

	            if(cinema.venderIngresso(sessao, tipoIngresso, opcaoInt-1)) {
	                break;
	            } else {
	                System.out.println("\nEssa poltrona j� foi vendida!");
	                pausar();
	            }

	        } while(true);

	        System.out.println("Venda realizada com sucesso!");
	        pausar();
	    }

	    public static void cancelarVenda() {

	        lerSessoes();
	        do {
	            loop = false;
	            System.out.print("\nDeseja cancelar venda para qual sess�o? ");
	            leitor(1, cinema.getSessoes().size(), 'i');
	            if(cinema.getSessoes().get(opcaoInt-1).taxaOcupacao() == 0) {
	                System.out.print("\nNenhum ingresso foi vendido para essa sess�o.. ");
	                loop = true;
	            }
	        } while (loop);

	        
	        Sessao sessao = cinema.getSessoes().get(opcaoInt-1);

	        do {
	            System.out.print(sessao.poltronasOcupadas());

	            System.out.print("\nQual a poltrona a ser liberada? ");
	            leitor(1, sessao.getSala().getCapacidade(), 'i');

	            if(cinema.cancelarVenda(sessao, opcaoInt-1)) {
	                break;
	            } else {
	                System.out.println("\nEssa poltrona j� foi vendida!");
	                pausar();
	            }
	        } while (true);

	        System.out.println("Venda Cancelada!");
	        pausar();
	    }

	    public static void lerFaturamento() {
	        System.out.println("\n                             > Sess�es 3D < ");
	        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras3D());
		    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras3D()));
	        System.out.println("Total de meio ingressos vendidos: " + cinema.getIngressosMeias3D());
		    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias3D()));
	        System.out.println("\n                             > Sess�es 2D < ");
	        System.out.println("\nTotal de ingressos inteiros vendidos: " + cinema.getIngressosInteiras());
		    System.out.println("Total de ingressos inteiros vendidos (em reais): " + df.format(cinema.getFaturamentoInteiras()));
	        System.out.println("Total de meio ingresso vendidos: " + cinema.getIngressosMeias());
		    System.out.println("Total de meio ingressos vendidos (em reais): " + df.format(cinema.getFaturamentoMeias()));
	        pausar();

	    }
	    /*M�TODOS FINANCEIROS*/

	    /*M�TODOS DE APOIO*/
	    public static void leitor(int opcaoMin, int opcaoMax, Character tipoEntrada) {
	        if(tipoEntrada.equals('i')) { //Quero receber um inteiro.
	            do {
	                temp = scanner.nextLine();

	                try {
	                    opcaoInt = Integer.parseInt(temp);

	                    if(opcaoInt >= opcaoMin && opcaoInt <= opcaoMax) { 
	                        break;
	                    } else {
	                        System.out.print("\nOp��o inv�lida, tente novamente: "); //O valor digitado n�o est� entre 1-quantidadeOpcoes.
	                    }

	                } catch (NumberFormatException e) {
	                    System.out.print("\nValor inv�lido, tente novamente: "); //N�o � um inteiro.
	                }

	            } while(true);

	        } else { //Quero receber um double.
	            do {
	                temp = scanner.nextLine();

	                try {
	                    opcaoDouble = Double.parseDouble(temp);

	                    if(opcaoDouble >= 0 && opcaoDouble <= opcaoMax) { 
	                        break;
	                    } else {
	                        System.out.print("\nOp��o inv�lida, tente novamente: "); //O valor digitado n�o est� entre 1-quantidadeOpcoes.
	                    }

	                } catch (NumberFormatException e) {
	                    System.out.print("\nValor inv�lido, tente novamente: "); //N�o � um double.
	                }

	            } while(true);

	        }
	    }
	    
	    public static void atualizarSessoes() {
	        LocalTime horarioAtual = LocalTime.now();

	        for (int i = 0; i < cinema.getSessoes().size(); i++) {
	            if(cinema.getSessoes().get(i).getHorarioFinal().toSecondOfDay() < horarioAtual.toSecondOfDay()) {
	                cinema.removerSessao(cinema.getSessoes().get(i));
	            }
	        }

	    }

	    public static void limparTela() {
	        for(int i=0; i<100; i++) {
	            System.out.println();
	        }
	    }

	    public static void pausar() {
	        System.out.print("\nAperte enter para continuar...");
	        temp = scanner.nextLine();
	    }

	    public static String[] tipoAudio(){
	        String[] tipoAudio;

	        System.out.println("\n-------------------------------------------------------------------------------");
	        System.out.println("|           O filme possui qual(is) tipo(s) de audio dispon�vel(is)?          |");
	        System.out.println("|                                                                             |");
	        System.out.println("| 1 - Original                                                                |");
	        System.out.println("| 2 - Original com legenda                                                    |");
	        System.out.println("| 3 - Dublado                                                                 |");
	        System.out.println("| 4 - Original e original com legenda                                         |");
	        System.out.println("| 5 - Original e dublado                                                      |");
	        System.out.println("| 6 - Original com legenda e dublado                                          |");
	        System.out.println("| 7 - Original, original com legenda e dublado                                |");
	        System.out.println("-------------------------------------------------------------------------------");
	        
	        System.out.print("\nDigite o n�mero da op��o desejada: ");
	        leitor(1, 7, 'i');
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
	    /*M�TODOS DE APOIO*/
}

