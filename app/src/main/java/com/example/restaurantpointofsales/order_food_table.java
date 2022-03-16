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

public class order_food_table extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    Button cancel_select_table;

    ListView table_list;

    ArrayList<String> tableAr;

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_table);

        table_list = findViewById(R.id.selectTableList);

        cancel_select_table = findViewById(R.id.cancelSelectTable);

        tableAr = new ArrayList<String>();




        DataBaseHelper dataBaseHelper = new DataBaseHelper(order_food_table.this);

        List<table> allTables = dataBaseHelper.populate_table_list();

        //populates String list for Listview and tables class list to get data from listview position

        final ArrayList<table> table_object_list = new ArrayList<>(allTables);

        for(int i = 0; i < allTables.size(); i ++){
            table_object_list.add(allTables.get(i));
            tableAr.add("Table Number: " + allTables.get(i).getTable_num());
        }

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tableAr);

        table_list.setAdapter(arrayAdapter);

        //takes user back to main activity

        cancel_select_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food_table.this, main.class);
                startActivity(i);
            }
        });

        //when an element in the listview is clicked it's corresponding data in the
        //table class list is then passed into the new activity order food

        table_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                table newTable = table_object_list.get(position);

                Intent i = new Intent(order_food_table.this, order_food.class);
                i.putExtra("newTable",newTable);
                startActivity(i);
            }
        });


    }
}