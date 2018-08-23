package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

public class PaidUnlockItem {

    private int price;
    private int quantity;
    private int drawableId;


    public PaidUnlockItem(int price, int quantity, int drawableId) {
        this.price = price;
        this.quantity = quantity;
        this.drawableId = drawableId;
    }



    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
