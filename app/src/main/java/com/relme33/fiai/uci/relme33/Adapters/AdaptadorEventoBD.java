package com.relme33.fiai.uci.relme33.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.relme33.fiai.uci.relme33.R;
import com.relme33.fiai.uci.relme33.database.Evento;

import java.util.ArrayList;

public class AdaptadorEventoBD extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList<Evento> eventos;
    boolean t;

    public AdaptadorEventoBD(Context contexto,ArrayList<Evento> eventos,boolean t) {
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.contexto = contexto;
        this.eventos = eventos;
        this.t = t;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista_programa,null);
        String auxT = "";

        TextView view_horas = (TextView) vista.findViewById(R.id.text_hora);

        TextView view_Titulos = (TextView) vista.findViewById(R.id.text_titulo_evento);

        TextView view_ubicacion = (TextView) vista.findViewById(R.id.punto_mapa);

        TextView view_chair = (TextView) vista.findViewById(R.id.chair_text);

        view_Titulos.setText(eventos.get(position).getDescripcion());
        view_ubicacion.setText(eventos.get(position).getUbicacion());

        if(t){
            auxT = eventos.get(position).getTiempo().substring(0,11);
        }else{
            auxT = eventos.get(position).getTiempo();
        }

        view_horas.setText(auxT);
        view_chair.setText(eventos.get(position).getPerson());

        return vista;
    }


    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
