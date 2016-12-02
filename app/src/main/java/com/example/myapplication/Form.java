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
import android.widget.Toast;

public class Form extends AppCompatActivity {
    EditText name,address,email;
    RadioButton gender,male,female;
    RadioGroup genderGroup;
    DataBase db = new DataBase(this);
    Button addBtn, editBtn, viewBtn;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        find();
        type = getIntent().getStringExtra("type");
        if(type.equalsIgnoreCase("update")){
            int userid = Integer.parseInt(getIntent().getStringExtra("USER_ID"));
            User user = db.get_user(userid);

            name.setText(user.getName());
            email.setText(user.getEmail());
            address.setText(user.getAddress());
            if (user.getGender().equalsIgnoreCase("female")){
                female.setChecked(true);
                male.setChecked(false);
            }

            editBtn.setVisibility(View.VISIBLE);
            addBtn.setVisibility(View.GONE);
        }else{
            addBtn.setVisibility(View.VISIBLE);
            editBtn.setVisibility(View.GONE);
        }
    }

    public void Add(View view){
        try {
            if(type.equalsIgnoreCase("update")) {
                find();
                update();

                int userid = Integer.parseInt(getIntent().getStringExtra("USER_ID"));
                Toast.makeText(Form.this, "User " +userid+" updated..", Toast.LENGTH_LONG).show();

            }else {
                add();
                Toast.makeText(Form.this, "New user added..", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            System.out.println("Error while add data..");
            System.out.println(e);
        }
        Intent intend = new Intent(Form.this,MainActivity.class);
        startActivity(intend);
    }

    private void update() {
        int userid = Integer.parseInt(getIntent().getStringExtra("USER_ID"));

        genderGroup = (RadioGroup) findViewById(R.id.gender);
        int id = genderGroup.getCheckedRadioButtonId();
        gender = (RadioButton) findViewById(id);
        User user = new User(userid,name.getText().toString(),email.getText().toString(),address.getText().toString(),gender.getText().toString());
        db.update_user(user);
    }

    public void Main(View view) {
        Intent intend = new Intent(Form.this,MainActivity.class);
        startActivity(intend);
    }

    public void find() {
        name = (EditText) findViewById(R.id.reg_fullname);
        address = (EditText) findViewById(R.id.reg_address);
        email = (EditText) findViewById(R.id.reg_email);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        addBtn = (Button) findViewById(R.id.btnRegister);
        editBtn = (Button) findViewById(R.id.btnUpdate);
        viewBtn = (Button) findViewById(R.id.viewAll);
    }

    public void add(){
        genderGroup = (RadioGroup) findViewById(R.id.gender);
        int id = genderGroup.getCheckedRadioButtonId();
        gender = (RadioButton) findViewById(id);
        User user = new User(name.getText().toString(),email.getText().toString(),address.getText().toString(),gender.getText().toString());
        db.add_user(user);
    }
}
