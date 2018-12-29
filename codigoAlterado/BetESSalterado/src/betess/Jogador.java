/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package betess;

import java.io.Serializable;
import java.util.*;


public class Jogador implements Serializable{
    private String email;
    private String nome;
    private String password;
    private String contacto;
    private double saldo;
    private List<Notificacao> notificacoes;

    
    /* CONSTRUTOR */
    public Jogador(String email, String nome, String password, String contacto) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.contacto = contacto;
        this.saldo = 5; /* CONSIDERAR A OFERTA DE SALDO PARA UM NOVO UTILIZADOR */
        this.notificacoes = new ArrayList ();
    }

    public Jogador(String email, String nome, String password, String contacto, double saldo, List<Notificacao> notificacoes) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.contacto = contacto;
        this.saldo = saldo;
        this.notificacoes = notificacoes;
    }
    
    public Jogador(Jogador jog){
        this.email = jog.getEmail();
        this.nome = jog.getNome();
        this.password = jog.getPassword();
        this.contacto = jog.getContacto();
        this.saldo = jog.getSaldo();
        this.notificacoes = jog.getNotificacoes();
    }
    
    
    /* GETTERS */

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
    
    public String getContacto() {
        return contacto;
    }

    public double getSaldo() {
        return saldo;
    }

    private String getPassword() {
        return password;
    }
    
    
    
    public List<Notificacao> getNotificacoes(){
        List<Notificacao> res = new ArrayList ();
        
        for (Notificacao n : this.notificacoes){
            res.add(n.clone());
        }
        
        return res;
    }
    
    /* SETTERS */

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    /* método que permite a verificação da password sem que esta seja passada para o exterior */
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    
    /* método que permmite a adição de uma notificação relativa ao jogador */
    public void adicionaNotificacao(Notificacao n){
        this.notificacoes.add(n);
    }
    
    public void removeNotificacao(int id_aposta){
        for (Notificacao n : this.notificacoes){
            if (n.getId_aposta() == id_aposta){
                this.notificacoes.remove(id_aposta);
                break;
            }
        }
    }
    
    @Override
    public Jogador clone(){
        return new Jogador(this);
    }
}