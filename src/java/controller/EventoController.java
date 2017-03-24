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
        name = "EventoController",
        urlPatterns = {
            "/evento",
            "/evento/create",
            "/evento/lista_eventos",
            "/evento/update",
            "/evento/delete"})
public class EventoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/evento":
                listEvento(request, response);
                break;
                
            case "/evento/create":
                listEntPromotoraEv(request, response);
                break;

            case "/evento/delete":
                deleteEvento(request, response);
                break;

            case "/evento/update":
                
                listUpdate(request, response);
                break;
                
            case "/evento/lista_eventos":
                listEvento(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/evento/create":
                createEvento(request, response);
                break;

            case "/evento/update":
                updateEventoPost(request, response);
                break;
                
            case "/evento/lista_eventos":
                listEvento(request, response);
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

    private void listEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();
            List<Evento> eventoList = dao.all();
            
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/lista_eventos.jsp");
        dispatcher.forward(request, response);
    }
    
    //Função importada de EntPromotoraController
    private void listEntPromotoraEv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            List<EntPromotora> promotoraList = dao.all();
            
            request.setAttribute("promotoraList", promotoraList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/create.jsp");
        dispatcher.forward(request, response);

    }
    private void listUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            List<EntPromotora> promotoraList = dao.all();
            
            request.setAttribute("promotoraList", promotoraList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        updateEvento(request, response);

    }

    private void createEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Evento evento = new Evento();
        evento.setTitulo(request.getParameter("titulo"));
        evento.setDescricao(request.getParameter("descricao"));
        evento.setInformacoes(request.getParameter("informacoes"));
        evento.setEntidade_promotora(Long.parseLong(request.getParameter("entidade_promotora")));
        

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            dao.create(evento);

            response.sendRedirect(request.getContextPath()+"/welcome.jsp");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/evento/create");
        }
    }

    private void deleteEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/evento");

    }

    private void updateEvento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            Evento evento = (Evento) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("e", evento);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/atualiza.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/lista_eventos");
        }
    }

    private void updateEventoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Evento evento = new Evento();
        evento.setId(Long.parseLong(request.getParameter("id")));
        evento.setTitulo(request.getParameter("titulo"));
        evento.setDescricao(request.getParameter("descricao"));
        evento.setInformacoes(request.getParameter("informacoes"));
        evento.setEntidade_promotora(Long.parseLong(request.getParameter("entidade_promotora")));
        

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            dao.update(evento);
            HttpSession session = request.getSession(false);
            
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/evento/update");
        }
    }

}
