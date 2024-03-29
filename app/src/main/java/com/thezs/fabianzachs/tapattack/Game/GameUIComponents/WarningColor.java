package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

import java.util.ArrayList;
import java.util.Random;

import static com.google.android.gms.internal.zzahn.runOnUiThread;

/**
 * Created by fabianzachs on 05/03/18.
 */

// TODO fade in at a specific time using yoyo
    /*
        YoYo.with(Techniques.FadeIn)
                .duration(3500)
                .repeat(0)
                .playOn(warningComponent);
     */
public class WarningColor {

    private CentralGameCommunication mediator;
    private Handler mHandler = new Handler();
    private ImageView warningComponent;
    private GradientDrawable warningDrawable;
    private int colorIndex;

    private String[] strColors;
    private Integer[] intColors;

    public static boolean running = true;

    private Paint[] warningColorButtonPaints;
    private ArrayList<Integer> warningColorHistory;
    private int warningColorHistoryPointer;
    private int warningColorStreak;
    private Paint textPaint;


    public WarningColor(CentralGameCommunication mediator, String[] strColors, Integer[] intColors) {
        this.warningColorHistory = new ArrayList<>();
        this.warningColorHistoryPointer = 0;
        this.warningColorStreak = 0;
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        this.mediator = mediator;
        Paint leftPaint = new Paint();
        Paint rightPaint = new Paint();
        this.warningColorButtonPaints = new Paint[] {leftPaint, rightPaint};
        setAndRandomizeArrays(strColors,intColors);
        setNextColor();
        //resetColorHistory(); // todo do we uncomment this??


        this.textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(30);





        running = true;
        this.warningComponent= Constants.warningComponentImg;
        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(0)
                .playOn(warningComponent);

        // TODO make sure thread is disposed of properly at end
        Thread timer = new Thread() {
            public void run() {
                while (running) {
                    try {
                        Constants.GAME_ACTIVITY.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //if (shake == true)

                            }
                        });
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        Log.d("running", "run: error");
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();


    }

    public void addToWarningColorHistory(Integer color) {
        //warningColorHistoryPointer = warningColorHistory.size() == 0 ? 0 : warningColorHistoryPointer++;
        //this.warningColorHistory.add(warningColorHistoryPointer, color);
        this.warningColorHistory.add(color);
        //Log.d("warningstreak", "addToWarningColorHistory: " + warningColorHistory);
        //Log.d("warningstreak", "pointer: " + warningColorHistoryPointer);
    }

    public int findCurrentStreak(ShapeObject shape) {
        if ((warningColorHistory.size() != 2) || !shape.getColorInt().equals(getPreviousIntWarningColor())) { // == 1 if no warningcolor was tapped since last reset(), >2 if more than once warning color was tapped
            resetWarningStreak();
            resetColorHistory();
        }
        else {
            resetColorHistory();
            incStreak();
        }
        return warningColorStreak;
    }

    public void resetColorHistory() {
        //this.warningColorHistoryPointer = 0;
        Integer lastWarningColor = warningColorHistory.get(warningColorHistory.size() - 1);
        this.warningColorHistory.clear();
        warningColorHistory.add(lastWarningColor);
        //this.warningColorStreak = 0;
    }

    public Integer getPreviousIntWarningColor() {
        //Log.d("warningstreak", "pointer called: " + warningColorHistoryPointer);
        //return (warningColorHistoryPointer >= 1 ) ? warningColorHistory.get(warningColorHistoryPointer - 1) : null; // -2 since -1 gets current one since -0 is empty for now
        //return warningColorHistory.get(warningColorHistory.size() - 2);
        Integer previousWarningColor = (warningColorHistory.size() >= 2) ? warningColorHistory.get(warningColorHistory.size() - 2) : null;
        resetColorHistory();
        return previousWarningColor;
    }


    public int getStreak() {
        return this.warningColorStreak;
    }

    public void resetWarningStreak() {
        this.warningColorStreak = 0;
    }

    public void incStreak() {
        warningColorStreak++;
    }


    public void recieveTouch(MotionEvent event, String side) {
        // TODO handle scrolling
        if (event.getAction() == MotionEvent.ACTION_DOWN && side.equals("right"))
            setNextColor();
        else if (event.getAction() == MotionEvent.ACTION_DOWN && side.equals("left"))
            setPreviousColor();
    }


    public void setNextColor() {
        colorIndex++;
        colorIndex = colorIndex >= intColors.length ? 0 : colorIndex;
        warningDrawable.setColor(intColors[colorIndex]);
        updateAllWarningColorObservers(strColors[colorIndex]);
        addToWarningColorHistory(intColors[colorIndex]);
    }

    public void setPreviousColor() {
        colorIndex--;
        colorIndex = colorIndex < 0 ? intColors.length - 1 : colorIndex;
        warningDrawable.setColor(intColors[colorIndex]);
        updateAllWarningColorObservers(strColors[colorIndex]);
        addToWarningColorHistory(intColors[colorIndex]);
    }

    private void updateAllWarningColorObservers(String strColor) {
        this.mediator.warningColorChanged(strColor);

    }

    public Integer getNextIntColor() {
        //int nextIntColor = colorIndex + 1;
        //nextIntColor = nextIntColor >= intColors.length ? 0 : nextIntColor;
        int nextIntColor = colorIndex + 1 >= intColors.length ? 0 : colorIndex + 1;
        return intColors[nextIntColor];
    }

    public Integer getPreviousIntColor() {
        //int previousIntColor = colorIndex - 1;
        //previousIntColor = previousIntColor < 0 ? intColors.length - 1 : previousIntColor;
        int previousIntColor = colorIndex - 1 < 0 ? intColors.length - 1 : colorIndex - 1;
        return intColors[previousIntColor];
    }

    public String getCurrentStrColor() {
        return strColors[colorIndex];
    }

    public Integer getCurrentIntColor() {
        return intColors[colorIndex];
    }

    private void setAndRandomizeArrays(String[] strColors, Integer[] intColors){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<strColors.length; i++) {
            int randomPosition = rgen.nextInt(strColors.length);
            String strTemp = strColors[i];
            Integer intTemp = intColors[i];

            strColors[i] = strColors[randomPosition];
            intColors[i] = intColors[randomPosition];

            strColors[randomPosition] = strTemp;
            intColors[randomPosition] = intTemp;
        }
        this.strColors = strColors;
        this.intColors = intColors;
    }

