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
import model.Periodo;
import model.Periodo;

/**
 *
 * @author vinicius
 */
public class PeriodoDAO extends DAO<Periodo>{
   
    static String allQuery = "SELECT * FROM periodo";

    static String createQuery = "INSERT INTO periodo(dia, hora_ini, hora_fim, id_edicao)"
            + "VALUES (?, ?, ?, ?) RETURNING id";

    public PeriodoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Periodo periodo) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, periodo.getDia());
            statement.setString(2, periodo.getHora_ini());           
            statement.setString(3, periodo.getHora_fim());           
            statement.setInt(4, periodo.getId_edicao());           
                
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                periodo.setId(result.getInt("id"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_ent_periodo")) {
                throw new SQLException("Erro ao inserir entidade: entidade já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir evento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir entidade.");
            }
        }
    }

    @Override
    public List<Periodo> all() throws SQLException {
        List<Periodo> periodoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Periodo periodo = new Periodo();
                periodo.setId(result.getInt("id"));
                periodo.setDia(result.getString("dia"));
                periodo.setHora_ini(result.getString("hora_ini"));
                periodo.setHora_fim(result.getString("hora_fim"));
                periodo.setId_edicao(result.getInt("id_edicao"));
                
                periodoList.add(periodo);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar entidade periodo.");
        }

        return periodoList;

    }

    @Override
    public Periodo read(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Periodo t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
