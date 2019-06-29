package com.relme33.fiai.uci.relme33;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreditosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),AcercaDeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
