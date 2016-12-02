package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        getAllUser();

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
        intend.putExtra("type","add");
        startActivity(intend);
    }













    public class UserAdapter extends ArrayAdapter {
        Activity activity;
        int layoutResourceId;
        User user;
        ArrayList<User> data = new ArrayList<User>();
        EditText name = (EditText) findViewById(R.id.reg_fullname), address = (EditText) findViewById(R.id.reg_address),
                email = (EditText) findViewById(R.id.reg_email);

        public UserAdapter(Activity act, int layoutResourceId, ArrayList<User> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.name = (TextView) row.findViewById(R.id.nameText);
                holder.email = (TextView) row.findViewById(R.id.emailText);
                holder.address = (TextView) row.findViewById(R.id.addressText);
                holder.gender = (TextView) row.findViewById(R.id.genderText);
                holder.edit = (Button) row.findViewById(R.id.edit);
                holder.delete = (Button) row.findViewById(R.id.delete);
                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);
            holder.edit.setTag(user.getId());
            holder.delete.setTag(user.getId());
            holder.name.setText(user.getName());
            holder.email.setText(user.getEmail());
            holder.address.setText(user.getAddress());
            holder.gender.setText(user.getGender());

            holder.edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent update_user = new Intent(activity, Form.class);
                    update_user.putExtra("type", "update");
                    update_user.putExtra("USER_ID", v.getTag().toString());
                    System.out.println(v.getTag().toString());
                    activity.startActivity(update_user);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete ");
                    final int user_id = Integer.parseInt(v.getTag().toString());
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DataBase db = new DataBase(activity.getApplicationContext());
                            db.delete_user(user_id);
                            db.close();
                            MainActivity.this.onResume();
                        }
                    });
                    adb.show();
                }

            });
            return row;

        }

        class UserHolder{
            TextView name;
            TextView email;
            TextView address;
            TextView gender;
            Button edit;
            Button delete;

        }
    }



}
