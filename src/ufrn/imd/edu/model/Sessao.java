package ufrn.imd.edu.model;

import java.util.Calendar;
import java.util.UUID;

public class Sessao extends Filme{
    private static UUID id;
    private Calendar horario;
    private Integer numeroSala;

    public Sessao() {
        id = UUID.randomUUID();
    }

    public Sessao(Integer numeroSala, Calendar horario, Filme filme) {
        id = UUID.randomUUID();
        this.horario = horario;
        this.numeroSala = numeroSala;
        setFilme(filme);
    }

    public Sessao(Filme filme) {
        id = UUID.randomUUID();
        setFilme(filme);
    }
    public UUID getId() {
        return id;
    }

//    public void setId(UUID id) {
//        this.id = id;
//    }

    public Calendar getHorario() {
        return horario;
    }

    public void setHorario(Calendar horario) {
        this.horario = horario;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public void setFilme(Filme filme){
        super.setNome(filme.getNome());
        super.setDescricao(filme.getDescricao());
    }

    @Override
    public String toString() {
        return  "Nome do Filme: " + super.getNome() + "\n" +
                "Horário: " + horario + "\n" +
                "Número da Sala: " + numeroSala + "\n"+
                "Descrição: " + super.getDescricao() + "\n";

    }
}
