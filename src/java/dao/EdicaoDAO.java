/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Edicao;
import model.Periodo;

/**
 *
 * @author vinicius
 */
public class EdicaoDAO extends DAO<Edicao> {

    static String allQuery = "SELECT id, ed, preco, localizacao, tituloev, status_inscricoes FROM edicao";

    static String createQuery = "INSERT INTO edicao(ed, preco, localizacao, tituloev)"
            + "VALUES (?, ?, ?, ?) RETURNING id";

    static String deleteQuery = "DELETE FROM edicao WHERE id=?";

    static String readQuery = "SELECT * FROM edicao WHERE id=?";
    
    static String updateQuery = "UPDATE edicao SET ed=?, preco=?, localizacao=?, status_inscricoes=?"
            + " WHERE id=?";

    public EdicaoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Edicao edicao) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, edicao.getEd());
            statement.setFloat(2, edicao.getPreco());
            statement.setLong(3, edicao.getLocalizacao());
            statement.setString(4, edicao.getTituloev());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                edicao.setId(result.getLong("id"));
            }
            for(Periodo p:edicao.getPeriodoList()){
                
                p.setId_edicao(edicao.getId().intValue());
                new DAOFactory().getPeriodoDAO().create(p);
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir edição: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir edição.");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EdicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EdicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
     public Edicao read(Long id) throws SQLException {
        Edicao edicao = new Edicao();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    edicao.setId(id);
                    edicao.setEd(result.getString("ed"));
                    edicao.setPreco(result.getFloat("preco"));
                    edicao.setLocalizacao(result.getLong("localizacao"));
                    edicao.setTituloev(result.getString("tituloev"));
                    edicao.setStatus_inscricoes(result.getLong("status_inscricoes"));
                    
                    
                } else {
                    throw new SQLException("Erro ao visualizar: evento não encontrado.");
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

        return edicao;
    }

    @Override
    public void update(Edicao edicao) throws SQLException {
        String query;
        query = updateQuery;

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, edicao.getEd());
            statement.setFloat(2, edicao.getPreco());
            statement.setLong(3, edicao.getLocalizacao());
            statement.setLong(4, edicao.getStatus_inscricoes());
            statement.setLong(5, edicao.getId());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: edição não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: edição não encontrada.")) {
                throw ex;
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar edição: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar edição.");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: edição não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: edição não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir edição.");
            }
        }

    }

    @Override
    public List<Edicao> all() throws SQLException {
        List<Edicao> edicaoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Edicao edicao = new Edicao();
                edicao.setId(result.getLong("id"));
                edicao.setEd(result.getString("ed"));
                edicao.setPreco(result.getFloat("preco"));
                edicao.setLocalizacao(result.getLong("localizacao"));
                edicao.setTituloev(result.getString("tituloev"));
                edicao.setStatus_inscricoes(result.getLong("status_inscricoes"));
                
                edicaoList.add(edicao);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar edição.");
        }

        return edicaoList;

    }
    
}