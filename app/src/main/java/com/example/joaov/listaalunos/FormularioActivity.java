package com.example.joaov.listaalunos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joaov.listaalunos.dao.AlunoDAO;
import com.example.joaov.listaalunos.modelo.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Cadastro");

        final Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null)+"/"+ System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CODIGO_CAMERA:
                if(resultCode == RESULT_OK) {
                    helper.carregaFoto(caminhoFoto);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText nameField = (EditText) findViewById(R.id.formulario_nome);
        EditText addressField = (EditText) findViewById(R.id.formulario_endereco);
        EditText phoneField = (EditText) findViewById(R.id.formulario_telefone);
        EditText siteField = (EditText) findViewById(R.id.formulario_site);
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);
                if (aluno.isValid()) {
                    if (aluno.getId() == null) {
                        dao.insere(aluno);
                        dao.close();
                        Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    }
                    dao.altera(aluno);
                    dao.close();
                    Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                } else {
                    if (!aluno.isNameValid()) {
                        nameField.setError(getString(R.string.invalid_field));
                    } else {
                        nameField.setError(null);
                    }
                    if (!aluno.isAddressValid()) {
                        addressField.setError(getString(R.string.invalid_field));
                    } else {
                        addressField.setError(null);
                    }
                    if (!aluno.isSiteValid()) {
                        siteField.setError(getString(R.string.invalid_field));
                    } else {
                        siteField.setError(null);
                    }
                    if (!aluno.isPhoneValid()) {
                        phoneField.setError(getString(R.string.invalid_field));
                    } else {
                        phoneField.setError(null);
                    }
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
