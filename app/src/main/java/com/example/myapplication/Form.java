package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Form extends AppCompatActivity {
    EditText name,address,email;
    RadioButton gender;
    RadioGroup genderGroup;
    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

    }

    public void Main(View view){
        try {
            add();
            System.out.println("Data inserted");
            Log.d("Data inserted","abcdefgh");

        }catch (Exception e){
            System.out.println("Error while add data..");
            System.out.println(e);
        }
        Intent intend = new Intent(Form.this,MainActivity.class);
        startActivity(intend);
    }

    public void Form(View view) {
        setContentView(R.layout.activity_form);
    }

    public void add(){
        name = (EditText) findViewById(R.id.reg_fullname);
        address = (EditText) findViewById(R.id.reg_address);
        email = (EditText) findViewById(R.id.reg_email);
        genderGroup = (RadioGroup) findViewById(R.id.gender);
        int id = genderGroup.getCheckedRadioButtonId();
        gender = (RadioButton) findViewById(id);
        User user = new User(name.getText().toString(),email.getText().toString(),address.getText().toString(),gender.getText().toString());
        db.add_user(user);
    }
}
