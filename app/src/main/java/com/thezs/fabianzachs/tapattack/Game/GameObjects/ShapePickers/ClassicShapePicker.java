package com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapePickers;

/**
 * Created by fabianzachs on 29/07/18.
 */

public class ClassicShapePicker extends ShapePicker {




    @Override
    public String getShape() {
        int i = rand.nextInt(850);
        //Log.d("intindex", "getShape: int" + i);

        if (i<25)
            return "cross";
        else if (i<50)
            return "star";
        else if (i<200)
            return "square";


        else if (i<500)
            return "arrow";

        else
            return "circle";
    }
}
