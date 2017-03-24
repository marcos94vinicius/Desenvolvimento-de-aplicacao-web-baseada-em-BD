/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class Edicao {
    
    private Long id;
    private String ed;
    private float preco;
    private Long localizacao;
    private String tituloev;
    private Long status_inscricoes;
    private float renda;
    private int total_part;
    private List<Periodo> periodoList;

    public Edicao() {
        
        periodoList = new ArrayList<Periodo>();
    }

    public List<Periodo> getPeriodoList() {
        return periodoList;
    }

    public void setPeriodoList(List<Periodo> periodoList) {
        this.periodoList = periodoList;
    }

    public int getTotal_part() {
        return total_part;
    }

    public void setTotal_part(int total_part) {
        this.total_part = total_part;
    }

    public float getRenda() {
        return renda;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Long getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Long localizacao) {
        this.localizacao = localizacao;
    }

    public String getTituloev() {
        return tituloev;
    }

    public void setTituloev(String tituloev) {
        this.tituloev = tituloev;
    }

    public Long getStatus_inscricoes() {
        return status_inscricoes;
    }

    public void setStatus_inscricoes(Long status_inscricoes) {
        this.status_inscricoes = status_inscricoes;
    }
}
