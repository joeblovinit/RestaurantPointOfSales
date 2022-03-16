package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

public class edit_menu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText food_ID, food_item, food_price;
    Button editButton, deleteButton, cancelButton;
    Spinner food_category;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        //initialize variables

        food_ID = findViewById(R.id.foodId);
        food_item = findViewById(R.id.foodItem);
        food_price = findViewById(R.id.price);

        food_category = findViewById(R.id.editFoodTypeSpinner);

        editButton = findViewById(R.id.editFoodBtn);
        deleteButton = findViewById(R.id.deleteFoodBtn);
        cancelButton = findViewById(R.id.cancelEditFoodBtn);

        //passes the food class from previous activity to set the fields for the food to be edited

        Intent i = getIntent();

        food newFood = (food) i.getSerializableExtra("newFood");



        //ArrayAdapter to populate food type spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        food_category.setAdapter(adapter);
        if (newFood.getFood_type() != null){
            int spinnerPos = adapter.getPosition(newFood.getFood_type());
            food_category.setSelection(spinnerPos);
        }

        //sets the fields from the food class passed into this activity

        food_ID.setText(String.valueOf(newFood.getFood_id()));
        food_item.setText(newFood.getFood_name());
        food_category.setPrompt(newFood.getFood_type());
        food_price.setText(Double.toString(newFood.getPrice()));


        //calls the editmenu method to update the table
        editButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                editMenu(newFood);
           }
       });

        //calls the delete method to delete record from table
       deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               delete(newFood);
           }
       });

       //tales user to menu activity
       cancelButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(),menu.class);
               startActivity(i);
           }
       });





    }
    //first tests to see if the price of food is a valid double
    //then a food class has values set from the fields in the activity to provide data to update the record
    //uses catch and toast for validation and displaying appropriate error messages
    //calls the databasehelper class to perfrom sql
    public void editMenu(food newFood){

        try
        {
            Double test = Double.parseDouble(food_price.getText().toString());
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Enter a valid price for example 4.99",Toast.LENGTH_LONG).show();
            return;
        }

        try
        {
            newFood.setFood_name(food_item.getText().toString());
            newFood.setFood_type(food_category.getSelectedItem().toString());
            newFood.setPrice(Double.parseDouble(food_price.getText().toString()));

            dataBaseHelper.updateFood(newFood);

            Toast.makeText(this,"Food Item Updated",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),menu.class);
            startActivity(i);
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Failed to Update Food Item",Toast.LENGTH_LONG).show();
        }

    }

    //uses the databasehelper class to perfrom delete query and takes user to menu activity
    public void delete(food newFood){
        try {
            dataBaseHelper.deleteFood(newFood);
            Toast.makeText(this,"Food Item Deleted",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), menu.class);
            startActivity(i);
        }
        catch(Exception e){
            Toast.makeText(this,"Failed to Delete Food Item",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    //on back presses take user back to the menu
    public void onBackPressed(){
        Intent i = new Intent(edit_menu.this, menu.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}