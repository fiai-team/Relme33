package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    //Bundle bundle;
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

        lista_buscada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView ubicacion = (TextView) view.findViewById(R.id.punto_mapa);
                String aux = ubicacion.getText().toString().substring(0,2);;
                switch (aux){
                    case "D1":
                        showDialogWait();
                        Intent i1 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle1 = new Bundle();
                        mibundle1.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE1);
                        i1.putExtras(mibundle1);
                        startActivity(i1);
                        finish();
                        break;
                    case "D2":
                        showDialogWait();
                        Intent i2 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle2 = new Bundle();
                        mibundle2.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE2);
                        i2.putExtras(mibundle2);
                        startActivity(i2);
                        finish();
                        break;
                    case "D3":
                        showDialogWait();
                        Intent i3 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle3 = new Bundle();
                        mibundle3.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE3);
                        i3.putExtras(mibundle3);
                        startActivity(i3);
                        finish();
                        break;
                    case "D4":
                        showDialogWait();
                        Intent i4 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle4 = new Bundle();
                        mibundle4.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE4);
                        i4.putExtras(mibundle4);
                        startActivity(i4);
                        finish();
                        break;
                    case "D5":
                        showDialogWait();
                        Intent i5 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle5 = new Bundle();
                        mibundle5.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE5);
                        i5.putExtras(mibundle5);
                        startActivity(i5);
                        finish();
                        break;
                    case "D6":
                        showDialogWait();
                        Intent i6 = new Intent(ListaBuscadaActivity.this,MapaActivity.class);
                        Bundle mibundle6 = new Bundle();
                        mibundle6.putInt(Utiles.KEY_PASS_MAPA_SHOW, Utiles.KEY_MAP_DOCENTE6);
                        i6.putExtras(mibundle6);
                        startActivity(i6);
                        finish();
                        break;
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intenthome = new Intent(getApplicationContext(),ModalidadesActivity.class);
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

    public void onBackPressed() {
        Intent intent3 = new Intent(getApplicationContext(),ModalidadesActivity.class);
        startActivity(intent3);
        finish();
        super.onBackPressed();
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
