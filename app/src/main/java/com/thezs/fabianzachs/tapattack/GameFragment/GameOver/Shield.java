package com.thezs.fabianzachs.tapattack.GameFragment.GameOver;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

import java.util.concurrent.CopyOnWriteArrayList;

public class Shield {

    public boolean executable;
    private Mediator mediator;
    private View view;

    public Shield(Mediator mediator, View view) {
        this.mediator = mediator;
        this.view = view;
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        executable = prefs.getBoolean("shieldEnabled", true);
        updateShieldUILayout();
        updateShieldUIColor();
    }



    public void execute(CopyOnWriteArrayList<ShapeObject> shapes) {
        executable = false;
        turnShapesIntoStars(shapes);
        updateShieldUIColor();

        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        prefs.edit().putInt("shields", prefs.getInt("shields",0)-1).apply();
    }

    private void turnShapesIntoStars(CopyOnWriteArrayList<ShapeObject> shapes) {
        NormalShapeBuilder shapeBuilder = new NormalShapeBuilder(mediator);
        for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++) {
            ShapeObject star = shapeBuilder.buildShape("star",
                    shapes.get(shapeIndex).getColor(), shapes.get(shapeIndex).getCenterLocation(),
                    shapes.get(shapeIndex).getPaint(), shapes.get(shapeIndex).getBitmapHolder(),
                    shapes.get(shapeIndex).getShapeRadius(),"up" );
            shapes.set(shapeIndex, star);
        }
    }

    private void updateShieldUILayout() {
        final ImageView bottom = view.findViewById(R.id.bottom_image);
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams
                (Constants.GAME_VIEW_WIDTH, 20);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottom.setLayoutParams(bottomParams);

    }

    private void updateShieldUIColor() {
        final ImageView bottom = view.findViewById(R.id.bottom_image);
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        int[] UIComponentColors = helper.getCurrentWarningColorHolderColors(prefs.getString(Constants.BACKGROUND_TAG, Constants.BACKGROUNDS[0]));
        if (executable) {
            ((GradientDrawable)bottom.getDrawable()).setColors(new int[] {UIComponentColors[0], Constants.CURRENT_CONTEXT.getResources().getColor(R.color.gameshieldcolor), UIComponentColors[0]});
        }
        else
            ((GradientDrawable)bottom.getDrawable()).setColors(new int[] {UIComponentColors[0], Constants.CURRENT_CONTEXT.getResources().getColor(R.color.gamenonshieldcolor), UIComponentColors[0]});
    }
}
