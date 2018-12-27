
package BusinessLayer.GestaoUtilizadores;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Conta {
    private float saldo;
    private Map<Integer,Movimento> movimentos;
    
    public Conta(){
        this.saldo=100000;
        this.movimentos =  new TreeMap<>();
    }

    public Conta(float saldo, Map<Integer, Movimento> movs, Collection<Integer> cfds) {
        this.saldo=saldo;
        this.movimentos =  movs;

    }

    public float getSaldo() {
        return this.saldo;
    }
    
    
}

