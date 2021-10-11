package com.example.ordersysapp.Model;

public class Menu {
    private String idmenu;
    private String menuName;
    private String menuDescrip;
    private String menuPrice;



    public Menu(String idmenu, String menuName, String menuDescrip, String menuPrice) {
        this.idmenu = idmenu;
        this.menuName = menuName;
        this.menuDescrip = menuDescrip;
        this.menuPrice = menuPrice;


    }

    public String getId() {
        return idmenu;
    }

    public void setId(String idmenu) {
        this.idmenu = idmenu;
    }

    public String getName() {
        return menuName;
    }

    public void setName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescrip() {
        return menuDescrip;
    }

    public void setDescrip(String menuDescrip) {
        this.menuDescrip = menuDescrip;
    }

    public String getPrice() {
        return menuPrice;
    }

    public void setPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
