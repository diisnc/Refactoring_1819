
package DataLayer;

import BusinessLayer.GestaoUtilizadores.Conta;
import BusinessLayer.GestaoUtilizadores.Movimento;
import BusinessLayer.GestaoUtilizadores.Utilizador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilizadorDAO implements Map<String, Utilizador> {

    public boolean containsKey(String username) {
        boolean result = false;
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Utilizador U "
                  + "WHERE U.username = ? ");
            ps.setString(1, username);
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
    public Utilizador put(String username, Utilizador u) {
        if (u == null) {
            return null;
        }
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Utilizador (username, nome, password,saldo) "
                    + "VALUES ( ?, ?, ?,?)");
            ps.setString(1, username);
            ps.setString(2, u.getNome());
            ps.setString(3, new String(u.getPassword()));
            ps.setFloat(4, u.getConta().getSaldo());
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
        return u;
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilizador get(Object key) {
        Utilizador u = null;
        Map <Integer,Movimento> movs = new TreeMap<>();
        Collection<Integer> cfds = new TreeSet<>();
        Connection con = null;
        try {
            con = Connect.connect();
            /* Buscar Movimentos */
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Movimento WHERE utilizador = ?");
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            Movimento m = null;
            LocalDateTime data;
            while (rs.next()) {
                if ( rs.wasNull()){
                m = new Movimento(rs.getInt("idMovimento"), rs.getString("descricao"), 
                                  rs.getFloat("valorInvestido"), rs.getTimestamp("dataFecho").toLocalDateTime(),
                                  rs.getTimestamp("dataAbertura").toLocalDateTime(),rs.getFloat("valorAbertura"),
                                  rs.getFloat("nUnidades"), rs.getFloat("valorFecho"),rs.getString("utilizador"));
                } else {
                    m = new Movimento(rs.getInt("idMovimento"), rs.getString("descricao"), 
                                      rs.getFloat("valorInvestido"),
                                      rs.getTimestamp("dataFecho").toLocalDateTime(),rs.getString("utilizador"));
                }
                movs.put(m.getIdMovimento(),m);
            }
            /* Buscar CFDS*/
            ps= con.prepareStatement("SELECT idCFD FROM CFD WHERE utilizador = ?");
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                cfds.add(rs.getInt("idCFD"));
            }
            /*Buscar Utilizador*/
            ps = con.prepareStatement(
                    "SELECT * FROM Utilizador U "
                  + "WHERE U.username = ?");
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Utilizador(rs.getString("username"), rs.getString("nome"),
                        rs.getString("password").toCharArray(),new Conta(rs.getFloat("saldo"),movs,cfds));
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
        return u;
    }

    @Override
    public Utilizador remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Utilizador> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void depositar(String atualUser, float valor, Movimento m) {
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utilizador "
                    + "SET saldo = ?"
                    + "WHERE username = ?");
            ps.setFloat(1, valor);
            ps.setString(2, atualUser);
            ps.executeUpdate();
            
            ps = con.prepareStatement(
                    "INSERT INTO movimento (`descricao`,`valorInvestido`," 
                    + "`dataFecho`,`valorFecho`,`utilizador`)"
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, m.getDescricao());
            ps.setFloat(2, m.getValorInvestido());
            ps.setTimestamp(3, Timestamp.valueOf(m.getDataFecho()));
            ps.setFloat(4, m.getValorFecho());
            ps.setString(5, atualUser);
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

    public Collection<Movimento> getMovimetos(String atualUser) {
        Set<Movimento> movs = new TreeSet<>();
        Connection con = null;
        try {
            con = Connect.connect();
            /* Buscar Movimentos */
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Movimento WHERE utilizador = ?");
            ps.setString(1, atualUser);
            ResultSet rs = ps.executeQuery();
            Movimento m = null;
            LocalDateTime data;
            float valorFecho;
            while (rs.next()) {
               valorFecho=rs.getFloat("valorFecho");
               if ( valorFecho != -1){
                   m = new Movimento(rs.getInt("idMovimento"), rs.getString("descricao"), 
                                 rs.getFloat("valorInvestido"), rs.getTimestamp("dataFecho").toLocalDateTime(),
                                 rs.getTimestamp("dataAbertura").toLocalDateTime(),rs.getFloat("valorAbertura"),
                                 rs.getFloat("nUnidades"), rs.getFloat("valorFecho"),rs.getString("utilizador"));
               } else {
                   m = new Movimento(rs.getInt("idMovimento"), rs.getString("descricao"), 
                                     rs.getFloat("valorInvestido"),
                                     rs.getTimestamp("dataFecho").toLocalDateTime(),rs.getString("utilizador"));
               }
               movs.add(m);
            }
        }   catch (SQLException ex) {
               Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movs;
    }

    public void registaMovimento(Movimento m) {
         Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                       "INSERT INTO `esstrading`.`movimento`("
                        + "`descricao`,`valorInvestido`,`dataFecho`,`dataAbertura`,"
                        + "`valorAbertura`,`nUnidades`,`valorFecho`,`utilizador`)"
                        + "VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, m.getDescricao());
            ps.setFloat(2, m.getValorInvestido());
            ps.setTimestamp(3, Timestamp.valueOf(m.getDataFecho()));
            ps.setTimestamp(4, Timestamp.valueOf(m.getDataAbertura()));
            ps.setFloat(5, m.getValorAbertura());
            ps.setFloat(6, m.getnUnidades());
            ps.setFloat(7, m.getValorFecho());
            ps.setString(8, m.getUser());
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

    public void alteraSaldo(String atualUser, float valor) {
        Connection con = null;
        try {
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utilizador "
                    + "SET saldo = ?"
                    + "WHERE username = ?");
            ps.setFloat(1, valor);
            ps.setString(2, atualUser);
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

    public void updateDados(Utilizador u) {
    Connection con = null;
    try {
        con = Connect.connect();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Utilizador "
                + "SET nome=?,password = ?"
                + "WHERE username = ?");
        ps.setString(1, u.getNome());
        ps.setString(2, new String(u.getPassword()));
        ps.setString(3, u.getUsername() );
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
