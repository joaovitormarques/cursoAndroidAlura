package com.example.joaov.listaalunos;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.example.joaov.listaalunos.dao.AlunoDAO;
import com.example.joaov.listaalunos.modelo.Aluno;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by joaovitor on 22/09/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mapa;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        LatLng posicaoDaEscola = pegaCoordenadaDoEndereco("Rua H8A 120, Campus do CTA, Sao Jose dos Campos");
        if(posicaoDaEscola != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 12);
            googleMap.moveCamera(update);
        }

        AlunoDAO dao = new AlunoDAO(getContext());
        for(Aluno aluno : dao.buscaAlunos()){
            LatLng coordenada = pegaCoordenadaDoEndereco(aluno.getEndereco());
            if (coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                googleMap.addMarker(marcador);
            }
        }
        dao.close();
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco){
        Geocoder geoCoder = new Geocoder(getContext());
        try {
            List<Address> resultados = geoCoder.getFromLocationName(endereco, 1);
            if(!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void centralizaEm(LatLng coordenada) {
        if (mapa != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 17);
            mapa.moveCamera(update);
        }
    }
}
