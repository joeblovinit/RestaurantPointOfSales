package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {


    Button addToMenuButton, addStaffButton, viewStaffButton, addTablesButton, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        addToMenuButton = findViewById(R.id.addMenuBtn);
        addStaffButton = findViewById(R.id.addStaffBtn);
        viewStaffButton = findViewById(R.id.viewStaffBtn);
        addTablesButton = findViewById(R.id.settingsAddTablesBtn);
        cancel = findViewById(R.id.settingsCancelBtn);

        addToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(settings.this, add_menu.class);
                startActivity(i);
            }
        });

        addStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(settings.this, add_staff.class);
                startActivity(i);
            }
        });

        viewStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(settings.this, view_staff.class);
                startActivity(i);
            }
        });

        addTablesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(settings.this, add_table.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(settings.this, main.class);
                startActivity(i);
            }
        });
    }
}