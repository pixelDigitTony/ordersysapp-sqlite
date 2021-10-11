package com.example.ordersysapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordersysapp.Database.CartDatabase;
import com.example.ordersysapp.Database.OrderDatabase;
import com.example.ordersysapp.Model.Order;
import com.example.ordersysapp.ViewHolder.CartAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart extends AppCompatActivity {

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    TextView txtTotalPrice;
    Button btnPlace, cleanCart, headBack;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView) findViewById(R.id.total);
        btnPlace = (Button) findViewById(R.id.btnPlaceOrder);
        cleanCart = (Button) findViewById(R.id.clear_items);
        headBack = (Button) findViewById(R.id.head_back);


        loadListFood();

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

    }

    private void alertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Is this the final order?");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String total = (String) txtTotalPrice.getText();

                long idOrder = new OrderDatabase(getApplicationContext()).createOrder(total);
                processOrder(idOrder);
                dialog.dismiss();
                new CartDatabase(getBaseContext()).cleanCart();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 0);
                finish();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void processOrder(long idOrder) {
        cart = new CartDatabase(this).getCart();
        for (Order order : cart) {
            new OrderDatabase(getApplicationContext()).createOrderItems(idOrder, new Order(
                    order.getFoodId(),
                    order.getFoodName(),
                    order.getFoodPrice(),
                    order.getFoodQuantity()
            ));
        }
    }

    private void loadListFood() {
        cart = new CartDatabase(this).getCart();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);
        double total = 0;
        for (Order order : cart) {
            try {
                total += (order.getFoodPrice()) * (Double.parseDouble(order.getFoodQuantity()));
                String formattedValue = String.format("%.2f", total);
                txtTotalPrice.setText(formattedValue);
            } catch (NumberFormatException e) {

            }
        }
        cleanCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CartDatabase(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Cart Cleaned", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 0);
                finish();
            }
        });

    }


}