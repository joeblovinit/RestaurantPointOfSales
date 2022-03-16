package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    ListView menu_list;
    ArrayList<String> foodList;
    ArrayAdapter arrayAdapter;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_list = findViewById(R.id.menuList);

        foodList = new ArrayList<String>();
        
        dataBaseHelper = new DataBaseHelper(menu.this);

        List<food> food_menu_list = dataBaseHelper.populate_food_menu();

        final ArrayList<food> food_object_list = new ArrayList<food>();

        //populates a food class list and string list, string list is used to populate listView
        //food class list elements have the same position as corresponding elements in the string list so the class list is used to get data
        //when the user clicks on the listview position

        //Populate foodList with the food name, food type and food price of everything in the men
        for(int i = 0; i < food_menu_list.size(); i++){
            food_object_list.add(food_menu_list.get(i));
            foodList.add(food_menu_list.get(i).getFood_id() + " \t" + food_menu_list.get(i).getFood_name() + " \t" + food_menu_list.get(i).getFood_type() + "\t £" + food_menu_list.get(i).getPrice() );
        }

        //Populate the ListView with the food name, food type and food price of everything in the menu from foodList
        arrayAdapter = new ArrayAdapter(menu.this, android.R.layout.simple_list_item_1, foodList);
        menu_list.setAdapter(arrayAdapter);

        //Tests to see if the user has permission to edit the menu it then passes the food class into the edit menu activity
        //for it to be used to update the database record, edit menu actitvity is then started

        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dataBaseHelper.getCurrentUser().getStaffRole().equals("Admin") || dataBaseHelper.getCurrentUser().getStaffRole().equals("Manager")) {

                    food newFood = food_object_list.get(position);


                    Intent i = new Intent(menu.this, edit_menu.class);
                    i.putExtra("newFood", newFood);

                    startActivity(i);
                }
                else{
                    Toast.makeText(menu.this,"You Do Not have Permissions To Edit the Menu",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    @Override
    public void onBackPressed(){
        Intent i = new Intent(menu.this, main.class);
        startActivity(i);
    }


}

