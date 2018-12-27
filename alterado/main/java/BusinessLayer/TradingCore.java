
package BusinessLayer;

import BusinessLayer.GestaoAtivos.Ativo;
import BusinessLayer.GestaoAtivos.GestaoAtivos;
import BusinessLayer.GestaoCFD.CFD;
import BusinessLayer.GestaoCFD.GestaoCFD;
import BusinessLayer.GestaoUtilizadores.GestaoUtilizadores;
import BusinessLayer.GestaoUtilizadores.Movimento;
import BusinessLayer.GestaoUtilizadores.Utilizador;
import Exceptions.UserExistsException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class TradingCore {

    private GestaoAtivos ativos;
    private GestaoUtilizadores utilizadores;
    private GestaoCFD cfds;
    private String atualUser;
    
    public TradingCore (){
        ativos = new GestaoAtivos();
        utilizadores =  new GestaoUtilizadores();
        cfds = new GestaoCFD();
        closeCFDRunner cfdRunner = new closeCFDRunner();
        cfdRunner.start();
    }
    
    public void registarUser(String username, String nome, char[] password) throws UserExistsException{
        this.utilizadores.registarUser(username,nome,password);
    }

    public Boolean validarLogin(String username, char[] password) {
        return this.utilizadores.validarLogin(username, password);
    }

    public Utilizador getUser(String username) {
        return this.utilizadores.getUser(username);
    }
    
    public Utilizador getAtualUser() {
        return this.utilizadores.getUser(atualUser);
    }

     public void setAtualUser(String username) {
        this.atualUser = username;
    }

    public Collection<Ativo> getAtivos() {
        return ativos.getAtivos();
    }
    
    public Ativo getAtivo(String idAtivo) {
        return ativos.getAtivo(idAtivo);
    }

    public synchronized void insereCFD(CFD cfd) {
        float novoSaldo = utilizadores.getUser(atualUser).getConta().getSaldo()- cfd.getValorInvestido();
        cfds.insereCFD(cfd);
        utilizadores.descontaSaldo(atualUser,novoSaldo);
    }

    public void editarDados(String nome, char[] password1) {
        this.utilizadores.editarDados(this.atualUser,nome,password1);
    }

    public void depositar(float valor) {
        this.utilizadores.depositar(this.atualUser,valor);
    }

    public Collection<Movimento> getMovimentos() {
       return this.utilizadores.getMovimetos(atualUser);
    }

    public Map<Integer, CFD> getCfds() {
       Map<Integer,CFD> cfds = new TreeMap<>();
       Collection<CFD> cfd = this.cfds.getCFDs(atualUser);
       for(CFD c : cfd){
           cfds.put(c.getIdCFD(), c);
       }
       return cfds;
    }

    public void closeCFD(Integer idCFD, float valor) {
        CFD cfd = cfds.getCFD(idCFD);
        cfds.closeCFD(idCFD);
        float profit = cfd.getValorInvestido() + valor;
        float valorFecho =  ativos.getAtivo(cfd.getIdAtivo()).getPrecoAtual();
        utilizadores.registaFechoCFD(atualUser,profit,valorFecho, cfd);
    }
    
    class closeCFDRunner extends Thread{
        Map<Integer,CFD> cfdsAbertos;
        
        public void run(){
            CFD cfd = null;
            float valorPL,valorFecho,profit;
            while(true){
                cfdsAbertos = getCfds();
                for(Map.Entry<Integer,CFD> e : cfdsAbertos.entrySet()){
                    cfd = e.getValue();
                    valorFecho =  ativos.getAtivo(cfd.getIdAtivo()).getPrecoAtual();
                    valorPL = cfds.calculaPL(cfd,valorFecho);
                    if(valorPL < (-1)*cfd.getStopLoss() || valorPL > cfd.getTakeProfit()){
                        cfds.closeCFD(e.getKey());
                        profit = cfd.getValorInvestido() + valorPL;
                        utilizadores.registaFechoCFD(atualUser,profit,valorFecho, cfd);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
    }
    
    public static Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
