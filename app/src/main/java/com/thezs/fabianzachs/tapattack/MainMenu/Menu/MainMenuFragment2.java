package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class MainMenuFragment2 extends Fragment {

    private View view;
    private SharedPreferences prefs;
    private MyDBHandler myDBHandler;
    private PointsSection pointsSection;
    private PercentUnlockedSection percentUnlockedSection;
    private PlayGameSection playGameSection;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefs = getActivity().getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        this.myDBHandler = new MyDBHandler(getActivity(), null, null, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        this.pointsSection = new PointsSection(getActivity(),view, prefs);
        setupMorePointsSection();
        this.percentUnlockedSection = new PercentUnlockedSection(getActivity(), myDBHandler, view);
        this.playGameSection = new PlayGameSection(getActivity(),view, prefs, myDBHandler);

        return view;
    }

    private void setupMorePointsSection() {
        RelativeLayout pointsSection = view.findViewById(R.id.more_points_section);
        pointsSection.setOnTouchListener(new ButtonOnTouchListener(getActivity(), pointsSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                // todo launch morepoints fragment
            }
        }));
    }


}
