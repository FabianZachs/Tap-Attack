package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class ShapeBitmapManager {

    private Map<String, Bitmap> shapeBitmapMap = new HashMap<>();
    private String shapeType;

    public ShapeBitmapManager() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        shapeType = prefs.getString(Constants.SHAPE_TYPE_TAG, Constants.SHAPE_TYPES[0]);
        setupShapeBitmapMap();
    }

    public Bitmap getBitmap(String shape, boolean click) {
        if (click)
            return shapeBitmapMap.get(shapeType+shape+"click");
        else
            return shapeBitmapMap.get(shapeType+shape);
    }

    private void setupShapeBitmapMap() {
        for (String shape : Constants.SHAPES) {
            String bitmapName = shapeType+shape;
            int resID = Constants.CURRENT_CONTEXT.getResources().
                    getIdentifier(bitmapName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
            Bitmap img = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
            shapeBitmapMap.put(bitmapName, img);

            if (shape.equals("circle") || shape.equals("square")) {
                String bitmapClickName = shapeType+shape+"click";
                int resIDClick = Constants.CURRENT_CONTEXT.getResources().
                        getIdentifier(bitmapClickName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
                Bitmap imgClick = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resIDClick);
                shapeBitmapMap.put(bitmapClickName, imgClick);
            }

        }
    }
}
