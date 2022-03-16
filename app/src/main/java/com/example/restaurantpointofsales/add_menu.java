package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class add_menu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText foodItem, foodPrice;
    Button addFood, cancel;
    Spinner typeSpinner;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        //initialize variables

        foodItem = findViewById(R.id.foodItem);

        foodPrice = findViewById(R.id.price);

        typeSpinner = findViewById(R.id.foodTypeSpinner);

        addFood = findViewById(R.id.addFoodBtn);
        cancel = findViewById(R.id.cancelAddFoodBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(this);



        //Creates a new food record from the spinner and editTexts using an SQL query

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                food newFood = null;
                try {
                    newFood = new food(1, foodItem.getText().toString(), typeSpinner.getSelectedItem().toString(), null, Double.parseDouble(foodPrice.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(add_menu.this,"One of the fields is missing",Toast.LENGTH_LONG).show();
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(add_menu.this);

                boolean successfulAdd = dataBaseHelper.addFood(newFood);

                if(successfulAdd){
                    Toast.makeText(add_menu.this,"Successfully Added Food To The Database",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(add_menu.this,"Food Failed To Be Added To The Database",Toast.LENGTH_LONG).show();
                }
                foodItem.setText("");
                foodPrice.setText("");
            }
        });

        //takes the user back to the main menu

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_menu.this, main.class);
                startActivity(i);
            }
        });










    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}