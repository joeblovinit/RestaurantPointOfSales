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

public class order_side extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    ArrayList<String> sideAr;

    Button back;

    ListView side_list;

    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_side);

        back = findViewById(R.id.orderSideBackBtn);

        side_list = findViewById(R.id.orderSideListView);

        sideAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_side.this);

        //passes table class into activity

        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");

        //database query to populate list from table data

        List<food> side_menu_list = dataBaseHelper.populate_food_side();

        final ArrayList<food> food_side_object_list = new ArrayList<>(side_menu_list);

        //populates food class list and string list for the listview with corresponding data at each position
        for(int i = 0; i <  side_menu_list.size(); i++){
            food_side_object_list.add( side_menu_list.get(i));
            sideAr.add(" \t Price £" +  side_menu_list.get(i).getPrice() + " \t " +  side_menu_list.get(i).getFood_name());
        }

        //populates listview using adapter

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, sideAr);
        side_list.setAdapter(arrayAdapter);

        //passes food class and table class into add food to customer order class

        side_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_side_object_list.get(position);

                Intent i = new Intent(order_side.this, order_food_add.class);
                i.putExtra("newFood", newFood);
                i.putExtra("newTable", newTable);
                startActivity(i);

            }
        });

        //takes user back to order food activity

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_side.this, order_food.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });
    
    }
}