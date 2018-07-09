package com.thezs.fabianzachs.tapattack.Database;

/**
 * Created by fabianzachs on 09/07/18.
 */

public class StoreItem {

    private int _id;
    private String _category;
    private String _name;
    private Integer _drawableId;
    private int _pricePoints;
    private int _priceMoney;
    private boolean _unlocked;

    public StoreItem(String _category, String _name, Integer  _drawableId, int _pricePoints, int _priceMoney, boolean _unlocked) {
        this._category = _category;
        this._name = _name;
        this._drawableId = _drawableId;
        this._pricePoints = _pricePoints;
        this._priceMoney = _priceMoney;
        this._unlocked = _unlocked;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_drawableId(Integer _drawableId) {
        this._drawableId = _drawableId;
    }

    public void set_pricePoints(int _pricePoints) {
        this._pricePoints = _pricePoints;
    }

    public void set_priceMoney(int _priceMoney) {
        this._priceMoney = _priceMoney;
    }

    public void set_unlocked(boolean _unlocked) {
        this._unlocked = _unlocked;
    }

    public int get_id() {
        return _id;
    }

    public String get_category() {
        return _category;
    }

    public String get_name() {
        return _name;
    }

    public Integer get_drawableId() {
        return _drawableId;
    }

    public int get_pricePoints() {
        return _pricePoints;
    }

    public int get_priceMoney() {
        return _priceMoney;
    }

    public boolean is_unlocked() {
        return _unlocked;
    }
}
