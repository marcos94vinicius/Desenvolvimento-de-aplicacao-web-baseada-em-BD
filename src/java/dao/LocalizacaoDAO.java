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
import model.Localizacao;
import model.Usuario;

/**
 *
 * @author vinicius
 */
public class LocalizacaoDAO extends DAO<Localizacao> {

    static String allQuery = "SELECT id, nome, logradouro, complemento, bairro,"
            + "cep, cidade, estado, ddd, tel_numero, coordenadas FROM localizacao";

    static String createQuery = "INSERT INTO localizacao(nome, logradouro, complemento, bairro,"
            + "cep, cidade, estado, ddd, tel_numero, coordenadas)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

    static String deleteQuery = "DELETE FROM localizacao WHERE id=?";

    static String readQuery = "SELECT * FROM localizacao WHERE id=?";
    
    static String updateQuery = "UPDATE localizacao SET nome=?, logradouro=?, complemento=?, bairro=?,"
            + "cep=?, cidade=?, estado=?, ddd=?, tel_numero=?, coordenadas=? WHERE id=?";

    public LocalizacaoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Localizacao localizacao) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {

            statement.setString(1, localizacao.getNome());
            statement.setString(2, localizacao.getLogradouro());
            statement.setString(3, localizacao.getComplemento());
            statement.setString(4, localizacao.getBairro());
            statement.setString(5, localizacao.getCep());
            statement.setString(6, localizacao.getCidade());
            statement.setString(7, localizacao.getEstado());
            statement.setString(8, localizacao.getDdd());
            statement.setString(9, localizacao.getTel_numero());
            statement.setString(10, localizacao.getCoordenadas());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                localizacao.setId(result.getLong("id"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_localizacao")) {
                throw new SQLException("Erro ao inserir local: nome já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir local: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir local.");
            }
        }
    }

    @Override
    public Localizacao read(Long id) throws SQLException {
        Localizacao localizacao = new Localizacao();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    localizacao.setId(id);
                    localizacao.setNome(result.getString("nome"));
                    localizacao.setLogradouro(result.getString("logradouro"));
                    localizacao.setComplemento(result.getString("complemento"));
                    localizacao.setBairro(result.getString("bairro"));
                    localizacao.setCep(result.getString("cep"));
                    localizacao.setCidade(result.getString("cidade"));
                    localizacao.setEstado(result.getString("estado"));
                    localizacao.setDdd(result.getString("ddd"));
                    localizacao.setTel_numero(result.getString("tel_numero"));
                    localizacao.setCoordenadas(result.getString("coordenadas"));
                } else {
                    throw new SQLException("Erro ao visualizar: localização não encontrada.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: localização não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar localização.");
            }
        }

        return localizacao;
    }

    @Override
    public void update(Localizacao localizacao) throws SQLException {
        String query;

        query = updateQuery;
       

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            
            statement.setString(1, localizacao.getNome());
            statement.setString(2, localizacao.getLogradouro());
            statement.setString(3, localizacao.getComplemento());
            statement.setString(4, localizacao.getBairro());
            statement.setString(5, localizacao.getCep());
            statement.setString(6, localizacao.getCidade());
            statement.setString(7, localizacao.getEstado());
            statement.setString(8, localizacao.getDdd());
            statement.setString(9, localizacao.getTel_numero());
            statement.setString(10, localizacao.getCoordenadas());
            statement.setLong(11, localizacao.getId());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: localização não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: localização não encontrada.")) {
                throw ex;
            } else if (ex.getMessage().contains("uk_localizacao")) {
                throw new SQLException("Erro ao editar localização: nome já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar localização: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar localização.");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: localização não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: localização não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir localização.");
            }
        }

    }

    @Override
    public List<Localizacao> all() throws SQLException {
        List<Localizacao> localizacaoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Localizacao localizacao = new Localizacao();
                localizacao.setId(result.getLong("id"));
                localizacao.setNome(result.getString("nome"));
                localizacao.setLogradouro(result.getString("logradouro"));
                localizacao.setComplemento(result.getString("complemento"));
                localizacao.setBairro(result.getString("bairro"));
                localizacao.setCep(result.getString("cep"));
                localizacao.setCidade(result.getString("cidade"));
                localizacao.setEstado(result.getString("estado"));
                localizacao.setDdd(result.getString("ddd"));
                localizacao.setTel_numero(result.getString("tel_numero"));
                localizacao.setCoordenadas(result.getString("coordenadas"));

                localizacaoList.add(localizacao);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar localizações.");
        }

        return localizacaoList;

    }    
}
