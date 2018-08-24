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
import android.widget.ListView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public abstract class PaidItemType extends Fragment {

    protected ArrayList<PaidUnlockItem> items = new ArrayList<>();
    protected PaidUnlockAdapter adapter;
    private PaidItemTypeListener listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paid_items, container, false);
        adapter.setListener(new PaidUnlockAdapter.PaidUnlockAdapterListener() {
            @Override
            public void purchaseComplete() {
                listener.paidTypeFragmentToMainMenuFragment();
            }
        });

        ((ListView) view.findViewById(R.id.list_view)).setAdapter(adapter);

        ImageView backbutton = view.findViewById(R.id.back_image);
        backbutton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backbutton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.paidTypeFragmentToPaidUnlocksFragment();
            }
        }));


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PaidItemTypeListener) {
            listener = (PaidItemTypeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement PaidItemType.PaidItemTypeFragmentListener");
        }
    }


    public interface PaidItemTypeListener {
        void paidTypeFragmentToPaidUnlocksFragment();
        void paidTypeFragmentToMainMenuFragment();
    }
}
