package com.thezs.fabianzachs.tapattack.Game;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.SurfaceView;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;

        import com.thezs.fabianzachs.tapattack.R;
        import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class MainGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);

        setContentView(R.layout.activity_main_game);

        // TODO make a class for this to call from all over code
        RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);
        parentLayout.setBackground(getResources().getDrawable(R.drawable.blueneonbackground));


        LinearLayout viewForGamePanel = (LinearLayout) findViewById(R.id.game_panel_surface);
        viewForGamePanel.addView(new GamePanel(this));

        // below works for setting entire screen the view
        // setContentView(new GamePanel(this));

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void pauseClick(View view) {
        finish();
    }
}
