/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jdbc.ConnectionFactory;
import model.Localizacao;

/**
 *
 * @author dskaster
 */
public class DAOFactory implements AutoCloseable {

    private Connection connection = null;

    public DAOFactory() throws ClassNotFoundException, IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
    }

    public void beginTransaction() throws SQLException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao abrir transação.");
        }
    }


   public void commitTransaction() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void rollbackTransaction() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao executar transação.");
        }
    }

    public void endTransaction() throws SQLException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao fechar conexão ao banco de dados.");
        }
    }

    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO(connection);
    }
    public EventoDAO getEventoDAO(){
        return new EventoDAO(connection);
    }
    public EntPromotoraDAO getEntPromotoraDAO(){
        return new EntPromotoraDAO(connection);
    }
    public LocalizacaoDAO getLocalizacaoDAO(){
        return new LocalizacaoDAO(connection);
    }
    public EdicaoDAO getEdicaoDAO(){
        return new EdicaoDAO(connection);
    }
    public InscricaoDAO getInscricaoDAO(){
        return new InscricaoDAO(connection);
    }
    public InscricoesDAO getInscricoesDAO(){
        return new InscricoesDAO(connection);
    }
    public PeriodoDAO getPeriodoDAO(){
        return new PeriodoDAO(connection);
    }
    
    @Override
    public void close() throws SQLException {
        closeConnection();
    }
}