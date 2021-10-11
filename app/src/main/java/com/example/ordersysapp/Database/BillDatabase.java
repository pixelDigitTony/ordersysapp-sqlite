package com.example.ordersysapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.ordersysapp.Common.SelectMenuType;
import com.example.ordersysapp.Common.SelectOrderDetails;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import com.example.ordersysapp.Model.Bill;
import com.example.ordersysapp.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class BillDatabase extends SQLiteAssetHelper{

    private static final String DB_NAME = "ordersysdb.db";
    private static final int DB_VER = 1;

    public BillDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Bill> getBill() {
        SQLiteDatabase db = getReadableDatabase();


        String[] selectionArgs = new String[]{SelectOrderDetails.orderDetailID};

        final String query = "SELECT OrderMenus.menuQuantity, Menus.menuName, Menus.menuPrice FROM OrderMenus LEFT JOIN Menus ON OrderMenus.menu_idmenu = Menus.idmenu WHERE OrderMenus.order_idorder =?";
        Cursor c = db.rawQuery(query, selectionArgs);

        final List<Bill> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new Bill(c.getString(c.getColumnIndex("OrderMenus.menuQuantity")),
                        c.getString(c.getColumnIndex("Menus.menuName")),
                        c.getString(c.getColumnIndex("Menus.menuPrice"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }
}
