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
        AdaptadorEventoBD adB = new AdaptadorEventoBD(this,basedatos.getEventosFrom("sunday"),true);
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
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("sunday"),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Lunes 8")==0){
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("monday"),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Martes 9")==0){
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("thursday"),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Miércoles 10")==0){
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("wednesday"),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Jueves 11")==0){
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("tuesday"),true));

                }else if(parent.getItemAtPosition(position).toString().compareTo("Viernes 12")==0){
                    lista = (ListView) findViewById(R.id.lista);
                    lista.setAdapter(new AdaptadorEventoBD(getApplicationContext(),basedatos.getEventosFrom("friday"),true));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent inte_explicaciones = new Intent(getApplicationContext(),ListaBuscadaActivity.class);
                    startActivity(inte_explicaciones);
            }
        });
        */
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
