package com.example.restaurantpointofsales;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class order_starter extends Activity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    ArrayList<String> starterAr;

    Button back;

    ListView starter_list;

    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_starter);

        back = findViewById(R.id.orderStarterBackBtn);

        starter_list = findViewById(R.id.orderStarterListView);

        starterAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_starter.this);

        //passes table class into activity
        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");


        //database query to populate list from table data
        List<food> starter_menu_list = dataBaseHelper.populate_food_starter();

        final ArrayList<food> food_starter_object_list = new ArrayList<>(starter_menu_list);

        //populates food class list and string list for the listview with corresponding data at each position

        for(int i = 0; i < starter_menu_list.size(); i++){
            food_starter_object_list.add(starter_menu_list.get(i));
            starterAr.add(" \t Price £" + starter_menu_list.get(i).getPrice() + " \t " + starter_menu_list.get(i).getFood_name());
        }

        //populates listView using adapter

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, starterAr);
        starter_list.setAdapter(arrayAdapter);

        //passes selected food class and table into add food to customer order activity

        starter_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_starter_object_list.get(position);

                Intent i = new Intent(order_starter.this, order_food_add.class);
                i.putExtra("newFood", newFood);
                i.putExtra("newTable", newTable);
                startActivity(i);

            }
        });

        //takes user back to order food activity

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_starter.this, order_food.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

    }


}