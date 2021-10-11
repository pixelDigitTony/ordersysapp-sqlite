package com.example.ordersysapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ordersysapp.Common.SelectMenu;
import com.example.ordersysapp.Database.CartDatabase;
import com.example.ordersysapp.Model.Order;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail);

        TextView dishName, dishPrice, dishDescrip;
        ImageView dishImage;
        CollapsingToolbarLayout collapsingToolbarLayout;
        FloatingActionButton btnCart, btnBack;
        final int dishAvailNum;
        final ElegantNumberButton numberButton;



        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        btnBack = (FloatingActionButton)findViewById(R.id.btnBack);



        dishName = (TextView)findViewById(R.id.menu_name);
        dishPrice = (TextView)findViewById(R.id.menu_price);
        dishImage = (ImageView)findViewById(R.id.menu_image);
        dishDescrip = (TextView)findViewById(R.id.menu_descrip);


        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        Intent i = getIntent();
        final String menuDescript = SelectMenu.menuDescrip;
        final String menuId = SelectMenu.menuID;
        final String menuNAME = SelectMenu.menuName;
        final Double menuPRICE = Double.parseDouble(SelectMenu.menuPrice);


        dishName.setText(menuNAME);
        dishPrice.setText(""+menuPRICE);
        dishDescrip.setText(menuDescript);

        collapsingToolbarLayout.setTitle(menuNAME);



        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberButton.getNumber().equals("0")){
                    Toast.makeText(MenuDetail.this, "Get at least 1", Toast.LENGTH_SHORT).show();
                }else{
                    new CartDatabase(getApplicationContext()).addToCart(new Order(
                            menuId,
                            menuNAME,
                            menuPRICE,
                            numberButton.getNumber()
                    ));
                    Toast.makeText(MenuDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MenuList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent,0);
                    finish();
                }
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent,0);
                finish();
            }
        });

    }

}