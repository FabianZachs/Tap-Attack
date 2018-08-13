package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends ItemSectionFragment {

    //private MyDBHandler dbHandler;
    //private GameModeSectionFragmentListener listener;
    //private SharedPreferences prefs;
    //private GridView gridView;
    //private CustomAdapter adapter;
    private String SECTION = "game mode";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);

        super.setupItemGrid(view, SECTION);
        super.setInitialSelectedItem(SECTION, Constants.GAMEMODES[0]);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.setListener(context);
        /*
        if (getParentFragment() instanceof GameModeSectionFragmentListener) {
            listener = (GameModeSectionFragmentListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement GamemodeSectionFragment.GameModeSectionFragmentListener");
        }*/
    }

    /*
    public void setupItemGrid(View view) {
        gridView = (GridView) view.findViewById(R.id.gridview);
        adapter = new CustomAdapter( getActivity().getApplicationContext(), dbHandler, "game mode");
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                CustomAdapter myAdapter = (CustomAdapter) parent.getAdapter();


                myAdapter.setSelectedItemPosition(position);
                myAdapter.notifyDataSetChanged();
                listener.selectedItemChanged(((ImageView) (view.findViewById(R.id.item_image))).getDrawable(), adapter.getItem(position).get_unlocked());

                //if (dbHandler.isItemUnlocked(names[position]) == 1) {
                    //StyleableToast.makeText(mainMenuActivity,  "unlocked", R.style.successtoast).show();
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString("gamemode", myAdapter.getItem(position).get_name());
                    prefsEditor.apply();
                //}
            }
        });
    }*/

    /*
    public interface GameModeSectionFragmentListener extends ItemSectionListener {
        void selectedItemChanged(Drawable itemImage);
    }*/

}
