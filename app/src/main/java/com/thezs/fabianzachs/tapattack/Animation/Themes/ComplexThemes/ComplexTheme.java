package com.thezs.fabianzachs.tapattack.Animation.Themes.ComplexThemes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemeObject;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 14/03/18.
 */

public abstract class ComplexTheme extends ThemeObject {

    // TODO use altering bitmap by pixels
    // todo basic code for altering specific pixels of bitmap
    public void multiShapesMessaroundDELETE() {

        /*
        ImageView item = (ImageView) findViewById(R.id.play_button);
        LayerDrawable layers = (LayerDrawable) item.getDrawable();
        Drawable shape1 = layers.getDrawable(0);
        Log.d("bounds", "multiShapesMessaroundDELETE: " + shape1.getBounds());
        shape1.setBounds(0,0,160,5000);
        Log.d("bounds", "multiShapesMessaroundDELETE: " + shape1.getBounds());
        ColorFilter filter = new PorterDuffColorFilter(0xff74AC23, PorterDuff.Mode.SRC_IN);
        shape1.setColorFilter(filter);
        */

        // EDIT BITMAP
        //ImageView img = (ImageView) findViewById(R.id.play_button);
        Bitmap myBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.neonthemetemplate);
        myBitmap= myBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int [] allpixels = new int [ myBitmap.getHeight()*myBitmap.getWidth()];

        myBitmap.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0,myBitmap.getWidth(),myBitmap.getHeight());

        for(int i =0; i<myBitmap.getHeight()*myBitmap.getWidth();i++){

            if( allpixels[i] == 0xff00ffff/*|| allpixels[i] == Color.BLUE || allpixels[i] == Color.GREEN*/)
                allpixels[i] = Color.BLACK;
        }

        myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        //img.setImageBitmap(myBitmap);



        // SAVE IMG TO USER to internal storate -- only app can access this

    }

    public Paint getShapePaint(Paint paint, String color) {
        return paint;
    }

}
