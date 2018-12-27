
package BusinessLayer.GestaoUtilizadores;

public class Utilizador {
    private String nome;
    private String username;
    private char[] password;
    private Conta conta;

    public Utilizador(String username, String nome, char[] password) {
        this.username = username;
        this.nome = nome;
        this.password = password;
        Conta c = new Conta();
        this.conta = c;
    }

    public Utilizador(String username, String nome, char[] password, Conta c) {
        this.username = username;
        this.nome = nome;
        this.password = password;
        this.conta = c;
        
    }

    public String getUsername() {
        return this.username;
    }

    public String getNome() {
        return this.nome;
    }

    public char[] getPassword() {
        return this.password;
    }

    public Conta getConta() {
        return this.conta;
    }
}
