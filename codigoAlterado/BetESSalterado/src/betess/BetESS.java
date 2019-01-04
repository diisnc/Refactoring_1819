/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.io.*;
import java.util.*;


public class BetESS {
    
    private Database database;
    private String id_utilizador_aut;
    
    /* Contrutor BetESS */
    public BetESS(){
        Database d = null;
        d = readfile(d);
        if (d == null){
            System.out.println("Estado da aplicação iniciado.");
            this.database = new Database();
        }
        else {
            System.out.println("Restauro da aplicação com sucesso.");
            this.database = d;
        }
    }
    
    public Database readfile(Database d){
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/database");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            d = (Database) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return d;
    }
    
    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/database");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.database);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data!");
        } catch (IOException i) {
              i.printStackTrace();
        }
    }
    
    /* método que permite definir o jogador autenticado na sessao */
    public void setId_utilizador_aut(String id_utilizador_aut) {
        this.id_utilizador_aut = id_utilizador_aut;
    }

    public String getId_utilizador_aut() {
        return id_utilizador_aut;
    }
    
    public HashMap<String, Jogador> getJogadores(){
        return this.database.getJogadores();
    }
    
    public List<Jogador> getJogadoresBloqueados(){
        return this.database.getJogadoresBloqueados();
    }
    
    public void eliminaJogador(String id){
        this.database.eliminaJogador(id);
    }
    
    public void bloqueiaJogador(String id){
        this.database.bloqueiaJogador(id);
    }
    
    public void desbloqueiaJogador(String id){
        this.database.desbloqueiaJogador(id);
    }
    
    public Aposta getAposta(int id_aposta){
        return this.database.getAposta(id_aposta);
    }
    
    public Equipa getEquipa(String id_equipa){
        return this.database.getEquipa(id_equipa);
    }
    
    public List<Equipa> getEquipas(){
        return this.database.getEquipas();
    }
    
    public void registaEquipa(Equipa e){
        this.database.registaEquipa(e);
    }
    
    /* inserção de um jogador no sistema */
    public void registaJogador(Jogador j){
        this.database.registaJogador(j);
    }
    
    public void registaAposta(Aposta a){
        this.database.registaAposta(a);
    }
    
    public void atualizaSaldo(double creditos, String id_jogador){
        this.database.atualizaSaldo(creditos, id_jogador);
    }
    
    /* verificação se um jogador se encontra registado no sistema */
    public Jogador checkUser(String username){
        return this.database.checkUser(username);
    }
    
    public EventoDesportivo getEventoDesportivo(int id){
        return this.database.getEventoDesportivo(id);
    }
    
    public void registaLiga(Liga l){
        this.database.registaLiga(l);
    }
    
    public List<Liga> getLigas(){
        return this.database.getLigas();
    }
    
    public void removeNotificacao(String id_utilizador, int id_aposta){
        this.database.removeNotificacao(id_utilizador, id_aposta);
    }
            
    public void registaEventoDesportivo(EventoDesportivo e){
        this.database.registaEventoDesportivo(e);
    }
    
    public Map<Integer, EventoDesportivo> getEventosDesportivos(){
        return this.database.getEventosDesportivos();
    }
    
    public HashMap<Integer, Aposta> getApostas(){
        return this.database.getApostas();
    }
    
    public void removeAposta(int id){
        this.database.removeAposta(id);
    }
    
    public List<Aposta> getApostasJogador(String id_jogador){
        return this.database.getApostasJogador(id_jogador);
    }
    
    void fechaEvento(int id_evento, boolean ganha_casa, boolean ganha_fora, boolean empate) {
        this.database.fechaEvento(id_evento, ganha_casa, ganha_fora, empate);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login l = new Login();
        l.setLocationRelativeTo(null);
        l.setVisible(true);
    }
}
