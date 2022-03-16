package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class main extends AppCompatActivity {

    Button settingsButton, menuButton, logoutButton, orderButton, ticketsButton;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutButton = findViewById(R.id.logoutBtn);
        settingsButton = findViewById(R.id.settingsBtn);
        menuButton = findViewById(R.id.menuBtn);
        orderButton = findViewById(R.id.orderBtn);
        ticketsButton = findViewById(R.id.ticketBtn);

        //main activity menu

        //Gets the current staff role to see if they have permission to access settings, only Managers and Admins have permission
        //If the user has permission starts the settings activity else it returns an error message

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dataBaseHelper.getCurrentUser().getStaffRole().equals("Admin") || dataBaseHelper.getCurrentUser().getStaffRole().equals("Manager")){
                    Intent i = new Intent(main.this, settings.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this,"You Do Not Have Permission To View Settings",Toast.LENGTH_LONG).show();
                }


            }
        });

        //starts the view menu activity

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main.this, menu.class);
                startActivity(i);
            }
        });

        //logs the user out and takes them to the login screen

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteCurrentUser();
                Intent i = new Intent(main.this, login.class);
                startActivity(i);
            }
        });

        //starts the order food activity

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main.this, order_food_table.class);
                startActivity(i);
            }
        });

        //Starts the current active tickets activity

        ticketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main.this, tickets.class);
                startActivity(i);
            }
        });
    }
}