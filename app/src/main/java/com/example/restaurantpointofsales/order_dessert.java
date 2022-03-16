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

public class order_dessert extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    ArrayList<String> dessertAr;

    Button back;

    ListView dessert_list;

    ArrayAdapter arrayAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dessert);

        back = findViewById(R.id.orderDessertBackBtn);

        dessert_list = findViewById(R.id.orderDessertListView);

        dessertAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_dessert.this);

        //passes the table class into the activty

        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");

        List<food> dessert_menu_list = dataBaseHelper.populate_food_dessert();

        //populates string list and dessertmenu food class list

        final ArrayList<food> food_dessert_object_list = new ArrayList<>(dessert_menu_list);

        for(int i = 0; i <  dessert_menu_list.size(); i++){
            food_dessert_object_list.add( dessert_menu_list.get(i));
            dessertAr.add(" \t Price £" +  dessert_menu_list.get(i).getPrice() + " \t " +  dessert_menu_list.get(i).getFood_name());
        }

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dessertAr);
        dessert_list.setAdapter(arrayAdapter);

        //passes the food class and table class to the add food class and starts the activity

        dessert_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_dessert_object_list.get(position);

                Intent i = new Intent(order_dessert.this, order_food_add.class);
                i.putExtra("newFood", newFood);
                i.putExtra("newTable", newTable);
                startActivity(i);

            }
        });

        //takes the user back to the order food activity;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_dessert.this, order_food.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });
    }
}