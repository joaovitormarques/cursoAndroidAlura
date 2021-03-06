package com.example.joaov.listaalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.joaov.listaalunos.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 30-Aug-17.
 */

public class AlunoDAO extends SQLiteOpenHelper{
    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Alunos(id integer primary key," +
                " nome text not null," +
                " endereco text, " +
                "telefone text," +
                " site text," +
                " nota real," +
                " caminhoFoto text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:
            sql = "alter table Alunos add column caminhoFoto text";
            db.execSQL(sql);
        }
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = dadosAluno(aluno);
        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues dadosAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        String sql = "select * from Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<>();
        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public void delete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues dados = dadosAluno(aluno);
        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id = ?", params);
    }

    public boolean ehAluno(String telefone){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from Alunos where telefone = ?", new String[]{telefone});
        int resultados = c.getCount();
        c.close();
        return resultados > 0;
    }
}
