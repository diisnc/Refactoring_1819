
package DataLayer;

import BusinessLayer.GestaoAtivos.Ativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AtivoDAO implements Map<String, Ativo>{

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public boolean containsKey(Object key) {
        boolean result = false;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Ativo A "
                  + "WHERE A.idAtivo = ? ");
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ativo get(Object key) {
        Ativo a = null;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Ativo WHERE idAtivo = ?");
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    a = new Ativo (rs.getString("idAtivo"),rs.getString("descricao"),
                                   rs.getFloat("precoCompra"), rs.getFloat("precoVenda"),
                                   rs.getFloat("variacao"),rs.getFloat("PrecoAtual"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    @Override
    public Ativo put(String key, Ativo a) {
        if (a == null) {
            return null;
        }
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement( 
                    "INSERT INTO Ativo (idAtivo, descricao, precoCompra,precoVenda,variacao,precoAtual) "
                    + "VALUES (?, ?, ?,?,?,?)");
            ps.setString(1, key);
            ps.setString(2, a.getDescricao());
            ps.setFloat(3, a.getPrecoCompra());
            ps.setFloat(4, a.getPrecoVenda());
            ps.setFloat(5, a.getVariacao());
            ps.setFloat(6, a.getPrecoAtual());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    } 

    @Override
    public Ativo remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Ativo> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        Set<String> ativos = new TreeSet<>();
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ativo ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ativos.add(rs.getString("idAtivo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ativos;
    }

    @Override
    public Collection<Ativo> values() {
        Set<String> ativos = keySet();
        Collection<Ativo> res = new TreeSet<>();
        for (String s : ativos) {
            res.add(get(s));
        }
        return res;
    }

    @Override
    public Set<Map.Entry<String, Ativo>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(String idAtivo, Ativo a) {
    Connection con = null;
    try {
        con = Connect.connect();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Ativo "
                + "SET descricao=? , precoCompra = ?, precoVenda = ?, variacao = ?, precoAtual = ? "
                + "WHERE idAtivo = ?");
        ps.setString(1, a.getDescricao());
        ps.setFloat(2, a.getPrecoCompra());
        ps.setFloat(3, a.getPrecoVenda());
        ps.setFloat(4, a.getVariacao());
        ps.setFloat(5, a.getPrecoAtual());
        ps.setString(6, idAtivo);
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    
}
