/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vinicius
 */
public class InscricaoEdicao {

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

    public String getTituloev() {
        return tituloev;
    }

    public void setTituloev(String tituloev) {
        this.tituloev = tituloev;
    }
    
    private Long id;
    private String ed;
    private String tituloev;
}
