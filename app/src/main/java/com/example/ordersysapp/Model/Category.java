package com.example.ordersysapp.Model;

public class Category {
    private String idmenuType;
    private String menuTypeName;



    public Category(String idmenuType, String menuTypeName) {
        this.idmenuType = idmenuType;
        this.menuTypeName = menuTypeName;


    }

    public String getId() {
        return idmenuType;
    }

    public void setId(String idmenuType) {
        this.idmenuType = idmenuType;
    }

    public String getName() {
        return menuTypeName;
    }

    public void setName(String menuTypeName) {
        this.menuTypeName = menuTypeName;
    }


}
