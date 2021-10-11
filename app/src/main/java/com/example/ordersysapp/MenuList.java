package com.example.ordersysapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ordersysapp.Common.SelectMenu;
import com.example.ordersysapp.Common.SelectMenuType;
import com.example.ordersysapp.Database.CategoryDatabase;
import com.example.ordersysapp.Database.MenuDatabase;
import com.example.ordersysapp.Model.Menu;
import com.example.ordersysapp.ViewHolder.CategoryAdapter;
import com.example.ordersysapp.ViewHolder.MenuAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuList extends AppCompatActivity implements MenuAdapter.ItemClickListener {

    RecyclerView recyclerView;
    List<Menu> menuList;
    MenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton backbtn = (FloatingActionButton) findViewById(R.id.menuListBack);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent,0);
                finish();
            }
        });

        loadAll();

    }

    private void loadAll() {
        menuList = new MenuDatabase(this).getMenu();
        adapter = new MenuAdapter(this, menuList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(int position) {
        Toast.makeText(this, menuList.get(position).getName(), Toast.LENGTH_SHORT).show();
        SelectMenu.menuID = menuList.get(position).getId();
        SelectMenu.menuName = menuList.get(position).getName();
        SelectMenu.menuDescrip = menuList.get(position).getDescrip();
        SelectMenu.menuPrice = menuList.get(position).getPrice();
        Intent intent = new Intent(this, MenuDetail.class);
        startActivity(intent);
    }
}