package com.example.ordersysapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ordersysapp.Common.SelectOrderDetails;
import com.example.ordersysapp.Database.BillDatabase;
import com.example.ordersysapp.Model.Bill;
import com.example.ordersysapp.ViewHolder.BillAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    List<Bill> billList = new ArrayList<>();
    BillAdapter adapter;
    Button returnButton;
    TextView txtTotalPrice, txtOrderNumber, txtOrderDate, txtOrderStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);

        recyclerView = (RecyclerView)findViewById(R.id.billList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.totalBill);
        txtOrderStatus = (TextView)findViewById(R.id.txtStatus);
        txtOrderNumber = (TextView)findViewById(R.id.txtNumber);
        txtOrderDate = (TextView)findViewById(R.id.txtBillDate);
        returnButton = (Button)findViewById(R.id.go_back);

        String orderTotal = SelectOrderDetails.orderDetailTotal;
        String orderDate = SelectOrderDetails.orderDetailDatetime;
        String orderStatus = SelectOrderDetails.orderDetailSTATUS;
        String orderNumber = SelectOrderDetails.orderDetailNumber;

        txtTotalPrice.setText(orderTotal);
        txtOrderDate.setText(orderDate);
        txtOrderNumber.setText(orderNumber);
        txtOrderStatus.setText(orderStatus);

        loadData();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetail.this, OrderList.class);
                startActivity(intent);
            }
        });

    }

    private void loadData() {

        billList = new BillDatabase(this).getBill();
        adapter = new BillAdapter(this, billList, this);
        recyclerView.setAdapter(adapter);

    }

}