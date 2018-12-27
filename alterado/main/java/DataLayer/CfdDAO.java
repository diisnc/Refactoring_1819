
package DataLayer;

import BusinessLayer.GestaoAtivos.Ativo;
import BusinessLayer.GestaoCFD.CFD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CfdDAO implements Map<Integer, CFD> {

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object o) {
        boolean result = false;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM CFD C "
                  + "WHERE C.idCFD = ? ");
            ps.setFloat(1, Float.parseFloat(o.toString()));
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
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CFD get(Object o) {
        CFD cfd = null;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM CFD WHERE idCFD = ?");
            ps.setString(1, o.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cfd = new CFD (rs.getInt("idCFD"),rs.getString("ativo"), rs.getFloat("valorAbertura"),
                               rs.getFloat("takeProfit"), rs.getFloat("stopLoss"), 
                               rs.getByte("categoria"),rs.getFloat("investimento"),
                               rs.getFloat("uniAtivo"), rs.getTimestamp("dataAbertura").toLocalDateTime(),
                               rs.getBoolean("estado"),rs.getString("utilizador"));
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
        return cfd;
    }

    @Override
    public CFD put(Integer k, CFD cfd) {
        if (cfd == null) {
            return null;
        }
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement( 
                    "INSERT INTO `esstrading`.`cfd`(`dataAbertura`,`valorAbertura`,`takeProfit`," +
                                                    "`stopLoss`,`categoria`,`uniAtivo`,`estado`," + 
                                                    "`investimento`,`utilizador`,`ativo`)"
                    + "VALUES (?, ?, ?,?,?,?,?,?,?,?)");
            ps.setTimestamp(1, Timestamp.valueOf(cfd.getDataAbertura()));
            ps.setFloat(2, cfd.getValorAbertura());
            ps.setFloat(3, cfd.getTakeProfit());
            ps.setFloat(4, cfd.getStopLoss());
            ps.setByte(5, cfd.getCategoria());
            ps.setFloat(6, cfd.getUniAtivo());
            ps.setBoolean(7, cfd.getEstado());
            ps.setFloat(8, cfd.getValorInvestido());
            ps.setString(9, cfd.getUtilizador());
            ps.setString(10, cfd.getIdAtivo());
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
        return cfd;
    }

    @Override
    public CFD remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends CFD> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> cfds = new TreeSet<>();
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CFD");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cfds.add(rs.getInt("idCFD"));
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
        return cfds;
    }

    @Override
    public Collection<CFD> values() {
        Set<Integer> cfds = keySet();
        Collection<CFD> res = new TreeSet<>();
        for (Integer i : cfds) {
            CFD c = get(i);
            res.add(c);
        }
        return res;
    }

    @Override
    public Set<Entry<Integer, CFD>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Collection<CFD> cfdsAtivos(String user){
        Collection<CFD> cfds = values();
        Collection<CFD> res = new TreeSet<>();
        if(cfds != null){
            for(CFD c: cfds){
                if(c.getUtilizador().equals(user) == true && c.getEstado() == true ) {
                    res.add(c);
                }
            }
        }
        return res;
    }

    public void close(Integer idCFD) {
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("Update CFD SET estado = ? WHERE idCFD = ?");
            ps.setBoolean(1, false);
            ps.setInt(2, idCFD);
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
