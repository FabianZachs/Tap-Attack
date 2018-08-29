package com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class RecycledRect {

    private ArrayBlockingQueue<Rect> unusedRect;
    private ArrayList<Rect> usedRect;

    public RecycledRect() {
        unusedRect = new ArrayBlockingQueue<>(10);
        usedRect = new ArrayList<>();

    }

    public Rect getUnusedRect() {
        Rect rect = unusedRect.poll();
        if (rect == null) {
            rect = getNewRect();
        }
        usedRect.add(rect);
        return rect;

    }

    public void freePaint(Rect rect) {
        unusedRect.add(rect);
        usedRect.remove(rect);

    }

    private Rect getNewRect() {

        if (unusedRect.size() + usedRect.size() >= 10)
            throw new IllegalStateException("Initiallized more than 10 rects");

        return new Rect();

    }

}
