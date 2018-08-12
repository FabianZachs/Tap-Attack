package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends Fragment {

    private MyDBHandler dbHandler; // todo maybe instantiate once and pass to all store item fragments
    private GameModeSectionFragmentListener listener;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof GameModeSectionFragmentListener) {
            listener = (GameModeSectionFragmentListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement GamemodeSectionFragment.GameModeSectionFragmentListener");
        }
    }

    public void setupItemGrid(View view) {
        GridView grid = (GridView) view.findViewById(R.id.gridview);
        grid.setAdapter(new CustomAdapter( getActivity().getApplicationContext(), dbHandler, "game mode"));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                CustomAdapter myAdapter = (CustomAdapter) parent.getAdapter();


                myAdapter.setSelectedItemPosition(position);
                myAdapter.notifyDataSetChanged();
                listener.selectedItemChanged(((ImageView) (view.findViewById(R.id.item_image))).getDrawable());
            }
        });
    }

    public interface GameModeSectionFragmentListener {
        void selectedItemChanged(Drawable itemImage);
    }

}
