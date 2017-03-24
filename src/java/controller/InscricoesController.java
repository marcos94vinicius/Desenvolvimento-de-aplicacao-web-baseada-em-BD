/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.InscricaoDAO;
import dao.InscricoesDAO;
import dao.UsuarioDAO;
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
import model.Edicao;
import model.Inscricao;
import model.Usuario;
import model.Inscricoes;

/**
 *
 * @author vinicius
 */
@WebServlet(
        name = "InscricoesController",
        urlPatterns = {
            "/inscricoes",
            "/inscricoes2",
            "/inscricoes3",
            "/inscricoes4",
            "/inscricoes/recibo"})
public class InscricoesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/inscricoes":
                listInscricoes(request, response);
                break; 
                
            case "/inscricoes2":
                listInscricoes2(request, response);
                break; 
                
            case "/inscricoes3":
                listInscricoes3(request, response);
                break; 
                
            case "/inscricoes4":
                listInscricoes4(request, response);
                break; 
                
            case "/inscricoes/recibo":
                mostraRecibo(request, response);
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
    
     private void listInscricoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));            
            
            request.setAttribute("inscricoesList", inscricoesList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricoes/index.jsp");
        dispatcher.forward(request, response);
        
    }
     
    private void listInscricoes2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();

            List<Inscricoes> inscricoesList = dao.allInscricoes2(Long.parseLong(request.getParameter("id_edicao")));            
            
            request.setAttribute("inscricoesList", inscricoesList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricoes/index.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void listInscricoes3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();

            List<Inscricoes> inscricoesList = dao.allInscricoes2(Long.parseLong(request.getParameter("id_edicao")));            
            
            request.setAttribute("inscricoesList", inscricoesList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricoes/index.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void listInscricoes4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));            
            
            request.setAttribute("inscricoesList", inscricoesList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricoes/list.jsp");
        dispatcher.forward(request, response);
        
    }
     
    private void mostraRecibo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();
            Inscricoes inscricoes = (Inscricoes) dao.read(Long.parseLong(request.getParameter("id")));
            
            request.setAttribute("inc2", inscricoes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricoes/recibo.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/inscricoes");
        }
    }

    
}
