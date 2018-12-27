
package BusinessLayer.GestaoCFD;
import DataLayer.CfdDAO;
import java.util.Collection;

public class GestaoCFD {
    private CfdDAO cfds = new CfdDAO();

    public synchronized void insereCFD(CFD cfd) {
        cfds.put(-1, cfd);
    }

    public synchronized Collection<CFD> getCFDs(String user) {
        return this.cfds.cfdsAtivos(user);
    }

    public synchronized void closeCFD(Integer idCFD) {
       this.cfds.close(idCFD);
    }
    
    public synchronized CFD getCFD(int idCFD){
        return cfds.get(idCFD);
    }

    public float calculaPL(CFD cfd, float valorFecho) {
        float res=0;
        if (cfd.getCategoria() == 0){
            // Compra
            res = (valorFecho - cfd.getValorAbertura()) * cfd.getUniAtivo();
        }else {
            // Venda
            res = (cfd.getValorAbertura()- valorFecho)* cfd.getUniAtivo();;
        }
        return res;
    }
    
    
}
