package com.example.ordersysapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import com.example.ordersysapp.Model.Order;
import com.example.ordersysapp.Model.Request;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OrderDatabase extends SQLiteAssetHelper{

        private static final String DB_NAME = "ordersysdb.db";
        private static final int DB_VER = 1;

        public OrderDatabase(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        public List<Request> getOrder() {
            SQLiteDatabase db = getReadableDatabase();
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

            String[] sqlSelect={"idorder", "orderNum", "order_created", "orderStatus" ,"orderTotal"};
            String sqlTable = "Orders";

            qb.setTables(sqlTable);
            Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

            final List<Request> result = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    result.add(new Request(c.getString(c.getColumnIndex("idorder")),
                            c.getString(c.getColumnIndex("orderNum")),
                            c.getString(c.getColumnIndex("order_created")),
                            c.getString(c.getColumnIndex("orderStatus")),
                            c.getString(c.getColumnIndex("orderTotal"))
                    ));
                } while (c.moveToNext());
            }
            return result;
        }

    public long createOrder(String total) {
        int random = ThreadLocalRandom.current().nextInt(1, 99999999);
        String randomNum = String.valueOf(random);
        SQLiteDatabase db = getWritableDatabase();
//        String query = String.format("INSERT INTO 'order'(orderNum, orderDiscount, orderTax, orderTotal, orderStatus) VALUES('%s','%s','%s','%s','%s');",
//                randomNum,
//                "0",
//                "0",
//                total,
//                "PENDING");
        ContentValues values = new ContentValues();
        values.put("orderNum", randomNum);
        values.put("orderDiscount", "0");
        values.put("orderTax", "0");
        values.put("orderTotal", total);
        values.put("orderStatus", "PENDING");
        return db.insert("'Orders'", "", values);
    }

    public void createOrderItems(long idOrder, Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("INSERT INTO OrderMenus(order_idorder, menu_idmenu, menuQuantity) VALUES('%s','%s','%s');",
                idOrder,
                order.getFoodId(),
                order.getFoodQuantity());
        db.execSQL(query);
    }

}
