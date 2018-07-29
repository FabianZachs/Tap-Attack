package com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapePickers;

/**
 * Created by fabianzachs on 29/07/18.
 */

public class SimpleShapePicker extends ShapePicker {
    private String shapeToReturn;

    public SimpleShapePicker(String shapeToReturn) {
        this.shapeToReturn = shapeToReturn;
    }

    @Override
    public String getShape() {
        return shapeToReturn;
    }
}
