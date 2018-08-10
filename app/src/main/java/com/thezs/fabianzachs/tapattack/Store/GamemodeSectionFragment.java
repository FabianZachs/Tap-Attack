package com.thezs.fabianzachs.tapattack.Store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);

        TextView txt = (TextView) view.findViewById(R.id.test_text);
        txt.setText("F");

        return view;
    }
}
