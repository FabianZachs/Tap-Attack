package com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapePickers;

/**
 * Created by fabianzachs on 29/07/18.
 */

public class SingleShapePicker extends ShapePicker {
    private String shapeToReturn;

    public SingleShapePicker(String shapeToReturn) {
        this.shapeToReturn = shapeToReturn;
    }

    @Override
    public String getShape() {
        return shapeToReturn;
    }
}
