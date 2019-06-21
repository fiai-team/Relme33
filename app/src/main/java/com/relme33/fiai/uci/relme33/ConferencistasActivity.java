package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.relme33.fiai.uci.relme33.fragments.ConferenciasEspecialesFragment;

public class ConferencistasActivity extends AppCompatActivity {


    Toolbar toolbar;
    String nombre_selec;
    int foto_selec;
    String bio_selec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferencistas);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                break;
            case R.id.Modalidades:
                Intent intent3 = new Intent(getApplicationContext(),ModalidadesActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.Programa:
                Intent intent4 = new Intent(getApplicationContext(),ProgramaActivity.class);
                startActivity(intent4);
                finish();
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

    private void showDialogBiografia(String name, int foto, String bio){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.alert_prompt_img_biografia, null);
        final TextView nombre = subView.findViewById(R.id.nombre_alert_persona);
        final ImageView imgFoto = subView.findViewById(R.id.foto_alert_persona);
        final TextView biografia = subView.findViewById(R.id.biografia_alert_persona);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        nombre.setText(name);
        imgFoto.setImageResource(foto);
        biografia.setText(bio);

        builder.setView(subView);
        builder.setCancelable(false);
        builder.create();
        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_p1:
                nombre_selec = getString(R.string.name_persona_1);
                foto_selec = R.mipmap.p1;
                bio_selec = getString(R.string.texto_persona_1);
                showDialogBiografia(nombre_selec,foto_selec,bio_selec);
                break;
            case R.id.b_p2:
                nombre_selec = getString(R.string.name_persona_2);
                foto_selec = R.mipmap.p2;
                bio_selec = getString(R.string.texto_persona_2);
                showDialogBiografia(nombre_selec,foto_selec,bio_selec);
                break;
            case R.id.b_p3:
                nombre_selec = getString(R.string.name_persona_3);
                foto_selec = R.mipmap.p3;
                bio_selec = getString(R.string.texto_persona_3);
                showDialogBiografia(nombre_selec,foto_selec,bio_selec);
                break;
            case R.id.b_p4:
                nombre_selec = getString(R.string.name_persona_4);
                foto_selec = R.mipmap.p4;
                bio_selec = getString(R.string.texto_persona_4);
                showDialogBiografia(nombre_selec,foto_selec,bio_selec);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intenthome);
        finish();
        super.onBackPressed();
    }
}
