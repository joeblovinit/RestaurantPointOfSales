package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class add_table extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    EditText table_number, table_seats;

    Button add_table, cancel_add_tabel;

    ListView table_list;

    ArrayList<String> tableAr;

    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);

        //initialize variables

        table_number = findViewById(R.id.addTableNum);
        table_seats = findViewById(R.id.addTableSeats);

        add_table = findViewById(R.id.addTableBtn);
        cancel_add_tabel = findViewById(R.id.cancelAddTableBtn);



        table_list = findViewById(R.id.tableList);

        tableAr = new ArrayList<String>();

        //databasehelper class called to perfrom a query to get data from database to populate listView

        DataBaseHelper dataBaseHelper = new DataBaseHelper(add_table.this);
        List<table> allTables = dataBaseHelper.populate_table_list();


        //the object list and string list have elements added in the same position so when the listview is populated with the strings
        //the elements you click on will be the same position of the element in the object list so you can access the data.

        final ArrayList<table> table_object_list = new ArrayList<>(allTables);

        for(int i = 0; i < allTables.size(); i ++){
            table_object_list.add(allTables.get(i));
            tableAr.add("Table Number: " + allTables.get(i).getTable_num());
        }

        //array adapter to populate listView

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tableAr);

        table_list.setAdapter(arrayAdapter);


        //accesses the database helper class to perfrom sql to add a new restaurant table to the table
        //uses try and catch for validation.

        add_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table newTable = null;

                try{
                    newTable = new table(1, Integer.parseInt(table_number.getText().toString()), Integer.parseInt(table_seats.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(add_table.this,"One of the fields is missing or is not a whole number",Toast.LENGTH_LONG).show();
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(add_table.this);

                boolean successfulAdd = dataBaseHelper.addTable(newTable);

                if(successfulAdd == true){
                    Intent i = new Intent(com.example.restaurantpointofsales.add_table.this, com.example.restaurantpointofsales.add_table.class);
                    startActivity(i);
                    Toast.makeText(add_table.this,"Successfully Added Table",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(add_table.this,"Table Failed To Be Added",Toast.LENGTH_LONG).show();
                }
                table_number.setText("");
                table_seats.setText("");
            }
        });



        cancel_add_tabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_table.this, main.class);
                startActivity(i);
            }
        });

        table_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                table newTable = table_object_list.get(position);
               try {
                   dataBaseHelper.deleteTable(newTable);
                   Intent i = new Intent(com.example.restaurantpointofsales.add_table.this, com.example.restaurantpointofsales.add_table.class);
                   startActivity(i);
                   Toast.makeText(add_table.this,"Successfully Removed Table",Toast.LENGTH_LONG).show();
               }
               catch(Exception e){
                   Toast.makeText(add_table.this,"Table Failed to be removed",Toast.LENGTH_LONG).show();
               }

            }
        });


    }


    @Override
    public void onBackPressed(){
        Intent i = new Intent(add_table.this, settings.class);
        startActivity(i);
    }

}