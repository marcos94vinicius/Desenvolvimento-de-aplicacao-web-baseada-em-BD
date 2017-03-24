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
import model.EntPromotora;
import model.Evento;

/**
 *
 * @author dskaster
 */
@WebServlet(
        name = "EntPromotoraController",
        urlPatterns = {
            "/entidade_promotora",
            "/entidade_promotora/create",
            "/entidade_promotora/update",
            "/entidade_promotora/delete"})
public class EntPromotoraController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/entidade_promotora":
                listEntPromotora(request, response);
                break;
                
            case "/entidade_promotora/create":
                dispatcher = request.getRequestDispatcher("/view/entidade_promotora/create.jsp");
                dispatcher.forward(request, response);
                break;

            case "/entidade_promotora/delete":
                deleteEntPromotora(request, response);
                break;

            case "/entidade_promotora/update":
                
                updateEntPromotora(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/entidade_promotora/create":
                createEntPromotora(request, response);
                break;

            case "/entidade_promotora/update":
                updateEntPromotoraPost(request, response);
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

    private void listEntPromotora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            List<EntPromotora> promotoraList = dao.all();
            
            request.setAttribute("promotoraList", promotoraList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/entidade_promotora/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createEntPromotora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntPromotora promotora = new EntPromotora();
        promotora.setNome(request.getParameter("nome"));
        promotora.setDescricao(request.getParameter("descricao"));        

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            dao.create(promotora);

            response.sendRedirect(request.getContextPath()+"/welcome.jsp");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/entidade_promotora/create");
        }
    }

    private void deleteEntPromotora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/entidade_promotora");

    }

    private void updateEntPromotora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            EntPromotora promotora = (EntPromotora) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("ep", promotora);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/entidade_promotora/update.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/entidade_promotora");
        }
    }

    private void updateEntPromotoraPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntPromotora promotora = new EntPromotora();
        promotora.setId(Long.parseLong(request.getParameter("id")));
        promotora.setNome(request.getParameter("nome"));
        promotora.setDescricao(request.getParameter("descricao"));        

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            dao.update(promotora);
            HttpSession session = request.getSession(false);
            
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/entidade_promotora/update");
        }
    }

}
