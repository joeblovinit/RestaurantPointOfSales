package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class view_staff extends AppCompatActivity {

    ListView staff_list;
    ArrayList<String> staffAr;
    ArrayAdapter arrayAdapter;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff);

        staff_list = findViewById(R.id.staffList);

        staffAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(view_staff.this);

        List<staffMember> staff_member_list = dataBaseHelper.populate_staff_list();

        final ArrayList<staffMember> staff_object_list = new ArrayList<>(staff_member_list);


        for(int i = 0; i < staff_member_list.size(); i++){
            staff_object_list.add(staff_member_list.get(i));
            staffAr.add(staff_member_list.get(i).getStaffId()
                    + " \t Role: " + staff_member_list.get(i).getStaffRole()
                    + " \t" + staff_member_list.get(i).getStaffName()
                    + " \t" + staff_member_list.get(i).getStaffSurname()
                    + " \t" + staff_member_list.get(i).getStaffPass()
                    + " \t Username: " + staff_member_list.get(i).getStaffUsername()
                    + " \t Time Worked: " + staff_member_list.get(i).getStaffTimeWorked());
        }


        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, staffAr);
        staff_list.setAdapter(arrayAdapter);



        staff_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                staffMember newStaff = staff_object_list.get(position);

                Intent i = new Intent(view_staff.this,edit_staff.class);
                i.putExtra("newStaff", newStaff);

                startActivity(i);
            }
        });


    }

    public void onBackPressed(){
        Intent i = new Intent(view_staff.this, main.class);
        startActivity(i);
    }
}