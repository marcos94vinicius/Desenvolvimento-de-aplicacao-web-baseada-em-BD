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

/**
 *
 * @author vinicius
 */
public class InscricaoDAO extends DAO<Inscricao> {

    static String allQuery = "SELECT * FROM inscricao";

    static String createQuery = "INSERT INTO inscricao(vai_com_outra_pessoa, ficou_sabendo,"
            + "formas_pagamento, id_usuario, id_edicao, data_inscricao) VALUES(?, ?, ?, ?, ?, ?) RETURNING id";

    static String deleteQuery = "DELETE FROM inscricao WHERE id=?";

    static String readQuery = "SELECT pagamento, status, preco FROM inscricao WHERE id=?";
    
    static String updateQuery = "UPDATE inscricao SET pagamento=?, status=? WHERE id=?";
    
    static String listInscricaoEdicao = "SELECT edicao.ed, edicao.tituloev, usuario.uid, inscricao.id FROM edicao INNER JOIN inscricao "
            + "ON edicao.id = inscricao.id_edicao INNER JOIN usuario ON inscricao.id_usuario = usuario.uid";

    public InscricaoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Inscricao inscricao) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            
            statement.setLong(1, inscricao.getVai_com_outra_pessoa());
            statement.setString(2, inscricao.getFicou_sabendo());
            statement.setString(3, inscricao.getFormas_pagamento());
            statement.setLong(4, inscricao.getId_usuario());
            statement.setLong(5, inscricao.getId_edicao());
            statement.setString(6, inscricao.getData_inscricao());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                inscricao.setId(result.getLong("id"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao fazer inscrição: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao fazer inscrição.");
            }
        }
    }

    @Override
    public Inscricao read(Long id) throws SQLException {
        Inscricao inscricao = new Inscricao();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    inscricao.setId(id);
                    //
                    inscricao.setPagamento(result.getLong("pagamento"));
                    inscricao.setStatus(result.getLong("status"));
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

        return inscricao;
    }

    @Override
    public void update(Inscricao inscricao) throws SQLException {
        String query;
        query = updateQuery;

        try (PreparedStatement statement = connection.prepareStatement(query);) {
           
            statement.setLong(1, inscricao.getPagamento());
            statement.setLong(2, inscricao.getStatus());
            statement.setLong(3, inscricao.getId());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: evento não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: evento não encontrado.")) {
                throw ex;
            }else if (ex.getMessage().contains("not-null")) {
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
    public List<Inscricao> all() throws SQLException {
        List<Inscricao> inscricaoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Inscricao inscricao = new Inscricao();
                
                inscricao.setId(result.getLong("id"));
                inscricao.setVai_com_outra_pessoa(result.getLong("vai_com_outra_pessoa"));
                inscricao.setFicou_sabendo(result.getString("ficou_sabendo"));
                inscricao.setFormas_pagamento(result.getString("formas_pagamento"));
                inscricao.setId_usuario(result.getLong("id_usuario"));
                inscricao.setId_edicao(result.getLong("id_edicao"));
                inscricao.setData_inscricao(result.getString("data_inscricao"));
                inscricao.setPagamento(result.getLong("pagamento"));
                inscricao.setPreco(result.getLong("preco"));

                inscricaoList.add(inscricao);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return inscricaoList;

    }
       
    public List<InscricaoEdicao> listInscricaoEdicao(Long idUsuario) throws SQLException {
        List<InscricaoEdicao> inscricaoEdicaoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(listInscricaoEdicao);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                InscricaoEdicao inscricaoEdicao = new InscricaoEdicao();
                
                inscricaoEdicao.setId(result.getLong("id"));
                inscricaoEdicao.setEd(result.getString("ed"));
                inscricaoEdicao.setTituloev(result.getString("tituloev"));

                if(result.getLong("uid") == idUsuario){
                    
                    inscricaoEdicaoList.add(inscricaoEdicao);
                }
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar eventos.");
        }

        return inscricaoEdicaoList;

    }
}