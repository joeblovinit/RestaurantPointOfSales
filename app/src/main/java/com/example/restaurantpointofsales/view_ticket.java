package com.example.restaurantpointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_ticket extends AppCompatActivity {

    TextView ticket_text_view;

    Button delete, back;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        ticket_text_view = findViewById(R.id.ticketTextView);

        delete = findViewById(R.id.viewTicketDeleteBtn);
        back = findViewById(R.id.viewTicketBackBtn);

        Intent i = getIntent();

        foodOrder newOrder = (foodOrder) i.getSerializableExtra("newOrder");
        String ticket = i.getStringExtra("ticket");

        ticket_text_view.setText(ticket);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteTicket(newOrder);

                Intent i = new Intent(view_ticket.this,tickets.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_ticket.this,tickets.class);
                startActivity(i);
            }
        });

    }
}