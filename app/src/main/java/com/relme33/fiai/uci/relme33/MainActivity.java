package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
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

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
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

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.link_uci_cu:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.sitio_uci))));
                break;
            case R.id.relme_cartel_principal:
                Intent intent3 = new Intent(getApplicationContext(),ModalidadesActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
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

}
