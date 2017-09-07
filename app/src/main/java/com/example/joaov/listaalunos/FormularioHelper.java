package com.example.joaov.listaalunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView campoFoto;

    public FormularioHelper(FormularioActivity formularioActivity) {
        campoNome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        campoRating = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) formularioActivity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(campoRating.getRating());
        aluno.setCaminhoFoto((String) campoFoto.getTag());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoRating.setRating((float) aluno.getNota());
        carregaFoto(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaFoto(String caminhoFoto) {
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}

