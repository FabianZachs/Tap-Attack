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
        void paidUnlocksFragmentToPaidPowerupsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_more_points, container, false);
        View view = inflater.inflate(R.layout.fragment_more_points3, container, false);
        //ListView pointsListView = view.findViewById(R.id.points_list_view);

        /*
        PaidUnlockItem unlock1 = new PaidUnlockItem(1,3,R.drawable.shield1);
        PaidUnlockItem unlock2 = new PaidUnlockItem(2,6,R.drawable.shield2);
        PaidUnlockItem unlock3 = new PaidUnlockItem(3,10,R.drawable.shield3);
        PaidUnlockItem unlock4 = new PaidUnlockItem(4,20,R.drawable.shield4);
        PaidUnlockItem unlock5 = new PaidUnlockItem(5,50,R.drawable.shield5);

        ArrayList<PaidUnlockItem> paidUnlocks = new ArrayList<>();
        paidUnlocks.add(unlock1);
        paidUnlocks.add(unlock2);
        paidUnlocks.add(unlock3);
        paidUnlocks.add(unlock4);
        paidUnlocks.add(unlock5);
*/
        /*
        PaidUnlockItem unlock1 = new PaidUnlockItem(1,5,R.drawable.star1);
        PaidUnlockItem unlock2 = new PaidUnlockItem(2,10,R.drawable.star2);
        PaidUnlockItem unlock3 = new PaidUnlockItem(3,20,R.drawable.star3);
        PaidUnlockItem unlock4 = new PaidUnlockItem(4,50,R.drawable.star4);
        PaidUnlockItem unlock5 = new PaidUnlockItem(5,100,R.drawable.star5);

        ArrayList<PaidUnlockItem> paidUnlocks = new ArrayList<>();
        paidUnlocks.add(unlock1);
        paidUnlocks.add(unlock2);
        paidUnlocks.add(unlock3);
        paidUnlocks.add(unlock4);
        paidUnlocks.add(unlock5);
        PaidUnlockAdapter adapter = new PaidUnlockAdapter("points", getActivity(), R.layout.list_purchase_skeleton, paidUnlocks);
        pointsListView.setAdapter(adapter);
        */

        /*
        ImageView powerupsIcon = view.findViewById(R.id.power_ups_icon);
        powerupsIcon.setOnTouchListener(new ButtonOnTouchListener(getActivity(), powerupsIcon, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidUnlocksFragmentToMainMenuFragment();
            }
        }));
        */

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

        ImageView powerupsImage = view.findViewById(R.id.powerups_icon);
        powerupsImage.setOnTouchListener(new ButtonOnTouchListener(getActivity(), powerupsImage, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidUnlocksFragmentToPaidPowerupsFragment();
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
