package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class order_food_add extends AppCompatActivity {

    EditText food_name, food_modifier;

    Button order_Food, cancel_order;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_add);


        food_name = findViewById(R.id.orderFoodName);
        food_modifier = findViewById(R.id.orderModifier);

        order_Food = findViewById(R.id.orderAddFood);
        cancel_order = findViewById(R.id.orderCancelAddFood);

        //paases food class and table class into activity

        Intent i = getIntent();

        food newFood = (food) i.getSerializableExtra("newFood");
        table newTable = (table) i.getSerializableExtra("newTable");


        food_name.setText(newFood.getFood_name());

        //takes user back to the order food activity and passes table class into the activity

        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food_add.this, order_food.class);
                i.putExtra("newTable",newTable);
                startActivity(i);
            }
        });

        //uses databasehandler class to perform sql query to add food to table

        order_Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(food_modifier.getText().toString().isEmpty() == false){
                    newFood.setFood_modifiers(food_modifier.getText().toString());
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(order_food_add.this);

                boolean successfulAdd = dataBaseHelper.addOrder(newFood, newTable.getTable_num());

                if(successfulAdd == true){
                    Toast.makeText(order_food_add.this,"Added Food To Order",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(order_food_add.this, order_food.class);
                    i.putExtra("newTable",newTable);
                    startActivity(i);
                }
                else{
                    Toast.makeText(order_food_add.this,"Food Failed To Be Added To Order",Toast.LENGTH_LONG).show();
                }
            }
        });


    }



}