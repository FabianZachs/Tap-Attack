package com.thezs.fabianzachs.tapattack.Database;

/**
 * Created by fabianzachs on 09/07/18.
 */

public class StoreItem {

    private int _id;
    private String _category;
    private String _name;
    private String _drawableFile;
    private Integer _warningColor1;
    private Integer _warningColor2;
    private int _pricePoints;
    private int _priceMoney;
    private int _unlocked;

    public StoreItem(String _category, String _name, String _drawableFile, Integer _warningColor1, Integer _warningColor2, int _pricePoints, int _priceMoney, int _unlocked) {
        this._category = _category;
        this._name = _name;
        this._drawableFile = _drawableFile;
        this._warningColor1 = _warningColor1;
        this._warningColor2 = _warningColor2;
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

    public void set_file(String _drawableFile) {
        this._drawableFile = _drawableFile;
    }

    public void set_pricePoints(int _pricePoints) {
        this._pricePoints = _pricePoints;
    }

    public void set_priceMoney(int _priceMoney) {
        this._priceMoney = _priceMoney;
    }

    public void set_unlocked(int _unlocked) {
        this._unlocked = _unlocked;
    }

    public int get_id() {
        return _id;
    }

    public void set_warningColor1(Integer _warningColor1) {
        this._warningColor1 = _warningColor1;
    }

    public void set_warningColor2(Integer _warningColor2) {
        this._warningColor2 = _warningColor2;
    }

    public Integer get_warningColor1() {
        return this._warningColor1;
    }

    public Integer get_warningColor2() {
        return this._warningColor2;
    }

    public String get_category() {
        return _category;
    }

    public String get_name() {
        return _name;
    }

    public String get_file() {
        return _drawableFile;
    }

    public int get_pricePoints() {
        return _pricePoints;
    }

    public int get_priceMoney() {
        return _priceMoney;
    }

    public int is_unlocked() {
        return _unlocked;
    }
}
