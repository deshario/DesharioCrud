package com.deshario.deshariocrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deshario.deshariocrud.CRUD.Create;
import com.deshario.deshariocrud.CRUD.Read;

public class MainActivity extends AppCompatActivity {

    Button create_btn,rud_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binder();
    }

    private void binder(){
        create_btn = (Button)findViewById(R.id.create);
        rud_btn = (Button)findViewById(R.id.rud);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Create.class));
            }
        });

        rud_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Read.class));
            }
        });


    }
}
