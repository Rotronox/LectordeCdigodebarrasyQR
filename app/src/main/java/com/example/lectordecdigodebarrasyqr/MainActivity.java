package com.example.lectordecdigodebarrasyqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void escanearClick(View view) {
        try{
            Intent intent = new Intent(MainActivity.this, SimpleScannerActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"esto ha fallado "+e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }
}
