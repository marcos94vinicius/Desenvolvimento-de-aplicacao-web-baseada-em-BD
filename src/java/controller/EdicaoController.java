/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.InscricoesDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aux;
import model.Edicao;
import model.EntPromotora;
import model.Estatisticas;
import model.Evento;
import model.Inscricao;
import model.Localizacao;
import model.Inscricoes;
import model.Periodo;


/**
 *
 * @author vinicius
 */
@WebServlet(
        name = "EdicaoController",
        urlPatterns = {
            "/edicao",
            "/edicao/create",
            "/edicao/update",
            "/edicao/delete",
            "/edicao/lista_edicoes",
            "/estatisticas",
            "/estatisticas/total", 
            "/estatisticas/total_sexo",
            "/estatisticas/total_idade",
            "/estatisticas/combinacao",
            "/estatisticas/instituicao",
            "/edicao/pesquisar",
            "/estatisticas/multiplas",
            "/estatisticas/index2"})
public class EdicaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/edicao":
                listEdicao(request, response);
                break;
                
            case "/edicao/create":
                listLocal(request, response);
                break;

            case "/edicao/delete":
                deleteEdicao(request, response);
                break;

            case "/edicao/update":
                
                listLocal2(request, response);
                break;
                
            case "/edicao/lista_edicoes":
                listEdicao2(request, response);
                break;
                
            case "/estatisticas":
                est(request, response);
                break;
               
            case "/estatisticas/total":
                estTotal(request, response);
                break;
                
            case "/edicao/pesquisar":
                dispatcher = request.getRequestDispatcher("/view/edicao/pesquisar.jsp");
                dispatcher.forward(request, response);
                break;
             
            case "/estatisticas/multiplas":
                
                listEdicoes(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/edicao/create":
                createEdicao(request, response);
                break;

            case "/edicao/update":
                updateEdicaoPost(request, response);
                break;
                
            case "/estatisticas/total_sexo":
                estSexo(request, response);
                break;
                
            case "/estatisticas/total_idade":
                estIdade(request, response);
                break;
                
            case "/estatisticas/combinacao":
                estCombinacao(request, response);
                break;
                
            case "/estatisticas/instituicao":
                estInstituicao(request, response);
                break;
                
            case "/edicao/pesquisar":
                listEventoPesquisa(request, response);
                break;
                
            case "/estatisticas/index2":
                
                multEstatisticas(request, response);
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
    
    
    
    private void est(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (DAOFactory daoFactory = new DAOFactory();) {
            
            Estatisticas estatisticas = new Estatisticas();
            estatisticas.setEdicao_id(Long.parseLong(request.getParameter("id_edicao")));
            
            request.setAttribute("estatisticas", estatisticas);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/index.jsp");
        dispatcher.forward(request, response);

    }
    
    private void estTotal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id")));            
            
            int inscritos = 0;
            for(int i=0; i<inscricoesList.size();i++){
                
                inscritos++;
            }
            
            request.setAttribute("inscritos", inscritos);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/total.jsp");
        dispatcher.forward(request, response);

    }
    
    private void estSexo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();
            
            String h = (request.getParameter("sexo"));

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));
            
            int inscritos = 0;
            for(int i=0; i<inscricoesList.size();i++){
                System.out.println(inscricoesList.get(i).getSexo());
                if(inscricoesList.get(i).getSexo().equals(h))
                    inscritos++;
            }
            
            request.setAttribute("inscritos", inscritos);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/total_sexo.jsp");
        dispatcher.forward(request, response);

    }
    
    private void estIdade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();
            
            int minimo = Integer.parseInt(request.getParameter("id_min"));
            int maximo = Integer.parseInt(request.getParameter("id_max"));

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));
            
            int inscritos = 0;
            for(int i=0; i<inscricoesList.size();i++){
                System.out.println(inscricoesList.get(i).getSexo());
                if((inscricoesList.get(i).getIdade() >= minimo) && (inscricoesList.get(i).getIdade() <= maximo))
                    inscritos++;
            }
            
            request.setAttribute("inscritos", inscritos);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/total_idade.jsp");
        dispatcher.forward(request, response);

    }
    
    private void estCombinacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();
            
            int minimo = Integer.parseInt(request.getParameter("id_min"));
            int maximo = Integer.parseInt(request.getParameter("id_max"));
            String h = (request.getParameter("sexo"));

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));
            
            int inscritos = 0;
            for(int i=0; i<inscricoesList.size();i++){
                System.out.println(inscricoesList.get(i).getSexo());
                if((inscricoesList.get(i).getIdade() >= minimo) && (inscricoesList.get(i).getIdade() <= maximo) && (inscricoesList.get(i).getSexo().equals(h)))
                    inscritos++;
            }
            
            request.setAttribute("inscritos", inscritos);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/combinacao.jsp");
        dispatcher.forward(request, response);

    }
    
    private void estInstituicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricoesDAO dao = daoFactory.getInscricoesDAO();
            
            String h = (request.getParameter("instituicao"));

            List<Inscricoes> inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("id_edicao")));
            
            int inscritos = 0;
            for(int i=0; i<inscricoesList.size();i++){
                
                if(inscricoesList.get(i).getInstituicao_origem().equals(h))
                    inscritos++;
            }
            
            request.setAttribute("inscritos", inscritos);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/instituicao.jsp");
        dispatcher.forward(request, response);

    }

     private void listLocal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            List<Localizacao> localizacaoList = dao.all();
            request.setAttribute("localizacaoList", localizacaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        listEv(request, response);

    }
     
    private void listLocal2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalizacaoDAO();

            List<Localizacao> localizacaoList = dao.all();
            request.setAttribute("localizacaoList", localizacaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        listEv2(request, response);

    }
    private void listEv2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();
            List<Evento> eventoList = dao.all();
            
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        updateEdicao(request, response);
    }
    private void listEv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();
            List<Evento> eventoList = dao.all();
            
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/create.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            
            float s = 0;
            DAO dao = daoFactory.getEdicaoDAO();
            DAO dao2 = daoFactory.getInscricaoDAO();
            List<Inscricao> inscricaoList = dao2.all();

            List<Edicao> edicaoList = dao.all();
            
            for(int i=0; i<edicaoList.size();i++){
                
                for(int j=0; j<inscricaoList.size();j++){
                    
                    if(inscricaoList.get(j).getPagamento() == 1 && Objects.equals(inscricaoList.get(j).getId_edicao(), edicaoList.get(i).getId())){
                        
                        s += edicaoList.get(i).getPreco();
                    }
                }
                edicaoList.get(i).setRenda(s);
                s=0;
            }
            
            request.setAttribute("edicaoList", edicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/index.jsp");
        dispatcher.forward(request, response);

    }
    
    private void listEdicao2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            List<Edicao> edicaoList = dao.all();
            
            request.setAttribute("edicaoList", edicaoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/lista_edicoes.jsp");
        dispatcher.forward(request, response);

    }

    private void createEdicao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Edicao edicao = new Edicao();
        edicao.setEd(request.getParameter("ed"));
        edicao.setPreco(Float.parseFloat(request.getParameter("preco")));
        edicao.setLocalizacao(Long.parseLong(request.getParameter("localizacao")));
        edicao.setTituloev(request.getParameter("tituloev"));
        Periodo p = new Periodo();
        int i = Integer.valueOf(request.getParameter("quantidade"));
        for(int j=1; j<=i;j++){
            
            p.setDia(request.getParameter("dia"+j));
            p.setHora_ini(request.getParameter("hora"+j));
            p.setHora_fim(request.getParameter("horaF"+j));
            edicao.getPeriodoList().add(p);
        }

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            dao.create(edicao);

            response.sendRedirect(request.getContextPath()+"/welcome.jsp");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao/create");
        }
    }

    private void deleteEdicao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/edicao");

    }

    private void updateEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            Edicao edicao = (Edicao) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("edi", edicao);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/update.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao");
        }
    }

    private void updateEdicaoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Edicao edicao = new Edicao();
        edicao.setId(Long.parseLong(request.getParameter("id")));
        edicao.setEd(request.getParameter("ed"));
        edicao.setPreco(Float.parseFloat(request.getParameter("preco")));        
        edicao.setLocalizacao(Long.parseLong(request.getParameter("localizacao")));
        edicao.setStatus_inscricoes(Long.parseLong(request.getParameter("status_inscricoes")));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            dao.update(edicao);
            HttpSession session = request.getSession(false);
            
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao/update");
        }
    }
    
    private void listEventoPesquisa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();//edição
            DAO dao2 = daoFactory.getEventoDAO();//evento
            DAO dao3 = daoFactory.getEntPromotoraDAO();//entidade
            List<Edicao> edicaoList = dao.all();//edição
            List<Evento> eventoList = dao2.all();//evento
            List<EntPromotora> entList = dao3.all();//entidade
            List<Edicao> ed = new ArrayList();//edição
            List<Evento> ev = new ArrayList();//evento
            List<Edicao> ed2 = new ArrayList();//edição
            
            
            if(Integer.parseInt(request.getParameter("c1")) == 1){
                
                for(int i = 0; i < edicaoList.size(); i++){
                    
                    if(edicaoList.get(i).getTituloev().equals(request.getParameter("nome"))){
                        
                        ed.add(edicaoList.get(i));
                        System.out.println(ed.get(0).getTituloev());
                    }
                }
                request.setAttribute("ed", ed);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/listTitle.jsp");
                dispatcher.forward(request, response);
            }
            
            else if(Integer.parseInt(request.getParameter("c1")) == 2){
                
                for(int i = 0; i < entList.size(); i++){
                    
                    if(entList.get(i).getNome().equals(request.getParameter("entidade"))){
                        
                        for(int j = 0; j < eventoList.size(); j++){
                            
                            if(eventoList.get(j).getEntidade_promotora() == (entList.get(i).getId())){
                                
                                ev.add(eventoList.get(j));
                            }                            
                        }
                    }
                }
                
                for(int i = 0; i < ev.size(); i++){
                    
                    for(int j = 0; j < edicaoList.size(); j++){
                        
                      if(ev.get(i).getTitulo().equals(edicaoList.get(j).getTituloev())){
                          
                          ed2.add(edicaoList.get(j));
                      }  
                    }
                }
                request.setAttribute("ed2", ed2);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/edicao/listEnt.jsp");
                dispatcher.forward(request, response);
            }
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }
    }
    
    private void listEdicoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEdicaoDAO();

            List<Edicao> edicaoList = dao.all();//edição
            request.setAttribute("edi", edicaoList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/multiplas.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao");
        }
    }
    
    private void multEstatisticas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            
            InscricoesDAO dao = daoFactory.getInscricoesDAO();  
            List<Inscricoes> inscricoesList;
            List<Aux> auxList = new ArrayList();
            int resp = 0;// resposta para o jsp

            int quantidade_eventos = Integer.parseInt(request.getParameter("quantidade_eventos"));
                
            for(int i=1; i<=quantidade_eventos;i++){
                
                if(request.getParameter("escolha_"+i) != null){
                
                    inscricoesList = dao.allInscricoes(Long.parseLong(request.getParameter("escolha_"+i)));
                    if(request.getParameter("b1") != null){
                        
                        Aux aux2 = new Aux();
                        
                        for(int j = 0; j < inscricoesList.size(); j++){
                            
                            if(inscricoesList.get(j).getSexo().equals(request.getParameter("sexo"))){
                                
                                resp++;
                                aux2.setEdicao(inscricoesList.get(j).getEd());
                                aux2.setTitulo(inscricoesList.get(j).getTituloev());
                            }
                        }
                        aux2.setQuantidade(resp);
                        auxList.add(aux2);
                        resp = 0;
                    }else if(request.getParameter("b0") != null){
                        
                        Aux aux2 = new Aux();
                        
                        for(int j = 0; j < inscricoesList.size(); j++){

                            resp++;
                            aux2.setEdicao(inscricoesList.get(j).getEd());
                            aux2.setTitulo(inscricoesList.get(j).getTituloev());
                            
                        }
                        aux2.setQuantidade(resp);
                        auxList.add(aux2);
                        System.out.println(auxList.get(0).getTitulo());
                        resp = 0;
                    }else if(request.getParameter("b2") != null){
                        
                        Aux aux2 = new Aux();
                        int minimo = Integer.parseInt(request.getParameter("id_min"));
                        int maximo = Integer.parseInt(request.getParameter("id_max"));
                        
                        for(int j = 0; j < inscricoesList.size(); j++){
                            
                            aux2.setEdicao(inscricoesList.get(j).getEd());
                            aux2.setTitulo(inscricoesList.get(j).getTituloev());
                            if((inscricoesList.get(j).getIdade() >= minimo) && (inscricoesList.get(j).getIdade() <= maximo)){
                                
                                resp++;
                            }
                        }
                        aux2.setQuantidade(resp);
                        auxList.add(aux2);
                        resp = 0;
                    }else if(request.getParameter("b3") != null){
                        
                        Aux aux2 = new Aux();
                        System.out.println(request.getParameter("id_min2"));
                        int minimo = Integer.parseInt(request.getParameter("id_min2"));
                        int maximo = Integer.parseInt(request.getParameter("id_max2"));
                        String sexo = request.getParameter("sexo2");
                        
                        for(int j = 0; j < inscricoesList.size(); j++){
                            
                            aux2.setEdicao(inscricoesList.get(j).getEd());
                            aux2.setTitulo(inscricoesList.get(j).getTituloev());
                            if((inscricoesList.get(j).getIdade() >= minimo) && (inscricoesList.get(j).getIdade() <= maximo) && (inscricoesList.get(j).getSexo().equals(sexo))){
                                
                                resp++;
                            }
                        }
                        aux2.setQuantidade(resp);
                        auxList.add(aux2);
                        resp = 0;
                        
                    }else if(request.getParameter("b4") != null){
                        
                        Aux aux2 = new Aux();
                        String insti = request.getParameter("instituicao");
                        
                        for(int j = 0; j < inscricoesList.size(); j++){
                            
                            aux2.setEdicao(inscricoesList.get(j).getEd());
                            aux2.setTitulo(inscricoesList.get(j).getTituloev());
                            if(inscricoesList.get(j).getInstituicao_origem().equals(insti)){
                             
                                resp++;
                            }
                        }
                        aux2.setQuantidade(resp);
                        auxList.add(aux2);
                        resp = 0;
                    }
                }    
            }
            
            request.setAttribute("aux", auxList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/estatisticas/index2.jsp");
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/edicao");
        }
    }
}