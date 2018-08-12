package com.thezs.fabianzachs.tapattack.MainMenu.Settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 11/08/18.
 */

public class SettingsFragment extends Fragment {

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private SettingsListener settingsListener;


    @SuppressLint({"CommitPrefEdits", "ClickableViewAccessibility"})
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        prefsEditor = prefs.edit();


        final ImageView backButton= (ImageView) view.findViewById(R.id.back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                settingsListener.settingsFragmentToMenuFragment();

                if (prefs.getInt("music", 1) == 0)
                    settingsListener.removeMusicPlayer();
            }
        }));

        final ImageView musicButton = (ImageView) view.findViewById(R.id.music_button);
        if (prefs.getInt("music", 1) == 1)
            musicButton.setImageResource(R.drawable.onbutton);
        else
            musicButton.setImageResource(R.drawable.offbutton);

        musicButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), musicButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (prefs.getInt("music", 1) == 1) {
                    musicButton.setImageResource(R.drawable.offbutton);
                    prefsEditor.putInt("music", 0);
                    prefsEditor.apply();
                    settingsListener.musicOff();
                }
                else {
                    musicButton.setImageResource(R.drawable.onbutton);
                    prefsEditor.putInt("music", 1);
                    prefsEditor.apply();
                    settingsListener.musicOn();
                }
            }
        }));


        final ImageView fxButton = (ImageView) view.findViewById(R.id.soundeffects_button);
        if (prefs.getInt("fx", 1) == 1)
            fxButton.setImageResource(R.drawable.onbutton);
        else
            fxButton.setImageResource(R.drawable.offbutton);

        fxButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), fxButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (prefs.getInt("fx", 1) == 1) {
                    fxButton.setImageResource(R.drawable.offbutton);
                    prefsEditor.putInt("fx", 0);
                    prefsEditor.apply();
                }
                else {
                    fxButton.setImageResource(R.drawable.onbutton);
                    prefsEditor.putInt("fx", 1);
                    prefsEditor.apply();
                }
            }
        }));


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SettingsListener) {
            settingsListener = (SettingsListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public interface SettingsListener {
        void settingsFragmentToMenuFragment();
        void musicOff();
        void musicOn();
        void fxOff();
        void fxOn();
        void removeMusicPlayer();
    }
}
