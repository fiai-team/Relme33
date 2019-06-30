package com.relme33.fiai.uci.relme33;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.relme33.fiai.uci.relme33.fragments.ConferenciasEspecialesFragment;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sitio_uci:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.sitio_uci))));
                break;
            case R.id.relme_cartel_principal:
                Intent inten = new Intent(getApplicationContext(),ModalidadesActivity.class);
                startActivity(inten);
                finish();
                break;
            case R.id.menu_programa_eventos:
                Intent intent1 = new Intent(getApplicationContext(),ProgramaActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.menu_programa_general:
                Intent intent11 = new Intent(getApplicationContext(),ProgramaGeneralActivity.class);
                startActivity(intent11);
                finish();
                break;
            case R.id.menu_relme:
                Intent intent2 = new Intent(getApplicationContext(),RelmeInfoActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.menu_conferencistas:
                Intent intent3 = new Intent(getApplicationContext(),ConferencistasActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.menu_modalidades:
                Intent intent4 = new Intent(getApplicationContext(),ModalidadesActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.menu_mapa:
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    showDialogWait();
                    Intent intent5 = new Intent(getApplicationContext(), MapaActivity.class);
                    startActivity(intent5);
                    finish();
                }else{
                    Intent intentAlternativo = new Intent(getApplicationContext(), MapaAlternativoActivity.class);
                    startActivity(intentAlternativo);
                    finish();
                    Toast.makeText(getApplicationContext(),"En esta versión de ANDROID no se abrirá el mapa. Disculpe las molestias",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_contactos:
                Intent intent6 = new Intent(getApplicationContext(),ContactosActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.menu_acerca:
                Intent intent7 = new Intent(getApplicationContext(),AcercaDeActivity.class);
                startActivity(intent7);
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
