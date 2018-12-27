
package BusinessLayer.GestaoAtivos;

import java.math.BigDecimal;

public class Ativo implements Comparable<Ativo>{
    private String idAtivo;
    private String descricao;
    private float precoCompra;
    private float precoVenda;
    private float variacao;
    private float precoAtual;

    public Ativo(String idAtivo, String descricao, float precoCompra, float precoVenda, float change, float precoAtual) {
        this.idAtivo = idAtivo;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.variacao = change;
        this.precoAtual = precoAtual;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public float getPrecoCompra() {
        return this.precoCompra;
    }

    public float getPrecoVenda() {
       return this.precoVenda;
    }
    
    public String getIdAtivo(){
        return this.idAtivo;
    }
    
    public float getVariacao(){
        return this.variacao;
    }

    @Override
    public int compareTo(Ativo o) {
        return idAtivo.compareTo(o.idAtivo);
    }

    void setDescricao(String name) {
        this.descricao = name;
    }

    void setPrecoCompra(Float bid) {
        this.precoCompra = bid;
    }

    void setPrecoVenda(Float ask) {
        this.precoVenda = ask;
    }

    void setVariacao(float floatValue) {
       this.variacao=floatValue;
    }

    void setValorAtual(float floatValue) {
        this.precoAtual= floatValue;
    }

    public float getPrecoAtual() {
        return this.precoAtual;
    }
    
}
