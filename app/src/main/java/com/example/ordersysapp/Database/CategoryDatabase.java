package com.example.ordersysapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.ordersysapp.Model.Category;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "ordersysdb.db";
    private static final int DB_VER = 1;

    public CategoryDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Category> getCategory() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"idmenuType", "menuTypeName", "menuTypeDescrip", "menuTypeStatus"};
        String sqlTable = "MenuTypes";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null, null, null, null, null);

        final List<Category> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new Category(c.getString(c.getColumnIndex("idmenuType")),
                        c.getString(c.getColumnIndex("menuTypeName"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }



}
