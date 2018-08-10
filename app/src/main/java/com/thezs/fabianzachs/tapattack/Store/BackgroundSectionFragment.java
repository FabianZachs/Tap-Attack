package com.thezs.fabianzachs.tapattack.Store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.CustomAdapter;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class BackgroundSectionFragment extends Fragment {

    private MyDBHandler dbHandler; // todo maybe instantiate once and pass to all store item fragments


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dbHandler = new MyDBHandler(getActivity(), null, null, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);

        setupItemGrid(view);

        return view;
    }

    public void setupItemGrid(View view) {
        GridView grid = (GridView) view.findViewById(R.id.gridview);
        CustomAdapter myAdapter = new CustomAdapter( getActivity().getApplicationContext(), dbHandler, "background");
        grid.setAdapter(myAdapter);
    }
}
