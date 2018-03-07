package com.thezs.fabianzachs.tapattack.Game.SharedResources;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by fabianzachs on 07/03/18.
 */

public class SharedPaint {

    private final int MAX_NUMBER_OF_PAINTS = 5; // TODO this is tied to max number of shapes in shapesPopulator
    //private HashMap<Boolean, ArrayBlockingQueue<Paint>> mapOfPaints;
    private ArrayBlockingQueue<Paint> unUsedPaints;
    private ArrayList<Paint> usedPaints;

    public SharedPaint() {
        //mapOfPaints = new HashMap<>();
        unUsedPaints = new ArrayBlockingQueue<Paint>(MAX_NUMBER_OF_PAINTS);
        usedPaints = new ArrayList<>();

        for (int i = 0; i < MAX_NUMBER_OF_PAINTS; i++)
            unUsedPaints.add(new Paint());

    }

    public Paint getUnUsedPaint() {
        Paint paint = unUsedPaints.poll();
        if (paint!=null)
            usedPaints.add(paint);

        return paint; // TODO returns null if empty,  HANDLE THIS (if null, dont create a new shape wait for next iteration
    }

    public void freePaint(Paint paint) {
        paint.setAlpha(255);
        unUsedPaints.add(paint);
        usedPaints.remove(paint);
    }



}
