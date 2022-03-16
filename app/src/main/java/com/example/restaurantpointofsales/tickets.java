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
import java.util.HashMap;
import java.util.List;

public class tickets extends AppCompatActivity {

    ListView ticket_list;

    ArrayList<String> ticketAr;

    ArrayAdapter arrayAdapter;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        ticket_list = findViewById(R.id.ticketList);

        ticketAr = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(tickets.this);

        cancel = findViewById(R.id.cancelTicketBtn);

        //ticket order list using sql query

        List<foodOrder> ticket_order_list = dataBaseHelper.populate_tickets();

        final ArrayList<foodOrder> food_order_object_list = new ArrayList<>(ticket_order_list);


        for(int i = 0; i < ticket_order_list.size(); i++){



            food_order_object_list.add(ticket_order_list.get(i));



            String Ticket = ("Table Number"
                    + ticket_order_list.get(i).getTable_num()
                    + "\t total price: "
                    + ticket_order_list.get(i).getPrice()
                    + "\t Staff Member:"
                    + ticket_order_list.get(i).getStaff_name()
                    + "\n" + ticket_order_list.get(i).getStarter()
                    + "\n" + ticket_order_list.get(i).getMain()
                    + "\n" + ticket_order_list.get(i).getSide()
                    + "\n" + ticket_order_list.get(i).getDessert()
                    + "\n" + ticket_order_list.get(i).getDrink()
                    + "\n Time Ordered: " + ticket_order_list.get(i).getTime_ordered());

            ticketAr.add("Table Number"
                    + ticket_order_list.get(i).getTable_num()
                    + "\t total price: "
                    + ticket_order_list.get(i).getPrice()
                    + "\t Staff Member: "
                    + ticket_order_list.get(i).getStaff_name());

        }


        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ticketAr);
        ticket_list.setAdapter(arrayAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(tickets.this,main.class);
                startActivity(i);
            }
        });

        ticket_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodOrder newOrder = food_order_object_list.get(position);

                String Ticket = new String();

                for(int j = 0; j < ticket_order_list.size(); j++){
                    Ticket = ("Table Number :"
                            + ticket_order_list.get(position).getTable_num()
                            + "\t total price: "
                            + ticket_order_list.get(position).getPrice()
                            + "\t Staff Member: "
                            + ticket_order_list.get(position).getStaff_name()
                            + "\n" + ticket_order_list.get(position).getStarter()
                            + "\n" + ticket_order_list.get(position).getMain()
                            + "\n" + ticket_order_list.get(position).getSide()
                            + "\n" + ticket_order_list.get(position).getDessert()
                            + "\n" + ticket_order_list.get(position).getDrink()
                            + "\n Time Ordered: " + ticket_order_list.get(position).getTime_ordered());

                }


                Intent i = new Intent(tickets.this,view_ticket.class);
                i.putExtra("newOrder", newOrder);
                i.putExtra("ticket", Ticket);

                startActivity(i);

            }
        });

    }
}