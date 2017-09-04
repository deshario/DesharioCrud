package com.deshario.deshariocrud.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deshario.deshariocrud.Database.DBHandler;
import com.deshario.deshariocrud.Models.User;
import com.deshario.deshariocrud.R;

/**
 * Created by Deshario on 9/5/2017.
 */

public class Create extends AppCompatActivity {

    EditText et_nickname,et_email;
    String u_nickname,u_email;
    Button btn_save;
    TextView main_title;
    DBHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);

        db = new DBHandler(this);

        View view = (View)findViewById(R.id.user_form);
        main_title = (TextView)findViewById(R.id.title);
        et_nickname = (EditText)view.findViewById(R.id.nickname);
        et_email = (EditText)view.findViewById(R.id.email);
        btn_save = (Button)findViewById(R.id.save);

        main_title.setText("Create New Records");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                work();
            }
        });
    }

    private void work(){
        u_nickname = et_nickname.getText().toString();
        u_email = et_email.getText().toString();

        if(u_nickname.isEmpty() || u_email.isEmpty()){
            Toast.makeText(Create.this,"Empty Field !",Toast.LENGTH_SHORT).show();
        }else{
            User user = new User();
            user.setNickname(u_nickname);
            user.setEmail(u_email);
            db.addUser(user);
            Toast.makeText(Create.this,"Create Success!",Toast.LENGTH_SHORT).show();
            reset();
        }
    }

    private void reset(){
        et_nickname.setText("");
        et_email.setText("");
        et_nickname.clearFocus();
        et_email.clearFocus();
        finish();
        startActivity(new Intent(Create.this,Read.class));
    }
}
