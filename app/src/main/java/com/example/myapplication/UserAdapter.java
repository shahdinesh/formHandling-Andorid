package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Dinesh on 11/29/2016.
 */
public class UserAdapter extends ArrayAdapter{
    Activity activity;
    int layoutResourceId;
    User user;
    ArrayList<User> data = new ArrayList<User>();

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
                Log.i("Edit Button Clicked", "**********");

                Intent update_user = new Intent(activity, Form.class);
                update_user.putExtra("called", "update");
                update_user.putExtra("USER_ID", v.getTag().toString());
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
                adb.setPositiveButton("Ok",
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // MyDataObject.remove(positionToRemove);
                                DataBase dBHandler = new DataBase(activity.getApplicationContext());
                                //dBHandler.Delete_Contact(user_id);
                                //MainActivity.this.onResume();
                            }


                        });
                adb.show();
            }

        });
        return row;

    }

    class UserHolder {
        TextView name;
        TextView email;
        TextView address;
        TextView gender;
        Button edit;
        Button delete;
    }
}

