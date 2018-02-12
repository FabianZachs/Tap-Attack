package com.thezs.fabianzachs.tapattack.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by fabianzachs on 11/02/18.
 */

// will play an animation
public class Animation {


    // time inbetween frames
    private float frameTime;
    private Bitmap[] frames;
    private int frameIndex;
    private long lastFrame;


    private boolean isPlaying = false;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void play() {
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stop() {
        isPlaying = false;
    }


    public Animation(Bitmap[] frames, float animTime) {
        // animTime is the speed

        this.frames = frames;
        frameIndex = 0;

        frameTime = animTime/frames.length;

        lastFrame = System.currentTimeMillis();
    }

    public void  draw(Canvas canvas, Rect destination) {
        // to draw bitmap, we need the image, a source rectangle (what portion of bitmap to draw)
        // destination rect draws image to fill the rectangle (scaling included)
        if (!isPlaying())
            return;

        // source rectangle is null -- we want to draw entire image
        canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());

    }


    public void update() {

        if (!isPlaying())
            return;

        // if its time to go to the next frame
        if (System.currentTimeMillis() - lastFrame > frameTime*1000) {
            frameIndex++;
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }
}
