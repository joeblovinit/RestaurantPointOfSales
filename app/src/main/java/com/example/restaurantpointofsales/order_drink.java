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

public class order_drink extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    ArrayList<String> drinkAr;

    Button back;

    ListView drink_list;

    ArrayAdapter arrayAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_drink);

        back = findViewById(R.id.orderDrinkBackBtn);

        drink_list = findViewById(R.id.orderDrinkListView);

        drinkAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_drink.this);

        //passes the table class into the activity

        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");

        List<food> drink_menu_list = dataBaseHelper.populate_food_drinks();

        final ArrayList<food> food_drink_object_list = new ArrayList<>(drink_menu_list);

        //populates drink food class list, and string list for the listview

        for(int i = 0; i <  drink_menu_list.size(); i++){
            food_drink_object_list.add( drink_menu_list.get(i));
            drinkAr.add(" \t Price £" +  drink_menu_list.get(i).getPrice() + " \t " +  drink_menu_list.get(i).getFood_name());
        }

        //array adapter to populate listview

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, drinkAr);
        drink_list.setAdapter(arrayAdapter);

        //passes drink food class and table class into add to order activity

        drink_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_drink_object_list.get(position);

                Intent i = new Intent(order_drink.this, order_food_add.class);
                i.putExtra("newFood", newFood);
                i.putExtra("newTable", newTable);
                startActivity(i);

            }
        });

        //takes user back to the order activity

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_drink.this, order_food.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });
    }
}