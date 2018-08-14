package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

import java.util.Random;

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

    public void randomUnlock() {
        Log.d("randomunlock", "randomUnlock: ");
        if (SECTION.equals("game mode"))
            return;
            //StyleableToast.makeText(Constants.CURRENT_CONTEXT, "goooood", R.style.successtoast).show();
        String[] lockedItems = dbHandler.getListOfLockedItems(SECTION);
        if (lockedItems.length == 0) {
            // todo handle if all items are unlocked
            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "All Items Unlocked", R.style.successtoast).show();
            return;
        }

        Random random = new Random();
        String itemNameToUnlock = lockedItems.length > 1 ?  lockedItems[random.nextInt(lockedItems.length - 1) + 1] : lockedItems[0];
        dbHandler.unlockItemViaName(itemNameToUnlock);

        int positionToUnlock = adapter.getIndexOfItemWithName(itemNameToUnlock);

        gridView.smoothScrollToPosition(positionToUnlock);
        // todo have it tick select each item to the positionToUnlock
        adapter.setSelectedItemPosition(positionToUnlock);
        adapter.notifyDataSetChanged();

        // todo tell parent framgnet new item shown

        Drawable image = getContext().getResources().getDrawable(helper.getResourceId(getContext(), adapter.getItem(positionToUnlock).get_file()));
        listener.selectedItemChanged(image, adapter.getItem(positionToUnlock).get_name(), 1);
    }

    protected void setUpPrefsAndDatabase() {
        dbHandler = new MyDBHandler(getActivity(), null, null, 1);
        prefs = getActivity().getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
    }



    protected void notifyNewItemToDisplayFromThisSectionBecauseSectionChange(/*String section, String defaultValue*/) {
        Log.d("itemunlocked", "notifyNewItemToDisplayFromThisSectionBecauseSectionChange: " + adapter.getItem(getCurrentEquipedItemPosition()).get_name());
        int resourceIDOfItemImage = helper.getResourceId(getContext(), adapter.getItem(getCurrentEquipedItemPosition()).get_file());
        listener.selectedItemChanged(getResources().getDrawable(resourceIDOfItemImage), adapter.getItem(getCurrentEquipedItemPosition()).get_name(), adapter.getItem(getCurrentEquipedItemPosition()).get_unlocked());
    }

    protected void setListener(Context context) {
        if (getParentFragment() instanceof ItemSectionListener) {
            listener = (ItemSectionListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement GamemodeSectionFragment.GameModeSectionFragmentListener");
        }
    }

    public void setupItemGrid(View view/*, final String section, String defaultValue*/) {
        gridView = (GridView) view.findViewById(R.id.gridview);
        adapter = new CustomAdapter(getActivity().getApplicationContext(), dbHandler, SECTION, getCurrentEquipedItemPosition());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                CustomAdapter myAdapter = (CustomAdapter) parent.getAdapter();


                myAdapter.setSelectedItemPosition(position);
                myAdapter.notifyDataSetChanged();
                listener.selectedItemChanged(((ImageView) (view.findViewById(R.id.item_image))).getDrawable(), adapter.getItem(position).get_name(), adapter.getItem(position).get_unlocked());

                //if (dbHandler.isItemUnlocked(names[position]) == 1) {
                //StyleableToast.makeText(mainMenuActivity,  "unlocked", R.style.successtoast).show();
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString(SECTION, myAdapter.getItem(position).get_name());
                prefsEditor.apply();
                //}
            }
        });
    }

    protected int getCurrentEquipedItemPosition() {
        String[] names = dbHandler.getItemNamesFromCategory(SECTION);
        return helper.getIndexOf(names, prefs.getString(SECTION, DEFAULT_SECTION_VALUE));
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
