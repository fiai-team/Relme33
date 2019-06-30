package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.relme33.fiai.uci.relme33.utiles.Utiles;

public class MapaAlternativoActivity extends AppCompatActivity {

    Toolbar toolbar;
    int docente_a_mostrar;
    ImageView imagen_mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_alternativo);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagen_mapa = (ImageView) findViewById(R.id.contenedorMapas);
        imagen_mapa.setImageResource(R.drawable.map_full);

        Bundle mibundle = this.getIntent().getExtras();
        if(mibundle!=null) {
            docente_a_mostrar = mibundle.getInt(Utiles.KEY_PASS_MAPA_SHOW);
        }
        switch (docente_a_mostrar){
            case 1:
                imagen_mapa.setImageResource(R.drawable.map_doc1);
                break;
            case 2:
                imagen_mapa.setImageResource(R.drawable.map_doc2);
                break;
            case 3:
                imagen_mapa.setImageResource(R.drawable.map_doc3);
                break;
            case 4:
                imagen_mapa.setImageResource(R.drawable.map_doc4);
                break;
            case 5:
                imagen_mapa.setImageResource(R.drawable.map_doc5);
                break;
            case 7:
                imagen_mapa.setImageResource(R.drawable.map_uh);
                break;
            case 8:
                imagen_mapa.setImageResource(R.drawable.map_karl);
                break;
            case 9:
                imagen_mapa.setImageResource(R.drawable.map_laguito);
                break;
            case 10:
                imagen_mapa.setImageResource(R.drawable.map_lacecilia);
                break;
            case 11:
                imagen_mapa.setImageResource(R.drawable.map_habana);
                break;
            default:
                imagen_mapa.setImageResource(R.drawable.map_full);
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
                Intent intent4 = new Intent(getApplicationContext(),ProgramaActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.programa_general:
                Intent intent44 = new Intent(getApplicationContext(),ProgramaGeneralActivity.class);
                startActivity(intent44);
                finish();
                break;
            case R.id.Mapa:
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
