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
public class Inscricoes {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_inscricao() {
        return data_inscricao;
    }

    public void setData_inscricao(String data_inscricao) {
        this.data_inscricao = data_inscricao;
    }

    public Long getPagamento() {
        return pagamento;
    }

    public void setPagamento(Long pagamento) {
        this.pagamento = pagamento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Long getId_inscricao() {
        return id_inscricao;
    }

    public void setId_inscricao(Long id_inscricao) {
        this.id_inscricao = id_inscricao;
    }
    
    public String getTituloev() {
        return tituloev;
    }

    public void setTituloev(String tituloev) {
        this.tituloev = tituloev;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }
    
    public Long getId_edicao() {
        return id_edicao;
    }

    public void setId_edicao(Long id_edicao) {
        this.id_edicao = id_edicao;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public String getInstituicao_origem() {
        return instituicao_origem;
    }

    public void setInstituicao_origem(String instituicao_origem) {
        this.instituicao_origem = instituicao_origem;
    }
    
    private String nome;
    private String data_inscricao;
    private Long pagamento;
    private float preco;
    private Long id_inscricao;
    private String tituloev;
    private String ed;
    private Long id_edicao;
    private String sexo;
    private int idade;
    private String instituicao_origem;
    
}
