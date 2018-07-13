package com.thezs.fabianzachs.tapattack.Database;

/**
 * Created by fabianzachs on 13/07/18.
 */

public class BasicStoreItem {

    private String _name;
    private String _drawableFile;
    private int _unlocked;

    public BasicStoreItem(String _name, String _drawableId, int _unlocked) {
        this._name = _name;
        this._drawableFile = _drawableId;
        this._unlocked = _unlocked;
    }

    public String get_name() {
        return _name;
    }

    public String get_file() {
        return _drawableFile;
    }

    public int is_unlocked() {
        return _unlocked;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_file(String _drawableFile) {
        this._drawableFile = _drawableFile;
    }

    public void set_unlocked(int _unlocked) {
        this._unlocked = _unlocked;
    }

}
