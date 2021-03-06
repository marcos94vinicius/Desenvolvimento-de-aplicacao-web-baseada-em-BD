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
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author dskaster
 */
@WebServlet(name = "LoginController", urlPatterns = {
    "/login",
    "/logout",
    ""})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "":
                verificaLogin(request, response);
                break;

            case "/logout":
                doLogout(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/login":                
                //
                if ((request.getParameter("ll") != null) && (request.getParameter("ll").compareTo("loginFB") == 0)) {
                    doLoginWithFB(request, response);
                } else {                    
                    doLogin(request, response);
                }
                break;
                //
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verificaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//obtém a sessão sem forçar criá-la (false)
        RequestDispatcher dispatcher = null;

        if (session != null && session.getAttribute("usuarioLogado") != null) {
            //Logado, redireciona para página inicial
          
        } else {
            //Não logado, redireciona para página de login
            dispatcher = request.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        switch (request.getServletPath()) {
            case "/login":
                Usuario usuario = new Usuario();
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                //usuario.setTipo_user(Integer.parseInt(request.getParameter("tipo_user")));

                HttpSession session;
                session = request.getSession();

                try (DAOFactory daoFactory = new DAOFactory();) {
                    UsuarioDAO dao = daoFactory.getUsuarioDAO();//observe que o tipo é da subclasse (UsuarioDAO) e não da interface/classe abstrata (DAO), pois o método authenticate só é definido na subclasse

                    dao.authenticate(usuario);

                    session.setAttribute("usuarioLogado", usuario);
                    
                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    session.setAttribute("error", ex.getMessage());
                }
                
                if(session.getAttribute("usuarioLogado") != null){
                    
                    Usuario usuario2 = (Usuario) session.getAttribute("usuarioLogado");
                
                    if(usuario2.getTipo_user() == 3){
                        // RequestDispatcher dispatcher = request.getRequestDispatcher("/welcomeMembro.jsp");
                        response.sendRedirect(request.getContextPath() + "/welcomePart.jsp");
                    }
                    else{
                    
                        response.sendRedirect(request.getContextPath() + "/welcome.jsp");
                    }
                }
                else{
                    
                    response.sendRedirect(request.getContextPath() + "/view/usuario/login.jsp");
                }
            }
    }
    
     public void doLoginWithFB(HttpServletRequest request, HttpServletResponse response) {
        try {
           System.out.println(request.getParameter("id"));
            DAOFactory dao = new DAOFactory();
            UsuarioDAO ud = dao.getUsuarioDAO();
            Usuario u = new Usuario();
            u = ud.read(Long.valueOf(request.getParameter("id")));
           

            request.getSession().setAttribute("usuarioLogado", u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();//Invalida a sessão; será retornada uma sessão nova no próximo getSession
        }

        response.sendRedirect(request.getContextPath() + "/");
    }

}
