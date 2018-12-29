/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package betess;

import java.io.Serializable;


public class Aposta implements Serializable{
    
    private int id_aposta; 
    private double quantia; 
    private int id_evento; 
    private String id_jogador; 
    private boolean ganha_casa;
    private boolean ganha_fora;
    private boolean empate;
    private String estado;

    public Aposta(int id_aposta, double quantia, int id_evento, String id_jogador, boolean ganha_casa, boolean ganha_fora, boolean empate) {
        this.id_aposta = id_aposta;
        this.quantia = quantia;
        this.id_evento = id_evento;
        this.id_jogador = id_jogador;
        this.ganha_casa = ganha_casa;
        this.ganha_fora = ganha_fora;
        this.empate = empate;
        this.estado = "Não paga";
    }

    public Aposta(Aposta ap){
        this.id_aposta = ap.getId_aposta();
        this.quantia = ap.getQuantia();
        this.id_evento = ap.getId_evento();
        this.id_jogador = ap.getId_jogador();
        this.ganha_casa = ap.getGanha_casa();
        this.ganha_fora = ap.getGanha_fora();
        this.empate = ap.getEmpate();
        this.estado = ap.getEstado();           
    }
    
    

    /* GETTERS */
    public int getId_aposta() {
        return id_aposta;
    }

    public double getQuantia() {
        return quantia;
    }

    public int getId_evento() {
        return id_evento;
    }

    public String getId_jogador() {
        return id_jogador;
    }

    public boolean getGanha_casa() {
        return ganha_casa;
    }

    public boolean getGanha_fora() {
        return ganha_fora;
    }

    public boolean getEmpate() {
        return empate;
    }

    public String getEstado() {
        return estado;
    }
    
    
    /* SETTERS */

    public void setGanha_casa(boolean ganha_casa) {
        this.ganha_casa = ganha_casa;
    }

    public void setGanha_fora(boolean ganha_fora) {
        this.ganha_fora = ganha_fora;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public Aposta clone(){
        return new Aposta(this);
    }
    
}