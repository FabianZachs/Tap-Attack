package com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 17/02/18.
 */

public class LayoutHeadings {

    private Context context;
    private TextView score;
    private TextView streak;
    private com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar;
    private RelativeLayout parentLayout;

    public LayoutHeadings(Context context, TextView score, TextView streak, com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar, RelativeLayout parentLayout) {
        this.context = context;
        this.score = score;
        this.streak = streak;
        this.progressBar = progressBar;
        this.parentLayout = parentLayout;

    }

    public void setParentLayoutBackground(Bitmap bitmap) {

        Drawable dr = new BitmapDrawable(context.getResources(), bitmap);
        parentLayout.setBackground(dr);

    }

}
