package com.example.ordersysapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.ordersysapp.Model.Order;

public class CartDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "cartOrders.db";
    private static final int DB_VER = 1;

    public CartDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCart() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"foodId", "foodName", "foodPrice", "foodQuantity"};
        String sqlTable = "orderCart";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("foodId")),
                        c.getString(c.getColumnIndex("foodName")),
                        c.getDouble(c.getColumnIndex("foodPrice")),
                        c.getString(c.getColumnIndex("foodQuantity"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO 'orderCart'(foodId, foodName, foodPrice, foodQuantity) VALUES('%s','%s','%s','%s');",
                order.getFoodId(),
                order.getFoodName(),
                order.getFoodPrice(),
                order.getFoodQuantity());
        db.execSQL(query);
    }

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM orderCart");
        db.execSQL(query);
    }

}
