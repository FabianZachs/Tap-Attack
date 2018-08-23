package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidUnlock;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidUnlockAdapter;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 19/08/18.
 */

public class MorePointsFragment extends Fragment {

    private morePointsListener listener;

    public interface morePointsListener {
        void morePointsFragmentToMainMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_more_points2, container, false);
        ListView listView = view.findViewById(R.id.list_view);

        PaidUnlock unlock1 = new PaidUnlock(1,3,R.drawable.bitcross);
        PaidUnlock unlock2 = new PaidUnlock(2,6,R.drawable.bitcross);
        PaidUnlock unlock3 = new PaidUnlock(3,10,R.drawable.bitcross);
        PaidUnlock unlock4 = new PaidUnlock(4,20,R.drawable.bitcross);
        PaidUnlock unlock5 = new PaidUnlock(5,50,R.drawable.bitcross);

        ArrayList<PaidUnlock> paidUnlocks = new ArrayList<>();
        paidUnlocks.add(unlock1);
        paidUnlocks.add(unlock2);
        paidUnlocks.add(unlock3);
        paidUnlocks.add(unlock4);
        paidUnlocks.add(unlock5);

        PaidUnlockAdapter adapter = new PaidUnlockAdapter(getActivity(), R.layout.list_purchase_skeleton, paidUnlocks);
        listView.setAdapter(adapter);

        /*
        ImageView powerupsIcon = view.findViewById(R.id.power_ups_icon);
        powerupsIcon.setOnTouchListener(new ButtonOnTouchListener(getActivity(), powerupsIcon, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.morePointsFragmentToMainMenuFragment();
            }
        }));
        */
        /*
        TextView backbutton = view.findViewById(R.id.more_points_title);
        backbutton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backbutton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.morePointsFragmentToMainMenuFragment();
            }
        }));
*/
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof morePointsListener) {
            listener = (morePointsListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MorePointsFragment.morePointsFragmentToMainMenuFragment");
        }
    }
}
