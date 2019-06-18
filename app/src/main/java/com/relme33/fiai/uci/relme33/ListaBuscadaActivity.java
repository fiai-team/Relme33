package com.relme33.fiai.uci.relme33;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.relme33.fiai.uci.relme33.Adapters.AdaptadorEventoBD;
import com.relme33.fiai.uci.relme33.database.DatabaseHelper;
import com.relme33.fiai.uci.relme33.utiles.Utiles;

public class ListaBuscadaActivity extends AppCompatActivity {

    ListView lista;
    String ubicacion[];


    private Toolbar toolbar;

    Bundle bundle;
    ListView lista_buscada;
    DatabaseHelper basedatos;
    String busca = "cc";
    TextView texto_encabezado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_buscada);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        basedatos = new DatabaseHelper(this);
        texto_encabezado = (TextView) findViewById(R.id.text_encabezado_busqueda);
        lista_buscada = (ListView) findViewById(R.id.lista_buscada);
        Bundle mibundle = this.getIntent().getExtras();
        if(mibundle!=null) {
            busca = mibundle.getString(Utiles.KEY_PASS_MODALIDAD);
            texto_encabezado.setText(busca);
        }
        lista_buscada.setAdapter( new AdaptadorEventoBD(getApplicationContext(),
                basedatos.getEventosOfModality(busca),false));


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intenthome = new Intent(getApplicationContext(),ProgramaActivity.class);
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

    public void onBackPressed() {
        Intent intent3 = new Intent(getApplicationContext(),ModalidadesActivity.class);
        startActivity(intent3);
        finish();
        super.onBackPressed();
    }

    public void onClick(View view) {
    }
}
