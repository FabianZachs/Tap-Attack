package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class MainGameFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        ImageView warningColorChangeButtonLeft =  view.findViewById(R.id.warning_color_change_button_left);
        RelativeLayout.LayoutParams colorChangeParamsLeft = new RelativeLayout.LayoutParams(Constants.WARNING_COLOR_CLICK_AREA_LEFT.width(), Constants.WARNING_COLOR_CLICK_AREA_LEFT.height());
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        warningColorChangeButtonLeft.setLayoutParams(colorChangeParamsLeft);


        ImageView warningColorChangeButtonRight = view.findViewById(R.id.warning_color_change_button_right);
        RelativeLayout.LayoutParams colorChangeParamsRight = new RelativeLayout.LayoutParams(Constants.WARNING_COLOR_CLICK_AREA_RIGHT.width(), Constants.WARNING_COLOR_CLICK_AREA_RIGHT.height());
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        warningColorChangeButtonRight.setLayoutParams(colorChangeParamsRight);




        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
