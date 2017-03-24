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
import model.Usuario;

/**
 *
 * @author dskaster
 */
public class UsuarioDAO extends DAO<Usuario> {

    static String allQuery = "SELECT uid, login, nome, cpf, rg, cracha, e_mail, dt_nasc, estado_civil,"
            + "escolaridade, profissao, instituicao_origem, tipo_user, logradouro, complemento, bairro,"
            + "cep, cidade, estado, tel_tipo, ddd, tel_numero, idade, sexo FROM usuario";

    static String createQuery = "INSERT INTO usuario(login, senha, nome, cpf, rg, cracha, e_mail, dt_nasc,"
            + "estado_civil, escolaridade, profissao, instituicao_origem, logradouro, complemento, bairro,"
            + "cep, cidade, estado, tel_tipo, ddd, tel_numero, idade, sexo, foto)"
            + "VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING uid";
    
    static String createQueryFB = "INSERT INTO usuario(login, senha, nome, cpf, rg, cracha, e_mail, dt_nasc,"
            + "estado_civil, escolaridade, profissao, instituicao_origem, logradouro, complemento, bairro,"
            + "cep, cidade, estado, tel_tipo, ddd, tel_numero, idade, sexo, foto,uid)"
            + "VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) RETURNING uid";

    
    static String deleteQuery = "DELETE FROM usuario WHERE uid=?";

    static String readQuery = "SELECT * FROM usuario WHERE uid=?";
    
    static String updateQuery = "UPDATE usuario SET login=?, nome=?, cpf=?, rg=?, cracha=?, e_mail=?, dt_nasc=?,"
            + "estado_civil=?, escolaridade=?, profissao=?, instituicao_origem=?, tipo_user=?, logradouro=?, complemento=?, bairro=?,"
            + "cep=?, cidade=?, estado=?, tel_tipo=?, ddd=?, tel_numero=?, idade=?, sexo=?, foto=? WHERE uid=?";

    static String updateWithPasswordQuery = "UPDATE usuario "
            + "SET login=?, nome=?, cpf=?, rg=?, cracha=?, e_mail=?, dt_nasc=?,"
            + "estado_civil=?, escolaridade=?, profissao=?, instituicao_origem=?, tipo_user=?, logradouro=?, complemento=?, bairro=?,"
            + "cep=?, cidade=?, estado=?, tel_tipo=?, ddd=?, tel_numero=?, idade=?, sexo=?, senha=md5(?) WHERE uid=?";
    
