package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.relme33.fiai.uci.relme33.Adapters.AdaptadorEventoBD;
import com.relme33.fiai.uci.relme33.database.DatabaseHelper;


public class ProgramaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView lista;
    Spinner dias;
    Spinner modalidades;
    String dia;
    String modalidad;
    //d-1,l-2,m-3,mx-4,j-5,v6
    DatabaseHelper basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        basedatos = new DatabaseHelper(this);
        dia = "sunday";
        modalidad = "todas";
        AdaptadorEventoBD adB = new AdaptadorEventoBD(this,basedatos.getEventosFrom(dia),true);
        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adB);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dias_del_relme,
                R.layout.estilo_seleccion_dias);
        dias = (Spinner)findViewById(R.id.spiner_dias);
        dias.setAdapter(adapter);
        dias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().compareTo("Domingo 7")==0){
                    dia = "sunday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }else if(parent.getItemAtPosition(position).toString().compareTo("Lunes 8")==0){
                    dia = "monday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }else if(parent.getItemAtPosition(position).toString().compareTo("Martes 9")==0){
                    dia = "thursday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }else if(parent.getItemAtPosition(position).toString().compareTo("Miércoles 10")==0){
                    dia = "wednesday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }else if(parent.getItemAtPosition(position).toString().compareTo("Jueves 11")==0){
                    dia = "tuesday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }else if(parent.getItemAtPosition(position).toString().compareTo("Viernes 12")==0){
                    dia = "friday";
                    if(modalidad.compareToIgnoreCase("todas")==0) {
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(), basedatos.getEventosFrom(dia), true));
                    }else{
                        lista = (ListView) findViewById(R.id.lista);
                        lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                                basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.modalidades_del_relme,
                R.layout.estilo_seleccion_dias);
        modalidades = (Spinner)findViewById(R.id.spiner_modalidad);
        modalidades.setAdapter(adapter2);
        modalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().compareTo("Todas las Modalidades")==0){
                    modalidad = "todas";
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom(dia),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Conferencia Especial")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Reportes de Investigación")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Comunicaciones Breves")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Talleres")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Grupos de Discusión")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Mesas Redondas")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Premios Clame")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Concursos")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                }
                else if(parent.getItemAtPosition(position).toString().compareTo("Presentaciones")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Cursos Cortos")==0){
                    modalidad = parent.getItemAtPosition(position).toString();
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),
                            basedatos.getEventosOfDayAndModality(dia,modalidad),true));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*view.findElementById(R.id.punto_mapa)*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intenthome);
        finish();
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intenthome);
                finish();
                break;
            case R.id.RELME_33:
                Intent intent1 = new Intent(getApplicationContext(),RelmeInfoActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.Conferencistas:
                Intent intent2 = new Intent(getApplicationContext(),ConferencistasActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.Modalidades:
                Intent intent3 = new Intent(getApplicationContext(),ModalidadesActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.Programa:
                break;
            case R.id.Mapa:
                showDialogWait();
                Intent intent7 = new Intent(getApplicationContext(),MapaActivity.class);
                startActivity(intent7);
                finish();
                break;
            case R.id.Contactos:
                Intent intent5 = new Intent(getApplicationContext(),ContactosActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.Acerca_de:
                Intent intent6 = new Intent(getApplicationContext(),AcercaDeActivity.class);
                startActivity(intent6);
                finish();
                break;
        }
        return true;
    }
    private void showDialogWait(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.layout_promptdialog__map_charging, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.setCancelable(false);
        builder.create();

        builder.show();

    }
    public void onClick(View view) {
    }
}
