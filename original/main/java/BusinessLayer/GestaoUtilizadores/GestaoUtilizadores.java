
package BusinessLayer.GestaoUtilizadores;

import BusinessLayer.GestaoCFD.CFD;
import DataLayer.UtilizadorDAO;
import Exceptions.UserExistsException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

public class GestaoUtilizadores {
    private UtilizadorDAO utilizadores = new UtilizadorDAO();

    public void registarUser(String username, String nome, char[] password) throws UserExistsException {
        if (this.utilizadores.containsKey(username)) {
            throw new UserExistsException();
        }
        Utilizador u = new Utilizador(username, nome, password);
        this.utilizadores.put(username, u);
    }

    public Boolean validarLogin(String username, char[] password) {
        char[] pass = null;
        if (this.utilizadores.containsKey(username)) {
            pass = this.utilizadores.get(username).getPassword();
            if (Arrays.equals(password, pass) == false) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Utilizador getUser(String username) {
        Utilizador u = null;
        if (this.utilizadores.containsKey(username)) {
            u = this.utilizadores.get(username);
        }
        return u;
    }

    public void editarDados(String atualUser, String nome, char[] password) {
        Utilizador u = new Utilizador(atualUser, nome, password);
        this.utilizadores.updateDados(u);
    }

    public void depositar(String atualUser, float valor) {
        float saldoAtualizado = this.utilizadores.get(atualUser).getConta().getSaldo() + valor;
        String descricao = "Deposito";
        Movimento m = new Movimento(descricao,atualUser, valor, LocalDateTime.now());
        this.utilizadores.depositar(atualUser,saldoAtualizado,m);
    }

    public synchronized Collection<Movimento> getMovimetos(String atualUser) {
        return this.utilizadores.getMovimetos(atualUser);
    }
    
    public void descontaSaldo(String atualUser, float valorInvestido) {
        this.utilizadores.alteraSaldo(atualUser,valorInvestido);
    }
    
    public void registaFechoCFD(String atualUser, float profit, float valorFecho, CFD cfd) {
        String descricao = null;
        if(cfd.getCategoria()==0) descricao = cfd.getIdAtivo()+" BUY";
        else descricao = cfd.getIdAtivo()+" SELL";
        Movimento m = new Movimento(-1, descricao, cfd.getValorInvestido(), 
                                    LocalDateTime.now(), cfd.getDataAbertura(), cfd.getValorAbertura(),
                                    cfd.getUniAtivo(), valorFecho, atualUser);
        this.utilizadores.registaMovimento(m);
        float saldo = this.utilizadores.get(atualUser).getConta().getSaldo()+ profit;
        this.utilizadores.alteraSaldo(atualUser, saldo);
    }

}
