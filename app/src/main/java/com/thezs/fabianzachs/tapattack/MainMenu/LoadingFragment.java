package com.thezs.fabianzachs.tapattack.MainMenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 09/08/18.
 */

public class LoadingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.loading_fragment, container, false);

        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).setViewPager(1);
            }
        });

        return view;
    }

}
