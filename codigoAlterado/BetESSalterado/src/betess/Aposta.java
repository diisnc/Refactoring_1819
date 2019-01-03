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
    
    public Aposta(){
        this.id_aposta = -1;
        this.quantia = -1;
        this.id_evento = -1;
        this.id_jogador = "None";
        this.ganha_casa = false;
        this.ganha_fora = false;
        this.empate = false;
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

    public void setId_aposta(int id_aposta) {
        this.id_aposta = id_aposta;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public void setId_jogador(String id_jogador) {
        this.id_jogador = id_jogador;
    }
    
    
    @Override
    public Aposta clone(){
        return new Aposta(this);
    }
    
}