package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Form extends AppCompatActivity {
    EditText name,address,email;
    RadioButton gender;
    RadioGroup genderGroup;
    DataBase db = new DataBase(this);
    Button addBtn, editBtn, viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        find();

        String type = getIntent().getStringExtra("type");
        System.out.println(type);
        if(type.equalsIgnoreCase("update")){
            editBtn.setVisibility(View.VISIBLE);
            addBtn.setVisibility(View.GONE);
        }else{
            addBtn.setVisibility(View.VISIBLE);
            editBtn.setVisibility(View.GONE);
        }
    }

    public void Add(View view){
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

    public void Main(View view) {
        Intent intend = new Intent(Form.this,MainActivity.class);
        startActivity(intend);
    }

    public void find() {
        addBtn = (Button) findViewById(R.id.btnRegister);
        editBtn = (Button) findViewById(R.id.btnUpdate);
        viewBtn = (Button) findViewById(R.id.viewAll);
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
