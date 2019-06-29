package com.relme33.fiai.uci.relme33;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    @Override
    public void onBackPressed() {
        Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intenthome);
        finish();
        super.onBackPressed();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textLicencias:
            Intent irLicencias = new Intent(getApplicationContext(),CreditosActivity.class);
            startActivity(irLicencias);
            finish();
            break;

        }
    }
}
