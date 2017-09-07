package com.example.joaov.listaalunos;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.joaov.listaalunos.modelo.Aluno;

/**
 * Created by joaov on 30-Aug-17.
 */

public class FormularioHelper {
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoRating;
    private EditText campoNome;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity formularioActivity) {
        campoNome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        campoRating = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
    }

    public Aluno pegaAluno() {
        aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(campoRating.getRating());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoRating.setRating((float) aluno.getNota());
        this.aluno = aluno;
    }
}

