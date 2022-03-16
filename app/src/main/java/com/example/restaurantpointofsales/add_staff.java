package com.example.restaurantpointofsales;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class add_staff extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText first_name, surname, username, password;
    Spinner role;
    Button add_staff, cancel_add_staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        //initialize variables

        first_name = findViewById(R.id.staffFirstName);
        surname = findViewById(R.id.staffSurname);
        username = findViewById(R.id.staffUsername);
        password = findViewById(R.id.staffPassword);

        role = findViewById(R.id.staffRoleSpinner);

        add_staff = findViewById(R.id.addStaffBtn);
        cancel_add_staff = findViewById(R.id.cancelAddStaffBtn);

        //array adapter to populate spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.staffRoles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
        role.setOnItemSelectedListener(this);

        //adds a staff member by calling the addStaff method

        add_staff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                addStaff();
            }
        });

        //Takes user back to the main menu

        cancel_add_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_staff.this, main.class);
                startActivity(i);
            }
        });
    }

    //add staff method tries to make a class from the fields if it fails one of the fields must be missing and an error message is displayed,
    // the database helper class is then called to perform SQL, the SQL statement adds a new staff member to staff member table.
    //Another try and catch is used to tell the user if the staff memeber was succussfully added.

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addStaff()  {

        staffMember newStaff = null;

        try {
            newStaff = new staffMember(1, role.getSelectedItem().toString(), first_name.getText().toString(), surname.getText().toString(), username.getText().toString(), toHex(hash(password.getText().toString())), 0);
        }
        catch (Exception ex) {
            Toast.makeText(this,"Some Fields Are Missing",Toast.LENGTH_LONG).show();
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(add_staff.this);

        boolean successfulAdd = dataBaseHelper.addStaff(newStaff);

        if(successfulAdd == true){
            Toast.makeText(add_staff.this,"Successfully Added " + first_name.getText() + " To The Database",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(add_staff.this,"Staff Failed To Be Added To Database",Toast.LENGTH_LONG).show();
        }

        first_name.setText("");
        surname.setText("");
        username.setText("");
        password.setText("");
        first_name.requestFocus();

    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] hash(String input) throws NoSuchAlgorithmException{

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));


    }

    public static String toHex(byte[] hash){

        BigInteger x = new BigInteger(1, hash);

        StringBuilder hex = new StringBuilder(x.toString(16));

        while (hex.length() < 32){
            hex.insert(0,'0');
        }
        return hex.toString();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}