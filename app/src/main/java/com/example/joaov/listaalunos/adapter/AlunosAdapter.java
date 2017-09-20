package com.example.joaov.listaalunos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joaov.listaalunos.R;
import com.example.joaov.listaalunos.modelo.Aluno;

import java.util.List;

/**
 * Created by joaovitor on 15/09/17.
 */

public class AlunosAdapter extends BaseAdapter{
    private Context context;
    private List<Aluno> alunos;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        view = layoutInflater.inflate(R.layout.list_item, parent, false);
        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());

        //modo paisagem
        TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        if(campoEndereco != null) {
            campoEndereco.setText(aluno.getEndereco());
        }
        if(campoSite != null) {
            campoSite.setText(aluno.getSite());
        }

        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view;
    }
}
