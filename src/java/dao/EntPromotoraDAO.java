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
import model.EntPromotora;

/**
 *
 * @author dskaster
 */
public class EntPromotoraDAO extends DAO<EntPromotora> {

    static String allQuery = "SELECT id, nome, descricao FROM ent_promotora";

    static String createQuery = "INSERT INTO ent_promotora(nome, descricao)"
            + "VALUES (?, ?) RETURNING id";

    static String deleteQuery = "DELETE FROM ent_promotora WHERE id=?";

    static String readQuery = "SELECT * FROM ent_promotora WHERE id=?";
    
    static String updateQuery = "UPDATE ent_promotora SET nome=?, descricao=? WHERE id=?";

    public EntPromotoraDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(EntPromotora promotora) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, promotora.getNome());
            statement.setString(2, promotora.getDescricao());           

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                promotora.setId(result.getInt("id"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_ent_promotora")) {
                throw new SQLException("Erro ao inserir entidade: entidade já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir evento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir entidade.");
            }
        }
    }

    @Override
    public EntPromotora read(Long id) throws SQLException {
        EntPromotora promotora = new EntPromotora();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    promotora.setId(id);
                    promotora.setNome(result.getString("nome"));
                    promotora.setDescricao(result.getString("descricao"));
                                      
                } else {
                    throw new SQLException("Erro ao visualizar: entidade promotora não encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: entidade promotora não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar entidade promotora.");
            }
        }

        return promotora;
    }

    @Override
    public void update(EntPromotora promotora) throws SQLException {
        String query;
        query = updateQuery;

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, promotora.getNome());
            statement.setString(2, promotora.getDescricao());
            statement.setLong(3, promotora.getId());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: entidade promotora não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: entidade promotora não encontrada.")) {
                throw ex;
            } else if (ex.getMessage().contains("uk_ent_promotora")) {
                throw new SQLException("Erro ao editar entidade promotora: nome já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar entidade promotora: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar entidade promotora.");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: entidade promotora não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: entidade promotora não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir entidade promotora.");
            }
        }

    }

    @Override
    public List<EntPromotora> all() throws SQLException {
        List<EntPromotora> promotoraList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                EntPromotora promotora = new EntPromotora();
                promotora.setId(result.getLong("id"));
                promotora.setNome(result.getString("nome"));
                promotora.setDescricao(result.getString("descricao"));
                
                promotoraList.add(promotora);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar entidade promotora.");
        }

        return promotoraList;

    }
    
}