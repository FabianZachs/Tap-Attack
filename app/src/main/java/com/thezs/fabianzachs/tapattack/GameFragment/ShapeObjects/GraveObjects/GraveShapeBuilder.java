package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Arrow;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Circle;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

public class GraveShapeBuilder {

    public static GraveObject buildGrave(ShapeObject shape) {

        if (shape instanceof Circle) {
            return new CircleGrave((Circle) shape);
        }

        else if (shape instanceof Arrow) {
            return new ArrowGrave((Arrow) shape);
        }

        throw new IllegalArgumentException("unknown grave to build");
    }
}
