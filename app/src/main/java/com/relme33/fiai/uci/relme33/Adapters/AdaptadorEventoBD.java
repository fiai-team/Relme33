package com.relme33.fiai.uci.relme33.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        ImageView icono_ubicacion = (ImageView) vista.findViewById(R.id.punto_mapa_ico);

        ImageView icono_chairs = (ImageView) vista.findViewById(R.id.chair_ico);

        ImageView icono_authors = (ImageView) vista.findViewById(R.id.authors_ico);

        TextView view_horas = (TextView) vista.findViewById(R.id.text_hora);

        TextView view_Titulos = (TextView) vista.findViewById(R.id.text_titulo_evento);

        TextView view_ubicacion = (TextView) vista.findViewById(R.id.punto_mapa);

        TextView view_autor = (TextView) vista.findViewById(R.id.autor_text);

        TextView view_chair = (TextView) vista.findViewById(R.id.chair_text);

        view_Titulos.setText(eventos.get(position).getDescripcion());
        view_ubicacion.setText(eventos.get(position).getUbicacion());

        if(t){
            auxT = eventos.get(position).getTiempo();
        }else{
            auxT = eventos.get(position).getTiempo() + " " + eventos.get(position).getDia();
        }

        view_horas.setText(auxT);

        if((eventos.get(position).getAutor().compareToIgnoreCase(eventos.get(position).getPerson())==0) &&
                eventos.get(position).getAutor().compareToIgnoreCase("UCI")==0){
            icono_authors.setVisibility(vista.GONE);
            view_autor.setVisibility(vista.GONE);
            icono_chairs.setVisibility(vista.GONE);
            view_chair.setVisibility(vista.GONE);
            icono_ubicacion.setVisibility(vista.GONE);
            view_ubicacion.setVisibility(vista.GONE);
        }else if(eventos.get(position).getAutor().compareToIgnoreCase(eventos.get(position).getPerson())==0){
            icono_authors.setVisibility(vista.GONE);
            view_autor.setVisibility(vista.GONE);
            view_chair.setText("Chairs: " + eventos.get(position).getPerson());
        }else if(eventos.get(position).getAutor().compareToIgnoreCase("UCI")==0){
            icono_authors.setVisibility(vista.GONE);
            view_autor.setVisibility(vista.GONE);
            view_chair.setText("Chairs: " + eventos.get(position).getPerson());
        }else {
            view_autor.setText("Authors: " + eventos.get(position).getAutor());
            view_chair.setText("Chairs: " + eventos.get(position).getPerson());
        }
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
