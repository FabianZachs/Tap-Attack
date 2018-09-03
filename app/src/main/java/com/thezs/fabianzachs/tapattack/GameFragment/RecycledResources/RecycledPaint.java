package com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources;

import android.graphics.Paint;
import android.util.Log;


import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class RecycledPaint {

    private ArrayBlockingQueue<Paint> unusedPaint;
    private ArrayList<Paint> usedPaint;

    public RecycledPaint() {
        unusedPaint = new ArrayBlockingQueue<>(10);
        usedPaint = new ArrayList<>();

    }

    public Paint getUnusedPaint() {
        Paint paint = unusedPaint.poll();
        if (paint == null) {
            paint = getNewPaint();
        }
        usedPaint.add(paint);
        return paint;

    }

    public void freePaint(Paint paint) {
        paint.setAlpha(255);
        unusedPaint.add(paint);
        usedPaint.remove(paint);

    }

    private Paint getNewPaint() {

        if (unusedPaint.size() + usedPaint.size() >= 15)
            throw new IllegalStateException("Initiallized more than 10 paints");

        return new Paint();

    }
}
