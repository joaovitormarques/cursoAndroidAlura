package com.example.joaov.listaalunos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.joaov.listaalunos.modelo.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joaovitor on 20/09/17.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);
        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto indireto", "Objeto direto");
        Prova provaPortugues = new Prova("Portugues", "20/09/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equacoes de segundo grau", "Trigonometria");
        Prova provaMat = new Prova("Matemática", "22/09/2017", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMat);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Clicou na prova de "+prova.toString(), Toast.LENGTH_SHORT).show();
                Intent vaiParaDetalhes = new Intent(getContext(), DetalhesProvaActivity.class);
                vaiParaDetalhes.putExtra("prova", prova);
                startActivity(vaiParaDetalhes);
            }
        });
        return view;
    }
}
