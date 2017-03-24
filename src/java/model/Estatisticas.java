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
public class Estatisticas {
    
    private Long edicao_id;
    private String sexo;
    private int idade_minima;
    private int idade_maxima;
    private String instituicao_origem;

    public Long getEdicao_id() {
        return edicao_id;
    }

    public void setEdicao_id(Long edicao_id) {
        this.edicao_id = edicao_id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade_minima() {
        return idade_minima;
    }

    public void setIdade_minima(int idade_minima) {
        this.idade_minima = idade_minima;
    }

    public int getIdade_maxima() {
        return idade_maxima;
    }

    public void setIdade_maxima(int idade_maxima) {
        this.idade_maxima = idade_maxima;
    }

    public String getInstituicao_origem() {
        return instituicao_origem;
    }

    public void setInstituicao_origem(String instituicao_origem) {
        this.instituicao_origem = instituicao_origem;
    }
}
