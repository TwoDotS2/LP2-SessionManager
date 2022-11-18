package ufrn.imd.edu.controller;

import ufrn.imd.edu.model.Sessao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Stringfy {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public final String sessoesEmCadaSala(Cinema cinema){
        //Variaveis auxiliares
        Sessao s;   String str = "";     UUID id;
        HashMap<Integer, ArrayList<UUID>> sessoesPorSala = cinema.getSessaoEmSala();
        ArrayList<Integer> listaNumSalas = cinema.getListaNumSalas();

        for(Integer i: listaNumSalas){
            str += "Sala " + i + ":\n";

            for(Integer j = 0; j < sessoesPorSala.get(i).size(); j++){
                //Acessar UUID dentro do ArrayList
                id = sessoesPorSala.get(i).get(j);
                s = cinema.getSessaoPorId(id);

                str +=  "\t Nome do Filme: " + s.getNome() + "\n" +
                        "\t Horário: " + sdf.format(s.getHorario().getTime()) + "\n" +
                        "\t Número da Sala: " + s.getNumeroSala() + "\n"+
                        "\t Descrição: " + s.getDescricao() + "\n\n";
            }
        }

        return str;
    }

//    public final String sessoesRegistradas(Cinema cinema){}

}

