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

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class order_food extends AppCompatActivity {

    Button orderStarterButton, orderMainButton, orderDessertButton, orderSideButton, orderDrinkButton, sendOrderButton ,cancelOrderButton;

    EditText table_number;

    ListView order_List;

    ArrayAdapter arrayAdapter;

    ArrayList<String> foodOrderList;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        Calendar getTime = Calendar.getInstance();
        String currentTime = getTime.get(Calendar.HOUR_OF_DAY) + ":" + getTime.get(Calendar.MINUTE);


        table_number = findViewById(R.id.table_number_edit_text);

        orderStarterButton = findViewById(R.id.orderStarterBtn);
        orderMainButton = findViewById(R.id.orderMainBtn);
        orderDessertButton = findViewById(R.id.orderDessertBtn);
        orderSideButton = findViewById(R.id.orderSideBtn);
        orderDrinkButton = findViewById(R.id.orderDrinkBtn);
        sendOrderButton = findViewById(R.id.sendOrderFoodBtn);
        cancelOrderButton = findViewById(R.id.cancelOrderBtn);

        order_List = findViewById(R.id.orderList);

        foodOrderList = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(order_food.this);

        List<food> order_list = dataBaseHelper.populate_order();




        final ArrayList<food> food_object_list = new ArrayList<>(order_list);

        //populates the order listView and food class list

        for (int i = 0; i < order_list.size(); i++) {

            food_object_list.add(order_list.get(i));

            if (order_list.get(i).getFood_modifiers() != null) {

                foodOrderList.add("\t" + order_list.get(i).getFood_type() + "\t  " + order_list.get(i).getFood_name() + "\t" + order_list.get(i).getFood_modifiers() + "\t £" + order_list.get(i).getPrice());
            } else {
                foodOrderList.add("\t" + order_list.get(i).getFood_type() + "\t  " + order_list.get(i).getFood_name() + "\t £" + order_list.get(i).getPrice());
            }
        }



        //adapter to populate listview

        arrayAdapter = new ArrayAdapter(order_food.this, android.R.layout.simple_list_item_1, foodOrderList);
        order_List.setAdapter(arrayAdapter);

        //passes table class into activity

        Intent newIntent = getIntent();
        table newTable = (table) newIntent.getSerializableExtra("newTable");

        table_number.setText(String.valueOf(newTable.getTable_num()));

        //open the order starter activity

        orderStarterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food.this, order_starter.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

        //open the main starter activity

        orderMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food.this, order_main.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

        //open the order dessert activity

        orderDessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food.this, order_dessert.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

        //open the order side activity

        orderSideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food.this, order_side.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

        //open the order drink activity

        orderDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(order_food.this, order_drink.class);
                i.putExtra("newTable", newTable);
                startActivity(i);
            }
        });

        //This is used to send the order, this generates a ticket which is displayed in the tickets actitivty
        //Uses a stringbuilder to get all the foodtypes together in a string to output on the ticket
        //generates a new foodOrder class by generating starter, main, dessert, side, drink strings
        //gets the total price of the order in the for loop, finds the current staff who ordered the food using an sql query
        //gets the time the food was ordered using java.util.Calendar and gets the table number from the table class passed into the activty

        sendOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!order_list.isEmpty()){

                    StringBuilder starters = new StringBuilder();
                    StringBuilder mains = new StringBuilder();
                    StringBuilder desserts = new StringBuilder();
                    StringBuilder sides = new StringBuilder();
                    StringBuilder drinks = new StringBuilder();

                    Double price = 0.0;


                    for (int i = 0; i < order_list.size(); i++) {

                        if (order_list.get(i).getFood_type().equals("Starter")) {
                            starters.append("\n £").append(order_list.get(i).getPrice()).append("\t").append(order_list.get(i).getFood_name());

                            if(order_list.get(i).getFood_modifiers() != null){
                                starters.append("\t").append(order_list.get(i).getFood_modifiers());
                            }

                            price = price + order_list.get(i).getPrice();

                        } else if (order_list.get(i).getFood_type().equals("Main")) {
                            mains.append("\n £").append(order_list.get(i).getPrice()).append("\t").append(order_list.get(i).getFood_name());

                            if(order_list.get(i).getFood_modifiers() != null){
                                mains.append("\t").append(order_list.get(i).getFood_modifiers());
                            }

                            price = price + order_list.get(i).getPrice();

                        } else if (order_list.get(i).getFood_type().equals("Dessert")) {
                            desserts.append("\n £").append(order_list.get(i).getPrice()).append("\t").append(order_list.get(i).getFood_name());

                            if(order_list.get(i).getFood_modifiers() != null){
                                desserts.append("\t").append(order_list.get(i).getFood_modifiers());
                            }

                            price = price + order_list.get(i).getPrice();

                        } else if (order_list.get(i).getFood_type().equals("Side")) {
                            sides.append("\n £").append(order_list.get(i).getPrice()).append("\t").append(order_list.get(i).getFood_name());

                            if(order_list.get(i).getFood_modifiers() != null){
                                sides.append("\t").append(order_list.get(i).getFood_modifiers());
                            }

                            price = price + order_list.get(i).getPrice();

                        } else if (order_list.get(i).getFood_type().equals("Drink")) {
                            drinks.append("\n £").append(order_list.get(i).getPrice()).append("\t").append(order_list.get(i).getFood_name());

                            if(order_list.get(i).getFood_modifiers() != null){
                                drinks.append("\t").append(order_list.get(i).getFood_modifiers());
                            }

                            price = price + order_list.get(i).getPrice();
                        }
                    }
                    foodOrder newOrder = new foodOrder();

                    newOrder.setStarter(starters.toString());
                    newOrder.setMain(mains.toString());
                    newOrder.setDessert(desserts.toString());
                    newOrder.setSide(sides.toString());
                    newOrder.setDrink(drinks.toString());
                    newOrder.setStaff_name(dataBaseHelper.getCurrentUser().getStaffName());
                    newOrder.setTable_num(Integer.parseInt(table_number.getText().toString()));
                    newOrder.setTime_ordered(currentTime);
                    newOrder.setPrice(price);

                    dataBaseHelper.addTicket(newOrder);
                    dataBaseHelper.deleteOrder();

                    Intent i = new Intent(order_food.this, main.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(order_food.this,"Order Is Empty",Toast.LENGTH_LONG).show();
                }
            }

        });

        //removes food from the order when they are clicked on in the listView

        order_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                food newFood = food_object_list.get(position);


                try {
                    dataBaseHelper.deleteFromFoodOrder(newFood);
                    Intent i = new Intent(com.example.restaurantpointofsales.order_food.this, com.example.restaurantpointofsales.order_food.class);
                    i.putExtra("newTable", newTable);
                    startActivity(i);
                    Toast.makeText(order_food.this,"Successfully Removed Food From Order",Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(order_food.this,"Failed TO remove food From Order",Toast.LENGTH_LONG).show();
                }
            }
        });

        //takes user back to the main menu

        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteOrder();

                Intent i = new Intent(order_food.this, main.class);

                startActivity(i);
            }
        });
    }

}
