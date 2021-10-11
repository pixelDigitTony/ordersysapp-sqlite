package com.example.ordersysapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ordersysapp.Common.SelectOrderDetails;
import com.example.ordersysapp.Database.OrderDatabase;
import com.example.ordersysapp.Model.Request;
import com.example.ordersysapp.ViewHolder.OrderAdapter;

import java.util.List;

public class OrderList extends AppCompatActivity implements OrderAdapter.ItemClickListener {

    RecyclerView recyclerView;
    List<Request> requestList;
    OrderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        Button btnBack = (Button) findViewById(R.id.goBackOrderList);

        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loadAll();
    }

    private void loadAll() {
        requestList = new OrderDatabase(this).getOrder();
        adapter = new OrderAdapter(this, requestList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(int position) {
        Toast.makeText(this, requestList.get(position).getRequestId(), Toast.LENGTH_SHORT).show();
        SelectOrderDetails.orderDetailID = requestList.get(position).getRequestId();
        SelectOrderDetails.orderDetailNumber = requestList.get(position).getRequestNumber();
        SelectOrderDetails.orderDetailSTATUS = requestList.get(position).getRequestStatus();
        SelectOrderDetails.orderDetailTotal = requestList.get(position).getRequestTotal();
        SelectOrderDetails.orderDetailDatetime = requestList.get(position).getRequestdateTime();
        Intent intent = new Intent(this, OrderDetail.class);
        startActivity(intent);
    }
}