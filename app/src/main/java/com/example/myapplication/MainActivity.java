package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<User> userList = new ArrayList<User>();
    ListView listUser;
    UserAdapter userAdapter;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            listUser = (ListView) findViewById(R.id.list);
            listUser.setItemsCanFocus(false);
            getAllUser();
        }catch (Exception e){
            System.out.println("Error while fetching data..");
            System.out.println(e);
        }

    }

    public void getAllUser(){
        userList.clear();
        db = new DataBase(this);
        ArrayList<User> userListDb = db.get_users();

        for (int i=0;i<userListDb.size();i++){
            int id = userListDb.get(i).getId();
            String name = userListDb.get(i).getName();
            String address = userListDb.get(i).getAddress();
            String email = userListDb.get(i).getEmail();
            String gender = userListDb.get(i).getGender();
            //System.out.println("user: "+id+"- name: "+name+", gender: "+gender+", address: "+address+", email: "+email);
            User user = new User();
            user.setId(id);
            user.setGender(gender);
            user.setAddress(address);
            user.setEmail(email);
            user.setName(i+1+". "+name);

            userList.add(user);
        }
        db.close();
        userAdapter = new UserAdapter(MainActivity.this, R.layout.user_list, userList);
        listUser.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }

    public void Form(View view) {
        Intent intend = new Intent(MainActivity.this,Form.class);
        startActivity(intend);
    }
}
