package com.thezs.fabianzachs.tapattack.Game.SharedResources;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by fabianzachs on 07/03/18.
 */

public class SharedPaint {

    // has a list of X paints
    // each paint in list has a boolean as to whether its in use
    // if used==false, return that paint on request.
    // if used==true, go to next paint in list
    // if no more paints, return null (telling shapePopulator to wait until a paint is freed up from previously initialized shape)
    // best data structure is map???
    private final int MAX_NUMBER_OF_PAINTS = 5;
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
        usedPaints.add(paint);
        return paint; // TODO returns null if empty,  HANDLE THIS
    }

    public void freePaint(Paint paint) {
        unUsedPaints.add(paint);
        usedPaints.remove(paint);
    }



}
