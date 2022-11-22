package ufrn.imd.edu.view;

import ufrn.imd.edu.controller.Cinema;
import ufrn.imd.edu.controller.Sala;
import ufrn.imd.edu.controller.Stringfy;
import ufrn.imd.edu.model.Filme;
import ufrn.imd.edu.model.Sessao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Aplicacao {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Stringfy stringfy = new Stringfy();
        Menu menu = new Menu();
        String auxHorario;

        //Gerar duas salas, - 1 e 2 - com 100 lugares.
        cinema.gerarSalas(2, 10, 10);

        Filme f1 = new Filme(
                "Avatar: O Caminho da Água",
                "Não, não é o filme do careca."
        );

        Calendar horario = Calendar.getInstance();

        //Horário de s1
        {
            horario.set(Calendar.YEAR, 2022);
            horario.set(Calendar.MONTH, Calendar.DECEMBER);
            horario.set(Calendar.DAY_OF_MONTH, 25);
            horario.set(Calendar.HOUR_OF_DAY, 17);
            horario.set(Calendar.MINUTE, 0);
            auxHorario = sdf.format(horario.getTime());
            System.out.println(auxHorario + "\n");
        }
        Sessao s1 = new Sessao(1, horario, f1);

        //Horário de s2
        {
            horario = Calendar.getInstance();
            horario.set(Calendar.YEAR, 2023);
            horario.set(Calendar.MONTH, Calendar.JANUARY);
            horario.set(Calendar.DAY_OF_MONTH, 15);
            horario.set(Calendar.HOUR_OF_DAY, 14);
            horario.set(Calendar.MINUTE, 45);
            auxHorario = sdf.format(horario.getTime());
            System.out.println(auxHorario + "\n");

        }
        Sessao s2 = new Sessao(1, horario, f1);

        //Horário de s3
        {
            horario = Calendar.getInstance();
            horario.set(Calendar.YEAR, 2023);
            horario.set(Calendar.MONTH, Calendar.JANUARY);
            horario.set(Calendar.DAY_OF_MONTH, 20);
            horario.set(Calendar.HOUR_OF_DAY, 18);
            horario.set(Calendar.MINUTE, 45);
            auxHorario = sdf.format(horario.getTime());
            System.out.println(auxHorario + "\n");

        }
        Sessao s3 = new Sessao(2, horario, f1);

        //Horário de s4
        {
            horario = Calendar.getInstance();
            horario.set(Calendar.YEAR, 2023);
            horario.set(Calendar.MONTH, Calendar.FEBRUARY);
            horario.set(Calendar.DAY_OF_MONTH, 14);
            horario.set(Calendar.HOUR_OF_DAY, 19);
            horario.set(Calendar.MINUTE, 30);
            auxHorario = sdf.format(horario.getTime());
            System.out.println(auxHorario + "\n");
        }

        Sessao s4 = new Sessao(2, horario, f1);

        //Cadastrando sessões
        {
            cinema.cadastrarSessao(s1);
            cinema.cadastrarSessao(s2);
            cinema.cadastrarSessao(s3);
            cinema.cadastrarSessao(s4);
        }



        System.out.println(s1.getId());
        System.out.println(s2.getId());
        System.out.println(s3.getId());
        System.out.println(s4.getId());

        while (true) {
            //Rodar os menus
            menu.runMenu(cinema);
        }
    }



}
