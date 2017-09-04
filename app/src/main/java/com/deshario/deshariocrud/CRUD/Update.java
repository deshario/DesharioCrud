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

public class Update extends AppCompatActivity {

    EditText et_nickname,et_email;
    String u_nickname,u_email;
    Button btn_update;
    TextView main_title;
    DBHandler db;
    int mainid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);

        db = new DBHandler(this);

        View view = (View)findViewById(R.id.user_form);
        main_title = (TextView)findViewById(R.id.title);
        et_nickname = (EditText)view.findViewById(R.id.nickname);
        et_email = (EditText)view.findViewById(R.id.email);
        btn_update = (Button)findViewById(R.id.save);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User)bundle.getSerializable("user");
        mainid = user.getId();
        fetchdata(user);

        main_title.setText("Update Records :: "+user.getNickname());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                work();
            }
        });
    }

    private void fetchdata(User user){
        et_nickname.setText(user.getNickname());
        et_email.setText(user.getEmail());
    }

    private void work(){
        u_nickname = et_nickname.getText().toString();
        u_email = et_email.getText().toString();
        if(u_nickname.isEmpty() || u_email.isEmpty()){
            System.out.println("Empty Field");
        }else{
            db.updateUser(mainid,u_nickname,u_email);
            Toast.makeText(Update.this,"Update Success!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