    static String authenticateQuery = "SELECT uid, nome, tipo_user,foto FROM usuario WHERE login=? AND senha=md5(?)";

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getCpf());
            statement.setString(5, usuario.getRg());
            statement.setString(6, usuario.getCracha());
            statement.setString(7, usuario.getE_mail());
            statement.setString(8, usuario.getDt_nasc());
            statement.setString(9, usuario.getEstado_civil());
            statement.setString(10, usuario.getEscolaridade());
            statement.setString(11, usuario.getProfissao());
            statement.setString(12, usuario.getInstituicao_origem());
            statement.setString(13, usuario.getLogradouro());
            statement.setString(14, usuario.getComplemento());
            statement.setString(15, usuario.getBairro());
            statement.setString(16, usuario.getCep());
            statement.setString(17, usuario.getCidade());
            statement.setString(18, usuario.getEstado());
            statement.setString(19, usuario.getTel_tipo());
            statement.setString(20, usuario.getDdd());
            statement.setString(21, usuario.getTel_numero());
            statement.setInt(22, usuario.getIdade());
            statement.setString(23, usuario.getSexo());
            statement.setBytes(24, usuario.getFoto());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                usuario.setUid(result.getLong("uid"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_usuario_login")) {
                throw new SQLException("Erro ao inserir usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    public void createFB(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQueryFB);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getCpf());
            statement.setString(5, usuario.getRg());
            statement.setString(6, usuario.getCracha());
            statement.setString(7, usuario.getE_mail());
            statement.setString(8, usuario.getDt_nasc());
            statement.setString(9, usuario.getEstado_civil());
            statement.setString(10, usuario.getEscolaridade());
            statement.setString(11, usuario.getProfissao());
            statement.setString(12, usuario.getInstituicao_origem());
            statement.setString(13, usuario.getLogradouro());
            statement.setString(14, usuario.getComplemento());
            statement.setString(15, usuario.getBairro());
            statement.setString(16, usuario.getCep());
            statement.setString(17, usuario.getCidade());
            statement.setString(18, usuario.getEstado());
            statement.setString(19, usuario.getTel_tipo());
            statement.setString(20, usuario.getDdd());
            statement.setString(21, usuario.getTel_numero());
            statement.setInt(22, usuario.getIdade());
            statement.setString(23, usuario.getSexo());
            statement.setBytes(24, usuario.getFoto());
            statement.setLong(25,usuario.getUid());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                usuario.setUid(result.getLong("uid"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_usuario_login")) {
                throw new SQLException("Erro ao inserir usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    
    @Override
    public Usuario read(Long id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUid(id);
                    usuario.setLogin(result.getString("login"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setCpf(result.getString("cpf"));
                    usuario.setRg(result.getString("rg"));
                    usuario.setCracha(result.getString("cracha"));
                    usuario.setE_mail(result.getString("e_mail"));
                    usuario.setDt_nasc(result.getString("dt_nasc"));
                    usuario.setEstado_civil(result.getString("estado_civil"));
                    usuario.setEscolaridade(result.getString("escolaridade"));
                    usuario.setProfissao(result.getString("profissao"));
                    usuario.setInstituicao_origem(result.getString("instituicao_origem"));
                    usuario.setTipo_user(result.getInt("tipo_user"));
                    usuario.setProfissao(result.getString("profissao"));
                    usuario.setLogradouro(result.getString("logradouro"));
                    usuario.setComplemento(result.getString("complemento"));
                    usuario.setBairro(result.getString("bairro"));
                    usuario.setCep(result.getString("cep"));
                    usuario.setCidade(result.getString("cidade"));
                    usuario.setEstado(result.getString("estado"));
                    usuario.setTel_tipo(result.getString("tel_tipo"));
                    usuario.setDdd(result.getString("ddd"));
                    usuario.setTel_numero(result.getString("tel_numero"));
                    usuario.setIdade(result.getInt("idade"));
                    usuario.setSexo(result.getString("sexo"));
                    usuario.setFoto(result.getBytes("foto"));
                } else {
                    throw new SQLException("Erro ao visualizar: usuário não encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar usuário.");
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if (usuario.getSenha() == null) {
            query = updateQuery;
        } else {
            query = updateWithPasswordQuery;
        }

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getCpf());
            statement.setString(4, usuario.getRg());
            statement.setString(5, usuario.getCracha());
            statement.setString(6, usuario.getE_mail());
            statement.setString(7, usuario.getDt_nasc());
            statement.setString(8, usuario.getEstado_civil());
            statement.setString(9, usuario.getEscolaridade());
            statement.setString(10, usuario.getProfissao());
            statement.setString(11, usuario.getInstituicao_origem());
            statement.setInt(12, usuario.getTipo_user());
            statement.setString(13, usuario.getLogradouro());
            statement.setString(14, usuario.getComplemento());
            statement.setString(15, usuario.getBairro());
            statement.setString(16, usuario.getCep());
            statement.setString(17, usuario.getCidade());
            statement.setString(18, usuario.getEstado());
            statement.setString(19, usuario.getTel_tipo());
            statement.setString(20, usuario.getDdd());
            statement.setString(21, usuario.getTel_numero());
            statement.setInt(22, usuario.getIdade());
            statement.setString(23, usuario.getSexo());
            statement.setBytes(24, usuario.getFoto());
            

            if (usuario.getSenha() == null) {
                statement.setLong(25, usuario.getUid());
            } else {
                statement.setString(25, usuario.getSenha());
                statement.setLong(26, usuario.getUid());
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: usuário não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uk_usuario_login")) {
                throw new SQLException("Erro ao editar usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar usuário.");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir usuário.");
            }
        }

    }

    @Override
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setUid(result.getLong("uid"));
                usuario.setLogin(result.getString("login"));
                usuario.setNome(result.getString("nome"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setRg(result.getString("rg"));
                usuario.setCracha(result.getString("cracha"));
                usuario.setE_mail(result.getString("e_mail"));
                usuario.setE_mail(result.getString("dt_nasc"));
                usuario.setEstado_civil(result.getString("estado_civil"));
                usuario.setEscolaridade(result.getString("escolaridade"));
                usuario.setProfissao(result.getString("profissao"));
                usuario.setInstituicao_origem(result.getString("instituicao_origem"));
                usuario.setTipo_user(result.getInt("tipo_user"));
                usuario.setLogradouro(result.getString("logradouro"));
                usuario.setComplemento(result.getString("complemento"));
                usuario.setBairro(result.getString("bairro"));
                usuario.setCep(result.getString("cep"));
                usuario.setCidade(result.getString("cidade"));
                usuario.setEstado(result.getString("estado"));
                usuario.setTel_tipo(result.getString("tel_tipo"));
                usuario.setDdd(result.getString("ddd"));
                usuario.setTel_numero(result.getString("tel_numero"));
                usuario.setIdade(result.getInt("idade"));
                usuario.setSexo(result.getString("sexo"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuarioList;

    }

    public void authenticate(Usuario usuario) throws SQLException, SecurityException {
        try (PreparedStatement statement = connection.prepareStatement(authenticateQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUid(result.getLong("uid"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setTipo_user(result.getInt("tipo_user"));
                    usuario.setFoto(result.getBytes("foto"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }    
    
}
