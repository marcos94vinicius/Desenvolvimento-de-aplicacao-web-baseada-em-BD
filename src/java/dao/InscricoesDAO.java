/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Inscricao;
import model.Usuario;
import model.Edicao;
import model.InscricaoEdicao;
import model.Inscricoes;

/**
 *
 * @author vinicius
 */
public class InscricoesDAO extends DAO<Inscricoes> {

    static String allQuery = "SELECT nome, data_inscricao, pagamento, edicao.preco, inscricao.id, "
            + " tituloev, ed, id_edicao, sexo, idade, instituicao_origem FROM usuario INNER JOIN inscricao" +
        " ON uid = inscricao.id_usuario INNER JOIN edicao ON id_edicao = edicao.id" +
        " WHERE id_edicao=? ORDER BY nome";
    
    static String allQuery2 = "SELECT nome, data_inscricao, pagamento, edicao.preco, inscricao.id, "
            + " tituloev, ed, id_edicao, sexo, idade, instituicao_origem FROM usuario INNER JOIN inscricao" +
        " ON uid = inscricao.id_usuario INNER JOIN edicao ON id_edicao = edicao.id" +
        " WHERE id_edicao=? ORDER BY data_inscricao";
    
    static String allQuery3 = "SELECT nome, data_inscricao, pagamento, edicao.preco, inscricao.id, "
            + " tituloev, ed, id_edicao, sexo, idade, instituicao_origem FROM usuario INNER JOIN inscricao" +
        " ON uid = inscricao.id_usuario INNER JOIN edicao ON id_edicao = edicao.id" +
        " WHERE id_edicao=? ORDER BY pagamento";
    
     static String readQuery = "SELECT nome, edicao.preco, tituloev, ed, sexo, idade, instituicao_origem FROM usuario INNER JOIN inscricao" +
        " ON uid = inscricao.id_usuario INNER JOIN edicao ON id_edicao = edicao.id" +
        " WHERE id_edicao=?";

    public InscricoesDAO(Connection connection) {
        super(connection);
    }
    
    public List<Inscricoes> allInscricoes(Long edicao_id) throws SQLException {
        List<Inscricoes> inscricoesList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allQuery)){
               
                statement.setLong(1, edicao_id);

                ResultSet result = statement.executeQuery();
            while (result.next()) {
                
                Inscricoes inscricoes = new Inscricoes();
                
                inscricoes.setNome(result.getString("nome"));
                inscricoes.setData_inscricao(result.getString("data_inscricao"));
                inscricoes.setPagamento(result.getLong("pagamento"));
                inscricoes.setPreco(result.getFloat("preco"));
                inscricoes.setId_inscricao(result.getLong("id"));                
                inscricoes.setTituloev(result.getString("tituloev"));
                inscricoes.setEd(result.getString("ed"));
                inscricoes.setId_edicao(result.getLong("id_edicao")); 
                inscricoes.setSexo(result.getString("sexo"));  
                inscricoes.setIdade(result.getInt("idade")); 
                inscricoes.setInstituicao_origem(result.getString("instituicao_origem"));

                inscricoesList.add(inscricoes);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return inscricoesList;

    }
    
    public List<Inscricoes> allInscricoes2(Long edicao_id) throws SQLException {
        List<Inscricoes> inscricoesList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allQuery2)){
               
                statement.setLong(1, edicao_id);

                ResultSet result = statement.executeQuery();
            while (result.next()) {
                
                Inscricoes inscricoes = new Inscricoes();
                
                inscricoes.setNome(result.getString("nome"));
                inscricoes.setData_inscricao(result.getString("data_inscricao"));
                inscricoes.setPagamento(result.getLong("pagamento"));
                inscricoes.setPreco(result.getFloat("preco"));
                inscricoes.setId_inscricao(result.getLong("id"));                
                inscricoes.setTituloev(result.getString("tituloev"));
                inscricoes.setEd(result.getString("ed"));
                inscricoes.setId_edicao(result.getLong("id_edicao"));  
                inscricoes.setSexo(result.getString("sexo"));  
                inscricoes.setIdade(result.getInt("idade"));
                inscricoes.setInstituicao_origem(result.getString("instituicao_origem"));

                inscricoesList.add(inscricoes);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return inscricoesList;

    }
    
    public List<Inscricoes> allInscricoes3(Long edicao_id) throws SQLException {
        List<Inscricoes> inscricoesList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allQuery3)){
               
                statement.setLong(1, edicao_id);

                ResultSet result = statement.executeQuery();
            while (result.next()) {
                
                Inscricoes inscricoes = new Inscricoes();
                
                inscricoes.setNome(result.getString("nome"));
                inscricoes.setData_inscricao(result.getString("data_inscricao"));
                inscricoes.setPagamento(result.getLong("pagamento"));
                inscricoes.setPreco(result.getFloat("preco"));
                inscricoes.setId_inscricao(result.getLong("id"));                
                inscricoes.setTituloev(result.getString("tituloev"));
                inscricoes.setEd(result.getString("ed"));
                inscricoes.setId_edicao(result.getLong("id_edicao")); 
                inscricoes.setSexo(result.getString("sexo"));  
                inscricoes.setIdade(result.getInt("idade"));
                inscricoes.setInstituicao_origem(result.getString("instituicao_origem"));

                inscricoesList.add(inscricoes);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return inscricoesList;

    }
    
    @Override
    public Inscricoes read(Long id) throws SQLException {
        Inscricoes inscricoes = new Inscricoes();
        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    //
                    inscricoes.setNome(result.getString("nome"));
                    inscricoes.setPreco(result.getFloat("preco"));
                    inscricoes.setTituloev(result.getString("tituloev"));
                    inscricoes.setEd(result.getString("ed"));
                    inscricoes.setSexo(result.getString("sexo"));  
                    inscricoes.setIdade(result.getInt("idade"));
                    inscricoes.setInstituicao_origem(result.getString("instituicao_origem"));
                    //
                  
                } else {
                    throw new SQLException("Erro ao visualizar: inscrição não encontrada.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: evento não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar evento.");
            }
        }

        return inscricoes;
    }

    @Override
    public void create(Inscricoes t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Inscricoes t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscricoes> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}