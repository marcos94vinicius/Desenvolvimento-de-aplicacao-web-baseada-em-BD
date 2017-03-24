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
public class Inscricao {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getVai_com_outra_pessoa() {
        return vai_com_outra_pessoa;
    }

    public void setVai_com_outra_pessoa(Long vai_com_outra_pessoa) {
        this.vai_com_outra_pessoa = vai_com_outra_pessoa;
    }

    public String getFicou_sabendo() {
        return ficou_sabendo;
    }

    public void setFicou_sabendo(String ficou_sabendo) {
        this.ficou_sabendo = ficou_sabendo;
    }

    public String getFormas_pagamento() {
        return formas_pagamento;
    }

    public void setFormas_pagamento(String formas_pagamento) {
        this.formas_pagamento = formas_pagamento;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_edicao() {
        return id_edicao;
    }

    public void setId_edicao(Long id_edicao) {
        this.id_edicao = id_edicao;
    }
    
    public Long getPagamento() {
        return pagamento;
    }

    public void setPagamento(Long pagamento) {
        this.pagamento = pagamento;
    }
    
    public String getData_inscricao() {
        return data_inscricao;
    }

    public void setData_inscricao(String data_inscricao) {
        this.data_inscricao = data_inscricao;
    }
    
    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    private Long id;
    private Long quantidade;
    private Long status;
    private Long vai_com_outra_pessoa;
    private String ficou_sabendo;
    private String formas_pagamento;
    private Long id_usuario;
    private Long id_edicao;
    private Long pagamento;
    private String data_inscricao;
    private float preco;
}
