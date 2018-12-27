 
package BusinessLayer.GestaoAtivos;

import DataLayer.AtivoDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import yahoofinance.Stock;

public class GestaoAtivos {
    private  AtivoDAO ativos;
    
    public GestaoAtivos (){
        ativos =  new  AtivoDAO();
        AtivosRunner ativosRunner = new AtivosRunner();
        ativosRunner.start();
    }

    public synchronized Collection<Ativo> getAtivos() {
        return ativos.values().stream().filter(a -> a.getPrecoCompra() * a.getPrecoVenda() != 0 ).collect(Collectors.toSet());
    }

    public synchronized Ativo getAtivo(String idAtivo) {
        return ativos.get(idAtivo);
    }
    
    public synchronized void updateAtivo(String idAtivo,Ativo a) {
        ativos.update(a.getIdAtivo(),a);
    }
   
   
    class AtivosRunner extends Thread{
        
        Collection<Ativo> yahooAtivos = ativos.values();
        
        @Override
        public void run(){
            while(true){
                execute_update();
            }
        }
        
        public void execute_update (){
            try{
                for (Ativo a : yahooAtivos){
                    Stock s = yahoofinance.YahooFinance.get(a.getIdAtivo());
                    if(s.isValid()){
                        a.setDescricao(s.getName());
                        BigDecimal bid = s.getQuote().getBid();
                        if (bid != null) a.setPrecoVenda(bid.floatValue());
                        BigDecimal ask = s.getQuote().getAsk();
                        if (ask != null) a.setPrecoCompra(ask.floatValue());
                        BigDecimal change = s.getQuote().getChangeInPercent();
                        if (change != null) a.setVariacao(change.floatValue());
                        BigDecimal atualValue = s.getQuote().getPrice();
                        if (atualValue != null) a.setValorAtual(atualValue.floatValue());
                        updateAtivo(a.getIdAtivo(),a);
                    }
                    
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }
    }
}
