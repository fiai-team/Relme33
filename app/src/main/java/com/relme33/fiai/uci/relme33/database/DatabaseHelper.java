package com.relme33.fiai.uci.relme33.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;


    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public synchronized void close() {
        if (database != null && database.isOpen()) {
            database.close();
            database = null;
        }

        super.close();
    }

    public void openForWriting() {
        database = getWritableDatabase();
    }

    public void openForReading() {
        database = getReadableDatabase();
    }

    public ArrayList<Evento> getEventosFrom(String dia){
        ArrayList<Evento> lista_eventos = new ArrayList<>();
        openForReading();
        try{
            Cursor cursor = database.rawQuery("Select * from "+ dia,null);
            if(cursor.getCount()>0) {
                while (cursor.moveToNext()) {
                    Evento evento;
                    evento = new Evento();

                    evento.setId_evento(cursor.getInt(0));
                    evento.setDescripcion(cursor.getString(1));
                    evento.setPerson(cursor.getString(2));
                    evento.setModalidad(cursor.getString(3));
                    evento.setTiempo(cursor.getString(4));
                    evento.setUbicacion(cursor.getString(5));

                    lista_eventos.add(evento);
                }
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return lista_eventos;
    }
    public ArrayList<Evento> getEventosOfModality(String modalidad){
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();
        ArrayList<Evento> lista_eventos_buscados = new ArrayList<>();
        openForReading();

        lista_eventos_buscados.addAll(getEventosFrom("sunday"));
        lista_eventos_buscados.addAll(getEventosFrom("monday"));
        lista_eventos_buscados.addAll(getEventosFrom("thursday"));
        lista_eventos_buscados.addAll(getEventosFrom("wednesday"));
        lista_eventos_buscados.addAll(getEventosFrom("tuesday"));
        lista_eventos_buscados.addAll(getEventosFrom("friday"));
        //lista_eventos_buscados.addAll(getEventosFrom("sabado"));


        for(int i = 0; i<lista_eventos_buscados.size();i++){
            if((lista_eventos_buscados.get(i).getModalidad()!=null)&&
                    (lista_eventos_buscados.get(i).getModalidad().compareToIgnoreCase(modalidad)==0)){
                lista_eventos_filtrados.add(lista_eventos_buscados.get(i));
            }
        }
        return lista_eventos_filtrados;
    }
    public ArrayList<Evento> getEventosOfDayAndModality(String dia,String modalidad){
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();
        ArrayList<Evento> lista_eventos_buscados = new ArrayList<>();
        openForReading();

        lista_eventos_buscados.addAll(getEventosFrom(dia));

        for(int i = 0; i<lista_eventos_buscados.size();i++){
            if((lista_eventos_buscados.get(i).getModalidad()!=null)&&
                    (lista_eventos_buscados.get(i).getModalidad().compareToIgnoreCase(modalidad)==0)){
                lista_eventos_filtrados.add(lista_eventos_buscados.get(i));
            }
        }
        return lista_eventos_filtrados;
    }

    public ArrayList<Evento> getEventosOfChair(String person){
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();
        ArrayList<Evento> lista_eventos_buscados = new ArrayList<>();
        openForReading();

        lista_eventos_buscados.addAll(getEventosFrom("sunday"));
        lista_eventos_buscados.addAll(getEventosFrom("monday"));
        lista_eventos_buscados.addAll(getEventosFrom("thursday"));
        lista_eventos_buscados.addAll(getEventosFrom("wednesday"));
        lista_eventos_buscados.addAll(getEventosFrom("tuesday"));
        lista_eventos_buscados.addAll(getEventosFrom("friday"));
        //lista_eventos_buscados.addAll(getEventosFrom("sabado"));

        for(int i = 0; i<lista_eventos_buscados.size();i++){
            String arr[] = lista_eventos_buscados.get(i).getPerson().split(" ");
            for(int j = 0;i<arr.length;i++){
                if(arr[i].compareToIgnoreCase(person)==0){
                    lista_eventos_filtrados.add(lista_eventos_buscados.get(i));
                }
            }

        }
        return lista_eventos_filtrados;
    }


}
