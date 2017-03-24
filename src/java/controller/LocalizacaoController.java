/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Localizacao;

/**
 *
 * @author vinicius
 */
@WebServlet(
        name = "LocalizacaoController",
        urlPatterns = {
            "/localizacao",
            "/localizacao/create",
            "/localizacao/delete",
            "/localizacao/update"})
public class LocalizacaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/localizacao":
                listLocalizacao(request, response);
                break;
                
            case "/localizacao/create":
                dispatcher = request.getRequestDispatcher("/view/localizacao/create.jsp");
                dispatcher.forward(request, response);
                break;

            case "/localizacao/delete":
                deleteLocalizacao(request, response);
                break;

            case "/localizacao/update":
                
                updateLocalizacao(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/localizacao/create":
                createLocalizacao(request, response);
                break;

            case "/localizacao/update":
                updateLocalizacaoPost(request, response);
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

    private void listLocalizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            List<Localizacao> localizacaoList = dao.all();
            request.setAttribute("localizacaoList", localizacaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/localizacao/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createLocalizacao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Localizacao localizacao = new Localizacao();

        localizacao.setNome(request.getParameter("nome"));
        localizacao.setLogradouro(request.getParameter("logradouro"));
        localizacao.setComplemento(request.getParameter("complemento"));
        localizacao.setBairro(request.getParameter("bairro"));
        localizacao.setCep(request.getParameter("cep"));
        localizacao.setCidade(request.getParameter("cidade"));
        localizacao.setEstado(request.getParameter("estado"));
        localizacao.setDdd(request.getParameter("ddd"));
        localizacao.setTel_numero(request.getParameter("tel_numero"));
        localizacao.setCoordenadas(request.getParameter("coordenadas"));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            dao.create(localizacao);
            
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/localizacao/create");
        }
    }

    private void deleteLocalizacao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/localizacao");

    }

    private void updateLocalizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            Localizacao localizacao= (Localizacao) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("l", localizacao);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/localizacao/update.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/localizacao");
        }
    }

    private void updateLocalizacaoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Localizacao localizacao = new Localizacao();
        
        localizacao.setId(Long.parseLong(request.getParameter("id")));
        localizacao.setNome(request.getParameter("nome"));
        localizacao.setLogradouro(request.getParameter("logradouro"));
        localizacao.setComplemento(request.getParameter("complemento"));
        localizacao.setBairro(request.getParameter("bairro"));
        localizacao.setCep(request.getParameter("cep"));
        localizacao.setComplemento(request.getParameter("complemento"));
        localizacao.setCidade(request.getParameter("cidade"));
        localizacao.setEstado(request.getParameter("estado"));
        localizacao.setDdd(request.getParameter("ddd"));
        localizacao.setTel_numero(request.getParameter("tel_numero"));
        localizacao.setCoordenadas(request.getParameter("coordenadas"));        

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            dao.update(localizacao);
            HttpSession session = request.getSession(false);
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/localizacao/update");
        }
    }
}
