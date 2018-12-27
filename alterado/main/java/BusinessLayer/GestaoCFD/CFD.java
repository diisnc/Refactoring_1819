
package BusinessLayer.GestaoCFD;

import java.time.LocalDateTime;

public class CFD implements Comparable<CFD>{
    private int idCFD;
    private String idAtivo;
    private LocalDateTime dataAbertura;
    private float valorAbertura;
    private float takeProfit;
    private float stopLoss;
    private Byte categoria;
    private float uniAtivo;
    private float valorInvestido;
    private boolean estado;
    private String user;
    
    public CFD (String idAtivo, float valorAbertura, float takeProfit, float stopLoss, Byte categoria,
                float valorInvestido, float uniAtivo, String user ){
        this.idAtivo = idAtivo;
        this.valorAbertura = valorAbertura;
        this.takeProfit  = takeProfit;
        this.stopLoss = stopLoss;
        this.categoria = categoria;
        this.uniAtivo = uniAtivo;
        this.valorInvestido =  valorInvestido;
        this.dataAbertura = LocalDateTime.now();
        this.estado = true;
        this.user = user;
    }
    
    public CFD (int idCfd, String idAtivo, float valorAbertura, float takeProfit, float stopLoss, Byte categoria,
                float valorInvestido, float uniAtivo, LocalDateTime dataAbertura,boolean estado, String user ){
        this.idCFD = idCfd;
        this.idAtivo = idAtivo;
        this.valorAbertura = valorAbertura;
        this.takeProfit  = takeProfit;
        this.stopLoss = stopLoss;
        this.categoria = categoria;
        this.uniAtivo = uniAtivo;
        this.valorInvestido =  valorInvestido;
        this.dataAbertura = dataAbertura;
        this.estado = estado;
        this.user = user;
    }

    public int getIdCFD() {
        return idCFD;
    }

    public void setIdCFD(int idCFD) {
        this.idCFD = idCFD;
    }

    public String getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(String idAtivo) {
        this.idAtivo = idAtivo;
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

    public float getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(float takeProfit) {
        this.takeProfit = takeProfit;
    }

    public float getStopLoss() {
        return stopLoss;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setStopLoss(float stopLoss) {
        this.stopLoss = stopLoss;
    }

    public Byte getCategoria() {
        return categoria;
    }

    public void setCategoria(Byte categoria) {
        this.categoria = categoria;
    }

    public float getUniAtivo() {
        return uniAtivo;
    }

    public void setUniAtivo(float uniAtivo) {
        this.uniAtivo = uniAtivo;
    }

    public float getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(float valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public String getUtilizador() {
        return user;
    }

    @Override
    public int compareTo(CFD t) {
        return idCFD- t.idCFD;
    }
    
    
}
