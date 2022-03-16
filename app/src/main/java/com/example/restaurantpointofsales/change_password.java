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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class change_password extends AppCompatActivity {

    EditText staff_id, change_password, confirm_change;
    Button change_password_button, cancel_change_password_button;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent i = getIntent();
        staffMember newStaff = (staffMember) i.getSerializableExtra("newStaff");


        staff_id = findViewById(R.id.changePasswordStaffId);
        change_password = findViewById(R.id.staffChangePassword);
        confirm_change = findViewById(R.id.staffConfirmChangePassword);

        change_password_button = findViewById(R.id.changePassBtn);
        cancel_change_password_button = findViewById(R.id.cancelChangePassBtn);

        staff_id.setText(String.valueOf(newStaff.getStaffId()));


        change_password_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                changePass(newStaff);
            }
        });

        cancel_change_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_password.this, edit_staff.class);
                startActivity(i);
            }
        });
    }
    //checks if the password fields are matching then hashes the password and converts it to hex for security
    //password is then updated

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changePass(staffMember newStaff)  {

        if(change_password.getText().toString().equals(confirm_change.getText().toString()) == true) {
            try{
                newStaff.setStaffPass(toHex(hash(change_password.getText().toString())));

                dataBaseHelper.updateStaffPass(newStaff);

                Toast.makeText(this, "Password Updated", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),main.class);
                startActivity(i);
            }
            catch(Exception ex){
                Toast.makeText(this,"Failed To Change Password",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(this,"Passwords Do Not Match",Toast.LENGTH_LONG).show();
        }


    }

    //method to generate hash

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] hash(String input) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));


    }
    //method to set string to hex
    public static String toHex(byte[] hash){

        BigInteger x = new BigInteger(1, hash);

        StringBuilder hex = new StringBuilder(x.toString(16));

        while (hex.length() < 32){
            hex.insert(0,'0');
        }
        return hex.toString();
    }

}

