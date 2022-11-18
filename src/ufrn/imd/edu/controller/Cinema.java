package ufrn.imd.edu.controller;

import ufrn.imd.edu.model.Filme;
import ufrn.imd.edu.model.Sessao;

import java.util.*;


public class Cinema {
    private HashMap<Integer, Sala> salas;
    private List<Sessao> sessoes;

    private ArrayList<Integer>listaNumSalas;
    private HashMap<Integer, ArrayList<UUID>> sessaoEmSala;

    public Cinema(){
        sessoes = new ArrayList<Sessao>();
        salas = new HashMap<Integer, Sala>();
    }

    public void cadastrarSessao(Integer numeroSala, Calendar horario, Filme filme){
        Sessao s = new Sessao();
            s.setNumeroSala(numeroSala);
            s.setFilme(filme);
            s.setHorario(horario);

        sessoes.add(s);
    }

    public void cadastrarSessao(Sessao s){
        sessoes.add(s);
    }

    public ArrayList<Sessao> listarSessoesPorHorario(Calendar horario){
        return new ArrayList<Sessao>();
    }

    private void gerarRelacaoSessaoSala(){
        //Variaveis auxiliares para sessao
        UUID id; Integer numSala;

        sessaoEmSala = new HashMap<Integer, ArrayList<UUID>>();

        //Vetor para acessar as sessoes e registrar
        for (Sessao s: sessoes) {
            id = s.getId();
            numSala = s.getNumeroSala();

            //Se as salas do cinema contém a sala registrada na sessão
            if(salas.containsKey(numSala)) {
                //Se as sessoes em sala tiverem a chave registrar, é pq tem o ArrayList tbm
                if (sessaoEmSala.containsKey(numSala)) {
                    sessaoEmSala.get(numSala).add(id);
                } else {
                    //Registra o ArrayList
                    sessaoEmSala.put(numSala, new ArrayList<UUID>());
                    sessaoEmSala.get(numSala).add(id);
                }
            }
        }

        gerarListaNumSalas();
    }

    private void gerarListaNumSalas(){
        Set<Integer> conjuntoNumSalas = salas.keySet();
        listaNumSalas = new ArrayList<>(conjuntoNumSalas);
    }

    public void gerarSalas(int qtdSalas, int linha, int coluna) {
        Sala auxSala;

        for (int i = 1; i <= qtdSalas; i++) {
            auxSala = new Sala();

            //Inicializa os assentos
            auxSala.configurarSala(linha, coluna);

            salas.put(i, auxSala);

        }
    }

    public void adicionarSala(int numeroSala, Sala sala) {
        //Se a sala não existir, adiciona ela
        if(!salas.containsKey(numeroSala))
            salas.put(numeroSala, sala);
    }

    public HashMap<Integer, ArrayList<UUID>> getSessaoEmSala() {
        gerarRelacaoSessaoSala();
        return sessaoEmSala;
    }

    //Atenção
    public ArrayList<Integer> getListaNumSalas() {
        return listaNumSalas;
    }

    public Sessao getSessaoPorId(UUID id){
        Sessao s_id = null;

        //Varrer no ArrayList para ver se encontra a sessão pelo UUID
        for (Sessao s: sessoes) {
            if(s.getId() == id){
                s_id = s;
                break;
            }
        }

        return s_id;
    }
}
