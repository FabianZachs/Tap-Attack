package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;


import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface ShapeMover {

    void update(CopyOnWriteArrayList<ShapeObject> shapes);

    void resetTimeAtLastUpdate();


}
