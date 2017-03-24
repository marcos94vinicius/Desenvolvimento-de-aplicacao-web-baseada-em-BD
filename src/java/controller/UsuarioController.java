/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Usuario;

/**
 *
 * @author dskaster
 */
@WebServlet(
        name = "UsuarioController",
        urlPatterns = {
            "/usuario",
            "/usuario/create",
            "/usuario/delete",
            "/usuario/update",
            "/usuario/login",
            "/usuario/lista_usuarios",
            "/foto"})
@MultipartConfig
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario":
                listUsuarios(request, response);
                break;

            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);
                break;

            case "/usuario/delete":
                deleteUsuario(request, response);
                break;

            case "/usuario/update":

                updateUsuario(request, response);
                break;

            case "/usuario/login":
                dispatcher = request.getRequestDispatcher("/view/usuario/login.jsp");
                dispatcher.forward(request, response);
                break;

            case "/usuario/lista_usuarios":
                imprimeDados(request, response);
                break;

            case "/foto":
                
                response.setContentType("image/*");
                response.getOutputStream().write(((Usuario) request.getSession().getAttribute("usuarioLogado")).getFoto());
                response.getOutputStream().flush();
                response.getOutputStream().close();
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/usuario/create":
                createUsuario(request, response);
                break;

            case "/usuario/update":
                updateUsuarioPost(request, response);
                break;
                
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            List<Usuario> usuarioList = dao.all();
            request.setAttribute("usuarioList", usuarioList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usuario = new Usuario();

        Part filePart = request.getPart("foto");
        InputStream is = filePart.getInputStream();

        byte[] b = new byte[(int) filePart.getSize()];
        is.read(b);
        is.close();

        usuario.setLogin(request.getParameter("login"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setNome(request.getParameter("nome"));
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setRg(request.getParameter("rg"));
        usuario.setCracha(request.getParameter("cracha"));
        usuario.setE_mail(request.getParameter("e_mail"));
        usuario.setDt_nasc(request.getParameter("dt_nasc"));
        usuario.setEstado_civil(request.getParameter("estado_civil"));
        usuario.setEscolaridade(request.getParameter("escolaridade"));
        usuario.setProfissao(request.getParameter("profissao"));
        usuario.setInstituicao_origem(request.getParameter("instituicao_origem"));
        usuario.setLogradouro(request.getParameter("logradouro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setCep(request.getParameter("cep"));
        usuario.setCidade(request.getParameter("cidade"));
        usuario.setEstado(request.getParameter("estado"));
        usuario.setTel_tipo(request.getParameter("tel_tipo"));
        usuario.setDdd(request.getParameter("ddd"));
        usuario.setTel_numero(request.getParameter("tel_numero"));
        usuario.setIdade(Integer.parseInt(request.getParameter("idade")));
        usuario.setSexo(request.getParameter("sexo"));
        usuario.setFoto(b);

        try (DAOFactory daoFactory = new DAOFactory();) {
            UsuarioDAO dao = daoFactory.getUsuarioDAO();

            if (request.getParameter("id") != null) {
                usuario.setUid(Long.valueOf(request.getParameter("id")));
                dao.createFB(usuario);
            } else {
                dao.create(usuario);
            }
            response.sendRedirect(request.getContextPath() + "/usuario");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario/create");
        }
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/usuario");

    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();
System.out.println(request.getParameter("id"));
            Usuario usuario = (Usuario) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("u", usuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/atualiza.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario");
        }
    }

    private void updateUsuarioPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usuario = new Usuario();
        usuario.setUid(Long.parseLong(request.getParameter("id")));
        usuario.setLogin(request.getParameter("login"));
        usuario.setNome(request.getParameter("nome"));
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setRg(request.getParameter("rg"));
        usuario.setCracha(request.getParameter("cracha"));
        usuario.setE_mail(request.getParameter("e_mail"));
        usuario.setDt_nasc(request.getParameter("dt_nasc"));
        usuario.setEstado_civil(request.getParameter("estado_civil"));
        usuario.setEscolaridade(request.getParameter("escolaridade"));
        usuario.setProfissao(request.getParameter("profissao"));
        usuario.setInstituicao_origem(request.getParameter("instituicao_origem"));
        usuario.setEstado_civil(request.getParameter("estado_civil"));
        usuario.setTipo_user(Integer.parseInt(request.getParameter("tipo_user")));
        usuario.setLogradouro(request.getParameter("logradouro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setCep(request.getParameter("cep"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setCidade(request.getParameter("cidade"));
        usuario.setEstado(request.getParameter("estado"));
        usuario.setTel_tipo(request.getParameter("tel_tipo"));
        usuario.setDdd(request.getParameter("ddd"));
        usuario.setTel_numero(request.getParameter("tel_numero"));
        usuario.setIdade(Integer.parseInt(request.getParameter("idade")));
        usuario.setSexo(request.getParameter("sexo"));
        
        Part filePart = request.getPart("foto");
        InputStream is = filePart.getInputStream();

        byte[] b = new byte[(int) filePart.getSize()];
        is.read(b);
        is.close();
        
        usuario.setFoto(b);

        if (!request.getParameter("senha").isEmpty()) {
            usuario.setSenha(request.getParameter("senha"));
        }

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            dao.update(usuario);
            HttpSession session = request.getSession(false);
            Usuario usuario2 = (Usuario) session.getAttribute("usuarioLogado");

            if (usuario2.getTipo_user() == 1 || usuario2.getTipo_user() == 2) {

                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            } else {

                response.sendRedirect(request.getContextPath() + "/welcomePart.jsp");
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario/update");
        }
    }

    private void imprimeDados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            Usuario usuario = (Usuario) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("u", usuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/lista_usuarios.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario");
        }
    }

}
