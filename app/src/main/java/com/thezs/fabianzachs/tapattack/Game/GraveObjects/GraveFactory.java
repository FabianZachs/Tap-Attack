package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

/**
 * Created by fabianzachs on 02/03/18.
 */

public class GraveFactory {

    public GraveFactory(){}

    public GraveObject buildGrave(ShapeObject shapeToCreateGraveFrom) {

        if (shapeToCreateGraveFrom instanceof Circle) {
            return new CircleGrave((Circle) shapeToCreateGraveFrom);
        }

        else if (shapeToCreateGraveFrom instanceof Cross) {
            return new CrossGrave((Cross) shapeToCreateGraveFrom);
        }
        else if (shapeToCreateGraveFrom instanceof Arrow) {
            return new ArrowGrave((Arrow) shapeToCreateGraveFrom);
        }

        return null;

    }
}
