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
import model.InscricaoEdicao;
import model.Inscricoes;
import model.Usuario;

/**
 *
 * @author vinicius
 */
@WebServlet(
        name = "InscricaoController",
        urlPatterns = {
            "/inscricao",
            "/inscricao/create",
            "/inscricao/update",
            "/inscricao/delete",
            "/inscricao/listUserEdicao",
            "/inscricao/inscricaoEdicao",
            "/inscricao/create2",
            "/inscricao/bloco",
            "/inscricao/importar"})
public class InscricaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            /*case "/inscricao":
                listInscricao(request, response);
                break;
              */  
            case "/inscricao/create":
                listEdicaoI(request, response);
                break;
            
            case "/inscricao/create2":
                listEdicaoII(request, response);
                break;    
                
            case "/inscricao/inscricaoEdicao":
                listIE(request, response);
                break;

            case "/inscricao/delete":
                deleteInscricao(request, response);
                break;

            case "/inscricao/update":
                
                updateInscricao(request, response);
                break;
                
            case "/inscricao/bloco":
                listarInc(request, response);
                break;
                
            /*case "/evento/lista_eventos":
                listEvento(request, response);
                break;*/
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/inscricao/create":
                createInscricao(request, response);
                break;
                
            case "/inscricao/create2":
                createInscricao2(request, response);
                break;

            case "/inscricao/update":
                updateInscricaoPost(request, response);
                break;
                
            case "/inscricao/importar":
                importar(request, response);
                break;
            /*
            case "/evento/lista_eventos":
                listEvento(request, response);
                break;*/
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
    
     private void listEdicaoI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();
            List<Edicao> edicaoList = dao.all();
            
            request.setAttribute("edicaoList", edicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/create.jsp");
        dispatcher.forward(request, response);
        
    }
     
    private void importar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         try (DAOFactory daoFactory = new DAOFactory();) {
            InscricaoDAO dao = daoFactory.getInscricaoDAO();

            List<Inscricao> inscricaoList = dao.all();            
            System.out.println(request.getParameter("id_edicao"));
            for(int i=1; i<inscricaoList.size();i++){
                
                Inscricao inscricao = new Inscricao();
                
                if(inscricaoList.get(i).getId_edicao() == (Long.parseLong(request.getParameter("id_edicao")))){
                    
                    inscricao.setVai_com_outra_pessoa(inscricaoList.get(i).getVai_com_outra_pessoa());
                    inscricao.setFicou_sabendo(inscricaoList.get(i).getFicou_sabendo());
                    inscricao.setFormas_pagamento(inscricaoList.get(i).getFormas_pagamento());
                    inscricao.setId_usuario(inscricaoList.get(i).getId_usuario());
                    inscricao.setId_edicao(Long.parseLong(request.getParameter("id_edicao2")));
                    inscricao.setData_inscricao(inscricaoList.get(i).getData_inscricao());
                    dao.create(inscricao);
                }
            }
            
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        } 
       

        response.sendRedirect(request.getContextPath()+"/welcome.jsp");
        
    }
     
    private void listarInc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();
            List<Edicao> edicaoList = dao.all();
            
            request.setAttribute("edicaoList", edicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/bloco.jsp");
        dispatcher.forward(request, response);
        
    }
     
     private void listEdicaoII(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao2 = daoFactory.getUsuarioDAO();

            Usuario usuario = (Usuario) dao2.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("user", usuario);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario");
        }
         
         try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();
            List<Edicao> edicaoList = dao.all();
            System.out.println();
            request.setAttribute("edicaoList", edicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/create2.jsp");
        dispatcher.forward(request, response);
        
    }

    private void listInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();
            List<Inscricao> inscricaoList = dao.all();
            
            request.setAttribute("inscricaoList", inscricaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listIE(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricaoDAO dao = daoFactory.getInscricaoDAO();
            List<InscricaoEdicao> inscricaoEdicaoList = dao.listInscricaoEdicao(Long.parseLong(request.getParameter("uid")));
            
            request.setAttribute("inscricaoEdicaoList", inscricaoEdicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/inscricaoEdicao.jsp");
        dispatcher.forward(request, response);
    }
  
    private void createInscricao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        float resp = 0;
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            Edicao edicao = (Edicao) dao.read(Long.parseLong(request.getParameter("id_edicao")));
            resp = edicao.getPreco();

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao");
        }
        
        Inscricao inscricao = new Inscricao();

        inscricao.setVai_com_outra_pessoa(Long.parseLong(request.getParameter("vai_com_outra_pessoa")));
        inscricao.setFicou_sabendo(request.getParameter("ficou_sabendo"));
        inscricao.setFormas_pagamento(request.getParameter("formas_pagamento"));
        inscricao.setId_usuario(Long.valueOf(request.getParameter("id_usuario")));
        inscricao.setId_edicao(Long.parseLong(request.getParameter("id_edicao"))); 
        inscricao.setData_inscricao(request.getParameter("data_inscricao"));
        
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();
            dao.create(inscricao);
            
            HttpSession session = request.getSession(false);
            Usuario usuario2 = (Usuario) session.getAttribute("usuarioLogado");

            if(usuario2.getTipo_user() == 1 || usuario2.getTipo_user() == 2){
                
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            }
            else{
                
                response.sendRedirect(request.getContextPath() + "/welcomePart.jsp");
            }

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/inscricao/create");
        }
    }
    
    private void createInscricao2(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        
        Inscricao inscricao = new Inscricao();

        inscricao.setVai_com_outra_pessoa(Long.parseLong(request.getParameter("vai_com_outra_pessoa")));
        inscricao.setFicou_sabendo(request.getParameter("ficou_sabendo"));
        inscricao.setFormas_pagamento(request.getParameter("formas_pagamento"));
        inscricao.setId_usuario(Long.parseLong(request.getParameter("id_usuario")));
        inscricao.setId_edicao(Long.parseLong(request.getParameter("id_edicao"))); 
        inscricao.setData_inscricao(request.getParameter("data_inscricao"));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();

            dao.create(inscricao);
            
            HttpSession session = request.getSession(false);
            Usuario usuario2 = (Usuario) session.getAttribute("usuarioLogado");

            if(usuario2.getTipo_user() == 1 || usuario2.getTipo_user() == 2){
                
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            }
            else{
                
                response.sendRedirect(request.getContextPath() + "/welcomePart.jsp");
            }

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/inscricao/create");
        }
    }

    private void deleteInscricao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/edicao");

    }

    private void updateInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();

            Inscricao inscricao = (Inscricao) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("i", inscricao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/update.jsp");
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/inscricao");
        }
    }

    private void updateInscricaoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Inscricao inscricao = new Inscricao();
        System.out.println("Cheguei carai59");
        inscricao.setId(Long.parseLong(request.getParameter("id")));
        inscricao.setPagamento(Long.parseLong(request.getParameter("pagamento")));
        inscricao.setStatus(Long.parseLong(request.getParameter("status")));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();

            dao.update(inscricao);
            HttpSession session = request.getSession(false);
            
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/inscricao/update");
        }
    }

}
