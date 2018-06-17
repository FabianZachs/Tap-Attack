package com.thezs.fabianzachs.tapattack.Game.SharedResources;

import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by fabianzachs on 07/03/18.
 */

public class SharedPaint {

    private final int MAX_NUMBER_OF_PAINTS = 8; // TODO this is tied to max number of shapes in shapesPopulator
    /*private*/ public ArrayBlockingQueue<Paint> unUsedPaints;
    /*private*/ public ArrayList<Paint> usedPaints;

    public SharedPaint() {
        unUsedPaints = new ArrayBlockingQueue<Paint>(MAX_NUMBER_OF_PAINTS);
        usedPaints = new ArrayList<>();

        for (int i = 0; i < MAX_NUMBER_OF_PAINTS; i++)
            unUsedPaints.add(new Paint());

    }

    public Paint getUnUsedPaint() {
        //Log.d("debugpaint", "getUnUsedPaint: unusedpaint:" + unUsedPaints.size());
        //Log.d("debugpaint", "getUnUsedPaint: usedpaint:" + usedPaints.size());
        Paint paint = unUsedPaints.poll();
        if (paint!=null)
            usedPaints.add(paint);

        return paint;
    }

    public void freePaint(Paint paint) {
        //Log.d("debug4", "freePaint: paint freed");
        //Log.d("seeifweare freeing paint", "freePaint: paint freed" + paint);
        paint.setAlpha(255);
        //Log.d("beforefreeint", "getUnUsedPaint: unusedpaint:" + unUsedPaints.size());
        //Log.d("beforefreeint", "getUnUsedPaint: usedpaint:" + usedPaints.size());
        boolean work = unUsedPaints.add(paint);
        //Log.d("diditwork", "freePaint: " + work);
        boolean work2 = usedPaints.remove(paint);
        //Log.d("diditwork", "freePaint2: " + work2);
        //Log.d("beforefreeint", "getUnUsedPaint: unusedpaint:after" + unUsedPaints.size());
        //Log.d("beforefreeint", "getUnUsedPaint: usedpaint:after" + usedPaints.size());
        //Log.d("debug4", "freePaint: size" + unUsedPaints.size());
    }

}
