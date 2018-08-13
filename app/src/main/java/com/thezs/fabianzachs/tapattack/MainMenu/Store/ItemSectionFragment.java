package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 12/08/18.
 */

public abstract class ItemSectionFragment extends Fragment {

    protected SharedPreferences prefs;
    protected MyDBHandler dbHandler;
    public ItemSectionListener listener;
    private GridView gridView;
    private CustomAdapter adapter;

    protected String DEFAULT_SECTION_VALUE;
    protected String SECTION;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpPrefsAndDatabase();
    }

    public void setUpPrefsAndDatabase() {
        dbHandler = new MyDBHandler(getActivity(), null, null, 1);
        prefs = getActivity().getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
    }



    protected void setDisplayedItemFromThisSection(/*String section, String defaultValue*/) {
        /*
        String[] names = dbHandler.getItemNamesFromCategory(section);
        int initiallySelectedPosition = helper.getIndexOf(names, prefs.getString(section, defaultValue));
        int resourceID = helper.getResourceId(getContext(), adapter.getItem(initiallySelectedPosition).get_file());
        listener.selectedItemChanged(getResources().getDrawable(resourceID), adapter.getItem(initiallySelectedPosition).get_unlocked());
        */
        String[] names = dbHandler.getItemNamesFromCategory(SECTION);
        int initiallySelectedPosition = helper.getIndexOf(names, prefs.getString(SECTION, DEFAULT_SECTION_VALUE));
        int resourceID = helper.getResourceId(getContext(), adapter.getItem(initiallySelectedPosition).get_file());
        listener.selectedItemChanged(getResources().getDrawable(resourceID), adapter.getItem(initiallySelectedPosition).get_unlocked());
        //adapter.setSelectedItemPosition(initiallySelectedPosition);
        //adapter.notifyDataSetChanged();
    }

    protected void setListener(Context context) {
        if (getParentFragment() instanceof ItemSectionListener) {
            listener = (ItemSectionListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement GamemodeSectionFragment.GameModeSectionFragmentListener");
        }
    }

    public void setupItemGrid(View view, final String section, String defaultValue) {
        gridView = (GridView) view.findViewById(R.id.gridview);
        adapter = new CustomAdapter(getActivity().getApplicationContext(), dbHandler, section, getCurrentSelectedItem(section, defaultValue));
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
                prefsEditor.putString(section, myAdapter.getItem(position).get_name());
                prefsEditor.apply();
                //}
            }
        });
    }

    protected int getCurrentSelectedItem(String section, String defaultValue) {
        String[] names = dbHandler.getItemNamesFromCategory(section);
        return helper.getIndexOf(names, prefs.getString(section, defaultValue));
    }


    protected void setDEFAULT_SECTION_VALUE(String DEFAULT_SECTION_VALUE) {
        this.DEFAULT_SECTION_VALUE = DEFAULT_SECTION_VALUE;
    }

    public String getDEFAULT_SECTION_VALUE() {
        return DEFAULT_SECTION_VALUE;
    }

    protected void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }

    public String getSECTION() {
        return SECTION;
    }
}
