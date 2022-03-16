package com.example.restaurantpointofsales;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class firstLaunchCreateAdmin extends AppCompatActivity {

    EditText Role, first_name, surname, username, password;
    Button add_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_create_admin);

        //initialize variables

        Role = findViewById(R.id.first_launch_staff_role);
        first_name = findViewById(R.id.first_launch_firstName);
        surname = findViewById(R.id.first_launch_surname);
        username = findViewById(R.id.first_launch_username);
        password = findViewById(R.id.first_launch_Password);

        add_admin = findViewById(R.id.first_launch_create_admin_btn);

        //this activity is used on the first startup of the app, to create an admin account
        //activity only launches when the staff list is empty

        //calls addStaff method to add admin to table
        add_admin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                addStaff();
            }
        });









    }

    //add staffmethod calls databasehelper to perfrom SQL
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addStaff()  {

        staffMember newStaff = null;

        try {
            newStaff = new staffMember(1, Role.getText().toString(), first_name.getText().toString(), surname.getText().toString(), username.getText().toString(), toHex(hash(password.getText().toString())), 0);
        }
        catch (Exception ex) {
            Toast.makeText(this,"Some Fields Are Missing",Toast.LENGTH_LONG).show();
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(firstLaunchCreateAdmin.this);

        boolean successfulAdd = dataBaseHelper.addStaff(newStaff);

        if(successfulAdd == true){
            Toast.makeText(firstLaunchCreateAdmin.this,"Successfully Added " + first_name.getText() + " To The Database",Toast.LENGTH_LONG).show();
            Intent i = new Intent(firstLaunchCreateAdmin.this, login.class);
            startActivity(i);
        }
        else{
            Toast.makeText(firstLaunchCreateAdmin.this,"Admin Failed To Be Added To Database",Toast.LENGTH_LONG).show();
        }

        first_name.setText("");
        surname.setText("");
        username.setText("");
        password.setText("");
        first_name.requestFocus();

    }

    //Methods for hashing the password and converting it to hex

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] hash(String input) throws NoSuchAlgorithmException {

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
}