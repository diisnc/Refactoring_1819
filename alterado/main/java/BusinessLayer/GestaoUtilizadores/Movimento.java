
package BusinessLayer.GestaoUtilizadores;

import java.time.LocalDateTime;

public class Movimento implements Comparable<Movimento> {
    private int idMovimento;
    private String descricao;
    private float valorInvestido;
    private LocalDateTime dataFecho;
    private LocalDateTime dataAbertura;
    private float valorAbertura;
    private float nUnidades;
    private float valorFecho;
    private String user;

    public Movimento(int idMovimento, String descricao, float valorInvestido, LocalDateTime dataFecho,
                      LocalDateTime dataAbertura, float valorAbertura, float nUnidades, float valorFecho, String user) {
        this.idMovimento = idMovimento;
        this.descricao = descricao;
        this.valorInvestido =  valorInvestido;
        this.dataFecho = dataFecho;
        this.dataAbertura = dataAbertura;
        this.valorAbertura =  valorAbertura;
        this.nUnidades = nUnidades;
        this.valorFecho = valorFecho;
        this.user = user;
        
    }

    public Movimento(String descricao, String atualUser, float valor, LocalDateTime now) {
        this.idMovimento = 0;
        this.descricao = descricao;
        this.user =  atualUser;
        this.valorInvestido =  valor;
        this.dataFecho = now;
        this.dataAbertura = null;
        this.valorAbertura =  0;
        this.nUnidades = 0;
        this.valorFecho = -1;
    }

    public Movimento(int id, String desc, float invest, LocalDateTime dataFecho, String user) {
        this.idMovimento = id;
        this.descricao = desc;
        this.user =  user;
        this.valorInvestido =  invest;
        this.dataFecho = dataFecho;
        this.dataAbertura = null;
        this.valorAbertura =  0;
        this.nUnidades = 0;
        this.valorFecho = -1;
    }

    public Integer getIdMovimento() {
        return idMovimento;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public float getValorFecho() {
        return valorFecho;
    }

    public void setValorFecho(float valorFecho) {
        this.valorFecho = valorFecho;
    }

    public float getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(float valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public LocalDateTime getDataFecho() {
        return dataFecho;
    }

    public void setDataFecho(LocalDateTime dataFecho) {
        this.dataFecho = dataFecho;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public float getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(float valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public float getnUnidades() {
        return nUnidades;
    }

    public void setnUnidades(float nUnidades) {
        this.nUnidades = nUnidades;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int compareTo(Movimento t) {
         return idMovimento - t.idMovimento;
    }


    
}
