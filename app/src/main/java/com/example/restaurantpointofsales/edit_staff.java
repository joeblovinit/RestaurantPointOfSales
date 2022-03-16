package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class edit_staff extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText staff_Id, staff_name, staff_surname, staff_username, staff_time_worked;
    Button changePasswordButton, editStaffButton, deleteStaffButton, cancelEditStaffButton;
    Spinner editStaffRole;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);

        //initialize varibables

        staff_Id = findViewById(R.id.editStaffId);
        staff_name = findViewById(R.id.editStaffFirstName);
        staff_surname = findViewById(R.id.editStaffSurname);
        staff_username = findViewById(R.id.editStaffUsername);
        staff_time_worked = findViewById(R.id.editStaffTimeWorked);

        editStaffRole = findViewById(R.id.editStaffRoleSpinner);

        changePasswordButton = findViewById(R.id.changeStaffPassBtn);
        editStaffButton = findViewById(R.id.editStaffBtn);
        deleteStaffButton = findViewById(R.id.deleteStaffBtn);
        cancelEditStaffButton = findViewById(R.id.cancelEditStaffBtn);

        //passes staff member class into activity

        Intent i = getIntent();

        staffMember newStaff = (staffMember) i.getSerializableExtra("newStaff");

        //adapter to populate staff role spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.staffRoles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editStaffRole.setAdapter(adapter);

        if (newStaff.getStaffRole() != null) {
            int spinnerPos = adapter.getPosition(newStaff.getStaffRole());
            editStaffRole.setSelection(spinnerPos);
        }

        //sets the fields from the passed staffmember class

        staff_Id.setText(String.valueOf(newStaff.getStaffId()));
        editStaffRole.setPrompt(newStaff.getStaffRole());
        staff_name.setText(newStaff.getStaffName());
        staff_surname.setText(newStaff.getStaffSurname());
        staff_username.setText(newStaff.getStaffUsername());
        staff_time_worked.setText(String.valueOf(newStaff.getStaffTimeWorked()));

        //calls editstaff method
        editStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editStaff(newStaff);
            }
        });

        //calls delete method
        deleteStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(newStaff);
            }
        });

        //passes the newStaff class into activity and starts the activity
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(edit_staff.this, change_password.class);
                i.putExtra("newStaff",newStaff);

                startActivity(i);
            }
        });
        //takes user back to the staff list
        cancelEditStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(edit_staff.this, view_staff.class);
                startActivity(i);
            }
        });

    }


    //uses try and catch for validation then calls database helper to perform SQL insert query
    public void editStaff(staffMember newStaff){

        try{
            int test = Integer.parseInt(staff_time_worked.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(this,"Enter a valid Time Worked in Minutes for example 120",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            newStaff.setStaffRole(editStaffRole.getSelectedItem().toString());
            newStaff.setStaffName(staff_name.getText().toString());
            newStaff.setStaffSurname(staff_surname.getText().toString());
            newStaff.setStaffUsername(staff_username.getText().toString());
            newStaff.setStaffTimeWorked(Integer.parseInt(staff_time_worked.getText().toString()));

            dataBaseHelper.updateStaff(newStaff);
            Toast.makeText(this,"Staff Member Updated",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),view_staff.class);
            startActivity(i);
        }
        catch (Exception ex) {
            Toast.makeText(this,"Failed To Edit Staff Member",Toast.LENGTH_LONG).show();
        }
    }

    //calls database helper to perform SQL delete query
    public void delete(staffMember newStaff){
        try
        {
            dataBaseHelper.deleteStaff(newStaff);
            Toast.makeText(this,"Staff Member Deleted",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),view_staff.class);
            startActivity(i);
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Failed to Delete Staff Member",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}