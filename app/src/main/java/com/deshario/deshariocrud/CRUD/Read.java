package com.deshario.deshariocrud.CRUD;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.deshario.deshariocrud.Database.DBHandler;
import com.deshario.deshariocrud.Models.User;
import com.deshario.deshariocrud.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Deshario on 9/5/2017.
 */

public class Read extends AppCompatActivity{

    ListView listview;
    TextView no_data;
    int sel_position = -1;
    DBHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        listview = (ListView)findViewById(R.id.listview);
        no_data = (TextView)findViewById(R.id.no_data_txt);
        db = new DBHandler(this);
        work();
    }

    public void work(){
        List<User> users = db.getAllUsers();
        if(users.size() <= 0){
            no_data.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        }else{
            no_data.setVisibility(View.GONE);
        }
        List<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();
        for (int i=0; i<users.size(); i++) {
            HashMap<String, String> hashmap = new HashMap<String, String>();
            hashmap.put("list_image", Integer.toString(R.mipmap.ic_launcher));
            hashmap.put("list_name", users.get(i).getNickname());
            hashmap.put("list_email", users.get(i).getEmail());
            datas.add(hashmap);
        }
        String[] from = {"list_image", "list_name", "list_email"};
        int[] to = {R.id.list_image, R.id.list_name, R.id.list_email};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), datas, R.layout.custom_listview, from, to);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                String list_name = item.get("list_name");
                final User user = db.getSingleUser(list_name);

                AlertDialog.Builder builder = new AlertDialog.Builder(Read.this);
                CharSequence[] array = {"Update", "Delete"};
                builder.setTitle("Select Choice");
                builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        sel_position = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        select(sel_position,user);
                        sel_position = -1;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sel_position = -1; // Reset
                        dialog.dismiss();
                    }
                });

                AlertDialog al = builder.create();
                al.show();

            }
        });
    }

    public void select(int position, User user){
        switch (position){
            case -1:
                Toast.makeText(Read.this,"Invalid Choice !",Toast.LENGTH_SHORT).show();
                break;
            case 0:
                update(user);
                break;
            case 1:
                delete(user);
                break;
            default:
        }
    }

    private void update(User user){
        Intent intent = new Intent(Read.this,Update.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void delete(User user){
        db = new DBHandler(this);
        db.deleteUser(user);
        Toast.makeText(Read.this,"Delete Success!",Toast.LENGTH_SHORT).show();
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        work();
    }
}
