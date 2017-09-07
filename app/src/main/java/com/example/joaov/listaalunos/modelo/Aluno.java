package com.example.joaov.listaalunos.modelo;

import java.io.Serializable;

/**
 * Created by joaov on 30-Aug-17.
 */
public class Aluno implements Serializable{
    private Long id;
    private String nome;
    private String endereco;
    private String site;
    private String telefone;
    private double nota;
    private  String caminhoFoto;

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return getId()+" - "+getNome();
    }

    public boolean isNameValid(){
        return nome != null && !nome.isEmpty();
    }

    public boolean isPhoneValid(){
        return telefone != null && !telefone.isEmpty();
    }

    public boolean isAddressValid(){
        return endereco != null && !endereco.isEmpty();
    }

    public boolean isSiteValid(){
        return site != null && !site.isEmpty();
    }

    public boolean isValid(){
        return isNameValid() && isPhoneValid() && isAddressValid() && isSiteValid();
    }
}
