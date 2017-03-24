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
import model.Evento;

/**
 *
 * @author dskaster
 */
public class EventoDAO extends DAO<Evento> {

    static String allQuery = "SELECT id, titulo, descricao, informacoes, entidade_promotora FROM evento";

    static String createQuery = "INSERT INTO evento(titulo, descricao, informacoes, entidade_promotora)"
            + "VALUES (?, ?, ?, ?) RETURNING id";

    static String deleteQuery = "DELETE FROM evento WHERE id=?";

    static String readQuery = "SELECT * FROM evento WHERE id=?";
    
    static String updateQuery = "UPDATE evento SET titulo=?, descricao=?, informacoes=?, "
            + "entidade_promotora=? WHERE id=?";

    public EventoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Evento evento) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, evento.getTitulo());
            statement.setString(2, evento.getDescricao());
            statement.setString(3, evento.getInformacoes());
            statement.setLong(4, evento.getEntidade_promotora());
           

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                evento.setId(result.getLong("id"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_evento_titulo")) {
                throw new SQLException("Erro ao inserir evento: evento já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir evento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir evento.");
            }
        }
    }

    @Override
    public Evento read(Long id) throws SQLException {
        Evento evento = new Evento();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    evento.setId(id);
                    evento.setTitulo(result.getString("titulo"));
                    evento.setDescricao(result.getString("descricao"));
                    evento.setInformacoes(result.getString("informacoes"));
                    evento.setEntidade_promotora(result.getLong("entidade_promotora"));
                    
                    
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

        return evento;
    }

    @Override
    public void update(Evento evento) throws SQLException {
        String query;
        query = updateQuery;

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, evento.getTitulo());
            statement.setString(2, evento.getDescricao());
            statement.setString(3, evento.getInformacoes());
            statement.setLong(4, evento.getEntidade_promotora());
            statement.setLong(5, evento.getId());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: evento não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: evento não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uk_evento_titulo")) {
                throw new SQLException("Erro ao editar evento: titulo já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar evento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar evento.");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: evento não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: evento não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir evento.");
            }
        }

    }

    @Override
    public List<Evento> all() throws SQLException {
        List<Evento> eventoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Evento evento = new Evento();
                evento.setId(result.getLong("id"));
                evento.setTitulo(result.getString("titulo"));
                evento.setDescricao(result.getString("descricao"));
                evento.setInformacoes(result.getString("informacoes"));
                evento.setEntidade_promotora(result.getLong("entidade_promotora"));

                eventoList.add(evento);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return eventoList;

    }
    
}
