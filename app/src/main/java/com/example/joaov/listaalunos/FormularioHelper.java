package com.example.joaov.listaalunos;

import android.widget.EditText;

import com.example.joaov.listaalunos.modelo.Aluno;

/**
 * Created by joaov on 30-Aug-17.
 */

public class FormularioHelper {
    private EditText campoNome;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity formularioActivity) {
        campoNome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
    }

    public Aluno pegaAluno() {
        aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        this.aluno = aluno;
    }
}

