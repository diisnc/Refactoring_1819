/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package betess;

import java.io.Serializable;


public class EventoDesportivo implements Serializable{
    
    private int id_evento;
    private String equipa_casa;
    private String equipa_fora;
    private String estado;
    private boolean ganha_casa;
    private boolean ganha_fora;
    private boolean empate;
    private double odd_casa;
    private double odd_fora;
    private double odd_empate;
    /* COLOCAMOS A HORA DO JOGO OU NÃO VALE A PENA? */

    public EventoDesportivo(int id_evento, String equipa_casa, String equipa_fora, double odd_casa, double odd_fora, double empate) {
        this.id_evento = id_evento;
        this.equipa_casa = equipa_casa;
        this.equipa_fora = equipa_fora;
        this.estado = "Aberto";
        this.ganha_casa = false;
        this.ganha_fora = false;
        this.empate = false;
        this.odd_casa = odd_casa;
        this.odd_fora = odd_fora;
        this.odd_empate = empate;
    }

    /* construtor utilizado no clone */
    public EventoDesportivo(int id_evento, String equipa_casa, String equipa_fora, String estado, boolean ganha_casa, boolean ganha_fora, boolean empate, double odd_casa, double odd_fora, double odd_empate) {
        this.id_evento = id_evento;
        this.equipa_casa = equipa_casa;
        this.equipa_fora = equipa_fora;
        this.estado = estado;
        this.ganha_casa = ganha_casa;
        this.ganha_fora = ganha_fora;
        this.empate = empate;
        this.odd_casa = odd_casa;
        this.odd_fora = odd_fora;
        this.odd_empate = odd_empate;
    }
    
    
    
    /* GETTERS */

    public int getId_evento() {
        return id_evento;
    }

    public String getequipa_casa() {
        return equipa_casa;
    }

    public String getequipa_fora() {
        return equipa_fora;
    }

    public String getEstado() {
        return estado;
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

    public double getOdd_casa() {
        return odd_casa;
    }

    public double getOdd_fora() {
        return odd_fora;
    }

    public double getOdd_empate() {
        return odd_empate;
    }
    
    
    
    
    
    /* SETTERS */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setGanha_casa(boolean ganha_casa) {
        this.ganha_casa = ganha_casa;
    }

    public void setGanha_fora(boolean ganha_fora) {
        this.ganha_fora = ganha_fora;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    
    public EventoDesportivo clone(){
        return new EventoDesportivo(this.getId_evento(), this.getequipa_casa(), this.getequipa_fora(),
                this.getEstado(), this.getGanha_casa(), this.getGanha_fora(), this.getEmpate(), this.getOdd_casa(),
                this.getOdd_fora(), this.getOdd_empate());
    }
    
}