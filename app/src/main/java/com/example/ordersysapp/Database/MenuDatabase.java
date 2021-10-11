package com.example.ordersysapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.ordersysapp.Common.SelectMenuType;
import com.example.ordersysapp.Model.Menu;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MenuDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "ordersysdb.db";
    private static final int DB_VER = 1;

    public MenuDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Menu> getMenu() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"idmenu", "menuName", "menuDescrip", "menuPrice" };
        String sqlTable = "Menus";
        String selection = "menuType_idmenuType = ?";
        String[] selectionArgs = new String[]{SelectMenuType.menuID};

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, selection, selectionArgs, null, null, null);

        final List<Menu> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                    result.add(new Menu(c.getString(c.getColumnIndex("idmenu")),
                            c.getString(c.getColumnIndex("menuName")),
                            c.getString(c.getColumnIndex("menuDescrip")),
                            c.getString(c.getColumnIndex("menuPrice"))
                    ));
            } while (c.moveToNext());
        }
        return result;
    }
}
