package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

import android.annotation.SuppressLint;
import android.content.Context;
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
 * Created by fabianzachs on 19/08/18.
 */

public class PaidUnlocksFragment extends Fragment {

    private PaidUnlocksListener listener;

    public interface PaidUnlocksListener {
        void paidUnlocksFragmentToMainMenuFragment();
        void paidUnlocksFragmentToPaidPointsFragment();
        void paidUnlocksFragmentToPaidShieldsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paid_menu, container, false);


        ImageView backbutton = view.findViewById(R.id.back_image);
        backbutton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backbutton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidUnlocksFragmentToMainMenuFragment();
            }
        }));

        ImageView pointsImage = view.findViewById(R.id.points_icon);
        pointsImage.setOnTouchListener(new ButtonOnTouchListener(getActivity(), pointsImage, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidUnlocksFragmentToPaidPointsFragment();
            }
        }));

        ImageView shieldsImage = view.findViewById(R.id.shields_icon);
        shieldsImage.setOnTouchListener(new ButtonOnTouchListener(getActivity(), shieldsImage, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidUnlocksFragmentToPaidShieldsFragment();
            }
        }));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PaidUnlocksListener) {
            listener = (PaidUnlocksListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement PaidUnlocksFragment.paidUnlocksFragmentToMainMenuFragment");
        }
    }
}
