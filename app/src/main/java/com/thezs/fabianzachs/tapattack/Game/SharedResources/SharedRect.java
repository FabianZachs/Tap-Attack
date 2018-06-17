package com.thezs.fabianzachs.tapattack.Game.SharedResources;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by fabianzachs on 08/03/18.
 */

public class SharedRect {

    private final int MAX_NUMBER_OF_RECTS = 8; // TODO this is tied to max number of shapes in shapesPopulator
    private ArrayBlockingQueue<Rect> unUsedRects;
    private ArrayList<Rect> usedRects;

    public SharedRect() {
        unUsedRects = new ArrayBlockingQueue<Rect>(MAX_NUMBER_OF_RECTS);
        usedRects = new ArrayList<>();

        for (int i = 0; i < MAX_NUMBER_OF_RECTS; i++)
            unUsedRects.add(new Rect());

    }

    public Rect getUnUsedRect() {
        //Log.d("debugrect", "getUnUsedRect: unusedrect:" + unUsedRects.size());
        //Log.d("debugrect", "getUnUsedRect: usedrect:" + usedRects.size());
        Rect rect = unUsedRects.poll();
        if (rect!=null)
            usedRects.add(rect);

        return rect;
    }

    public void freeRect(Rect rect) {
        //Log.d("paint", "freePaint: rect freed");
        unUsedRects.add(rect);
        usedRects.remove(rect);
    }
}
