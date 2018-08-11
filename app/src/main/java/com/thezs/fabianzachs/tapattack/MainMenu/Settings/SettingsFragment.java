package com.thezs.fabianzachs.tapattack.MainMenu.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 11/08/18.
 */

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        final ImageView backButton= (ImageView) view.findViewById(R.id.back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(),backButton, "fragmentToMenu"));

        return view;
    }
}