    public void shake() {
        /*
        YoYo.with(Techniques.Shake)
                .duration(750)
                .repeat(0)
                .playOn(Constants.warningComponentImg);
        shake = true; */
/*
        Constants.CURRENT_CONTEXT.runOnUiThread(new Runnable() {
            public void run() {
                Log.d("shakecalled", "shake: called");
                YoYo.with(Techniques.Shake)
                        .duration(750)
                        .repeat(0)
                        .playOn(warningComponent);
            }
        });
        */


        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("shalking", "run: ");
                YoYo.with(Techniques.Shake)
                        .duration(750)
                        .repeat(0)
                        .playOn(warningComponent);
            }
        });

        /*
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Shake)
                        .duration(950)
                        .repeat(0)
                        .playOn(warningComponent);
            }
        });
        */
    }

    public void draw(Canvas canvas) {
        /*
        //warningColorButtonPaints[0].setColor(getPreviousIntColor());
        //warningColorButtonPaints[1].setColor(getNextIntColor());
        //canvas.drawRect(Constants.WARNING_COLOR_CLICK_AREA_LEFT, warningColorButtonPaints[0]);
        //canvas.drawRect(Constants.WARNING_COLOR_CLICK_AREA_RIGHT, warningColorButtonPaints[1]);
        //Paint leftPaint = new Paint();
        //leftPaint.setShader(new LinearGradient(0,0,0,Constants.WARNING_COLOR_CLICK_AREA_LEFT.bottom,Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        //canvas.drawRect(Constants.WARNING_COLOR_CLICK_AREA_LEFT,leftPaint);
        */

        // todo now sure if/ how this should be displayed
        // todo if streak >= 1 then display streak (WARNING COLOR STREAK: 1 +x points) ( : 2 speed reduced) ( : 3 stars)
        //canvas.drawText("WARNING COLOR STREAK: 1 +x points", 0, 100, textPaint);
    }

}
