package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class order_main extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    ArrayList<String> mainAr;

    Button back;

    ListView main_list;

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        back = findViewById(R.id.orderMainBackBtn);

        main_list = findViewById(R.id.orderMainListView);

        mainAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_main.this);

        //passes table class into activity

        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");

        //database query to populate list from table data

        List<food> main_menu_list = dataBaseHelper.populate_food_main();

        final ArrayList<food> food_main_object_list = new ArrayList<>(main_menu_list);

        //populates food class list and string list for the listview with corresponding data at each position
        for(int i = 0; i <  main_menu_list.size(); i++){
            food_main_object_list.add( main_menu_list.get(i));
            mainAr.add(" \t Price £" +  main_menu_list.get(i).getPrice() + " \t " +  main_menu_list.get(i).getFood_name());
        }

        //populates listview using adapter
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mainAr);
        main_list.setAdapter(arrayAdapter);

        //passes the data into new activity to order a main to the customer order
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_main_object_list.get(position);

                Intent i = new Intent(order_main.this, order_food_add.class);
                i.putExtra("newFood", newFood);
                i.putExtra("newTable", newTable);
                startActivity(i);

            }
        });

        //takes user back to the order food activity

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_main.this, order_food.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

    }
}