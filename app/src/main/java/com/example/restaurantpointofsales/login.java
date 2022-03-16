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


public class login extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    EditText userTxt,passTxt;
    Button loginBtn,cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        userTxt = findViewById(R.id.user);
        passTxt = findViewById(R.id.pass);

        loginBtn = findViewById(R.id.loginBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        //login activity first page on usual startup


        dataBaseHelper = new DataBaseHelper(login.this);

        //if statement utilizing database helper class to check if the staffmember table is empty to
        //test if this is the first startup of the application

        if(dataBaseHelper.checkIfStaffIsEmpty() == true){
            Intent i = new Intent(login.this, firstLaunchCreateAdmin.class);
            startActivity(i);

        }

        //perfrom the login Method

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    Login();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }




    //login method, uses databasehelper to perform select query to see if the user exists in the table
    //based on the username and password
    //validates to see if the user has enetered a username or password

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Login() throws NoSuchAlgorithmException {

        String username = userTxt.getText().toString().trim();
        String password = toHex(hash(passTxt.getText().toString().trim()));


        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"No Username or Password Entered",Toast.LENGTH_LONG).show();
        }
        else if(dataBaseHelper.login(username,password) == true){
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show();
            dataBaseHelper.deleteCurrentUser();
            dataBaseHelper.getStaff(username,password);
            staffMember newStaff = dataBaseHelper.getStaff(username,password);
            dataBaseHelper.getCurrentStaff(newStaff);
            Intent i = new Intent(login.this, main.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Username or Password Incorrect",Toast.LENGTH_LONG).show();
        }


    }

    //clears password and username textView

    public void cancel(){
        userTxt.setText("");
        passTxt.setText("");
    }
    //methods to generate hash and hex
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