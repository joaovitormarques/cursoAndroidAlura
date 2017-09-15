package com.example.joaov.listaalunos.converter;

import com.example.joaov.listaalunos.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by joaovitor on 15/09/17.
 */

public class AlunoConverter {
    public AlunoConverter() {
    }

    public String converteParaJSON(List<Aluno> alunos) {
        JSONStringer json = new JSONStringer();
        try {
            json.object().key("list").array().object().key("aluno").array();
            for(Aluno aluno : alunos){
                json.object();
                json.key("id").value(aluno.getId());
                json.key("nome").value(aluno.getNome());
                json.key("endereco").value(aluno.getEndereco());
                json.key("telefone").value(aluno.getTelefone());
                json.key("site").value(aluno.getSite());
                json.key("nota").value(aluno.getNota());
                json.endObject();
            }
            json.endArray().endObject().endArray().endObject();
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
