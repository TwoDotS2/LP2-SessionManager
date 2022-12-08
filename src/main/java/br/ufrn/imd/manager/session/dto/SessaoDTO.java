package br.ufrn.imd.manager.session.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Arrays;

@Data
public class SessaoDTO {

    private Integer id;
	private FilmeDTO filmeDTO;
    private SalaDTO salaDTO;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private double valorIngresso;
    private char[] poltronas; //l = livre; m = meia; i = inteira
    private boolean exibicao3D;
    private String tipoAudio;
    
	public SessaoDTO(FilmeDTO filme, SalaDTO sala, LocalTime horarioInicial, LocalTime horarioFinal, double valorIngresso, boolean exibicao3d, String tipoAudio) {
		this.filmeDTO = filme;
		this.salaDTO = sala;
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.valorIngresso = valorIngresso;
		exibicao3D = exibicao3d;
		this.tipoAudio = tipoAudio;
		
		poltronas = new char[sala.getCapacidade()];
        for(int i=0; i < poltronas.length; i++) { //Inicializando todas as poltronas como livres.
            poltronas[i] = 'l';
        }
	}
	
	public boolean ocuparPoltrona(int poltrona, char tipoIngresso) {

        if(poltronas[poltrona] == 'l') {
            poltronas[poltrona] = tipoIngresso;
            return true;
        } else {
            return false;
        }

    }

    public boolean liberarPoltrona(int poltrona) {

        if(poltronas[poltrona] != 'l') {
            poltronas[poltrona] = 'l';
            return true;
        } else {
            return false;
        }

    }
    
    public double taxaOcupacao(){
        double ocupados=0;

        for (char p : poltronas) {
            if(p != 'l'){
                ocupados++;
            }         
        }

        return ocupados / salaDTO.getCapacidade();
    }
    
    public String poltronasLivres(){
        int quantidade = 0;
        String poltronasLivres = "|  ";

        for(int i = 0; i < poltronas.length; i++){

            if(poltronas[i] == 'l'){
                quantidade++;

                if (i<9){
                    poltronasLivres += " " + (i+1) + "  |  ";    
                } else {
                    poltronasLivres += (i+1) + "  |  ";
                }

            }

            if((i+1) % 10 == 0 && i != 0 && i != poltronas.length-1) { //Dividir em 10 colunas
                poltronasLivres += "\n|  ";
            }

        }
        return "Quantidade de poltronas livres: " + quantidade + "\n   > Poltronas <   \n" + poltronasLivres;
    }
    
    public String poltronasOcupadas(){
        int quantidade = 0;
        String poltronasOcupadas = "|  ";

        for(int i = 0; i < poltronas.length; i++){

            if(poltronas[i] != 'l'){
                quantidade++;

                if (i<9){
                    poltronasOcupadas += " " + (i+1) + "  |  ";    
                } else {
                    poltronasOcupadas += (i+1) + "  |  ";    
                }

            }

            if(i % 9 == 0 && i != 0 && i != poltronas.length-1) { //Dividir em 10 colunas
                poltronasOcupadas += "\n|  ";
            }
            
        }
        return "\n Quantidade de poltronas Ocupadas: " + quantidade + "\n\n    > Poltronas <   \n" + poltronasOcupadas;
    }
    
    
	@Override
	public String toString() {
		return "Sessao [filme=" + filmeDTO.getTitulo() + ", horarioInicial=" + horarioInicial + ", horarioFinal=" + horarioFinal
				+ ", valorIngresso=" + valorIngresso + ", poltronas=" + Arrays.toString(poltronas) + ", exibicao3D="
				+ exibicao3D + ", tipoAudio=" + tipoAudio + "]";
	}
	
	
    
    
}
