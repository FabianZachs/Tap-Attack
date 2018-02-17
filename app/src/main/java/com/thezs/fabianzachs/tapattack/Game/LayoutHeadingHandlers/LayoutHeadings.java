package com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers;

import android.content.Context;
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

    public LayoutHeadings(Context context, TextView score, TextView streak, com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar, RelativeLayout parentLayout) {
        score.setText("WORKS");

        // TODO make a class for this to call from all over code
        parentLayout.setBackground(context.getResources().getDrawable(R.drawable.neongreenbackground));
    }

}
