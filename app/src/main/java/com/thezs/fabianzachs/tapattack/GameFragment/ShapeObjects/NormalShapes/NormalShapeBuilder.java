package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeBitmapManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;

public class NormalShapeBuilder {

    private ShapeBitmapManager shapeBitmapManager;
    private ThemeManager themeManager;

    public NormalShapeBuilder() {
        this.shapeBitmapManager = new ShapeBitmapManager();
        this.themeManager = new ThemeManager();


    }

    public ShapeObject buildShape(String shape, Integer color, Point centerLocation, Paint paint,
                                  Rect bitmapHolder, int shapeRadius) {

        switch (shape) {

            case "circle":
                return new Circle(centerLocation, new Bitmap[] {
                        shapeBitmapManager.getBitmap(shape, false),
                        shapeBitmapManager.getBitmap(shape, true)
                        },
                        color, themeManager.getShapePaint(paint, color), bitmapHolder, shapeRadius);
            case "square":
                return new Square(centerLocation, new Bitmap[] {
                        shapeBitmapManager.getBitmap(shape, false),
                        shapeBitmapManager.getBitmap(shape, true)
                },
                        color, themeManager.getShapePaint(paint, color), bitmapHolder, shapeRadius);
            case "cross":
                return new Cross(centerLocation, new Bitmap[] {
                        shapeBitmapManager.getBitmap(shape, false),
                        null
                },
                        color, themeManager.getShapePaint(paint, color), bitmapHolder, shapeRadius);
            case "star":
                return new Star(centerLocation, new Bitmap[] {
                        shapeBitmapManager.getBitmap(shape, false),
                        shapeBitmapManager.getBitmap(shape, true)
                },
                        color, themeManager.getColors(), themeManager.getShapePaint(paint, color),
                        bitmapHolder, shapeRadius);
        }

        throw new IllegalArgumentException("unknown shape to build");
    }


    public ShapeObject buildShape(String shape, Integer color, Point centerLocation, Paint paint,
                                  Rect bitmapHolder, int shapeRadius, String direction) {

        switch (shape) {

            case "arrow":
                return new Arrow(centerLocation, new Bitmap[] {
                        shapeBitmapManager.getBitmap(shape, false),
                        null
                },
                        color, themeManager.getShapePaint(paint, color), bitmapHolder, shapeRadius,
                        direction);
        }

        throw new IllegalArgumentException("unknown shape to build");
    }

}
