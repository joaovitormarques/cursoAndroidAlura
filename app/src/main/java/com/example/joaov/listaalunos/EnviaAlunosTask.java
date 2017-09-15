package com.example.joaov.listaalunos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.joaov.listaalunos.converter.AlunoConverter;
import com.example.joaov.listaalunos.dao.AlunoDAO;
import com.example.joaov.listaalunos.modelo.Aluno;

import java.util.List;

/**
 * Created by joaovitor on 15/09/17.
 */

public class EnviaAlunosTask extends AsyncTask<Void, String, String>{

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando dados...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);
        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, "Recebido: "+resposta, Toast.LENGTH_SHORT).show();
    }
}
