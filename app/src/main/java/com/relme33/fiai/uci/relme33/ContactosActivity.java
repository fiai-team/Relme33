package com.relme33.fiai.uci.relme33;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static android.Manifest.permission.CALL_PHONE;

public class ContactosActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

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

    @Override
    public void onBackPressed() {
        Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intenthome);
        finish();
        super.onBackPressed();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llamar:
                if((checkSelfPermission(CALL_PHONE)==PackageManager.PERMISSION_DENIED)){
                    requestPermissions(new String[]{CALL_PHONE},100);
                }else{
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+  getString(R.string.numero_contacto))));
                }
                break;
            case R.id.correo:
                Intent enviarCorreo = new Intent((Intent.ACTION_SEND));
                enviarCorreo.setData(Uri.parse("mailto:"));
                enviarCorreo.setType("message/rfc822");
                enviarCorreo.setType("text/html");
                enviarCorreo.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.coreo)});
                enviarCorreo.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(enviarCorreo, "Email "));
                break;
            case R.id.sitio_web:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://clame-relme.org")));
                break;
            case R.id.sitio_face:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/relmeoficial")));
                break;
            case R.id.sitio_twitt:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.twitter.com/universidad_uci")));
                break;
            case R.id.sitio_yout:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/user/informativouci?feature=BF")));
                break;
            case R.id.sitio_linked:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/school/universidad-de-las-ciencias-inform%E1ticas")));
                break;

        }
    }
}
