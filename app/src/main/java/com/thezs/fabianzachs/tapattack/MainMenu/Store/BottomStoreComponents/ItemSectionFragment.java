package com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents;

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
import com.thezs.fabianzachs.tapattack.Database.BasicStoreItem;
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
    protected GridView gridView;
    private CustomGridAdapter adapter;

    protected String DEFAULT_SECTION_VALUE;
    protected String SECTION;

    public BasicStoreItem getCurrentlySelectedItem() {
        return adapter.getItem(adapter.getSelectedItemPosition());
    }


    public interface ItemSectionListener {
        void selectedItemChanged(String section, Drawable itemImage, String itemTitle ,int unlocked);
        void itemUnlockComplete(String typeOfUnlock);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpPrefsAndDatabase();
    }

    public void purchaseUnlock() {
        String[] lockedItems = dbHandler.getItemNamesFromCategory(SECTION);
        unlockViaItemName(lockedItems[adapter.getSelectedItemPosition()], "purchase");
    }

    public void randomUnlock() {
        String[] lockedItems = dbHandler.getListOfLockedItems(SECTION);
        Random random = new Random();
        String itemNameToUnlock = lockedItems.length > 1 ?  lockedItems[random.nextInt(lockedItems.length - 1) + 1] : lockedItems[0];
        unlockViaItemName(itemNameToUnlock, "random");
    }

    private void unlockViaItemName(String itemNameToUnlock, String typeOfUnlock) {
        dbHandler.unlockItemViaName(itemNameToUnlock);

        int positionToUnlock = adapter.getIndexOfItemWithName(itemNameToUnlock);

        gridView.smoothScrollToPosition(positionToUnlock);
        // todo have it tick select each item to the positionToUnlock
        adapter.setSelectedItemPosition(positionToUnlock);
        adapter.notifyDataSetChanged();

        Drawable image = getContext().getResources().getDrawable(helper.getResourceId(getContext(), adapter.getItem(positionToUnlock).get_file()));
        listener.selectedItemChanged(SECTION, image, adapter.getItem(positionToUnlock).get_name(), 1);
        listener.itemUnlockComplete(typeOfUnlock);

    }




    protected void setUpPrefsAndDatabase() {
        dbHandler = new MyDBHandler(getActivity(), null, null, 1);
        prefs = getActivity().getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
    }



    public void notifyNewItemToDisplayFromThisSectionBecauseSectionChange(/*String section, String defaultValue*/) {
        int resourceIDOfItemImage = helper.getResourceId(getContext(), adapter.getItem(getCurrentEquipedItemPosition()).get_file());
        listener.selectedItemChanged(SECTION, getResources().getDrawable(resourceIDOfItemImage), adapter.getItem(getCurrentEquipedItemPosition()).get_name(), adapter.getItem(getCurrentEquipedItemPosition()).get_unlocked());
    }

    protected void setListener(Context context) {
        if (getParentFragment() instanceof ItemSectionListener) {
            listener = (ItemSectionListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ItemSectionFragment.");
        }
    }

    public void setupItemGrid(View view/*, final String section, String defaultValue*/) {
        gridView = (GridView) view.findViewById(R.id.gridview);
        adapter = new CustomGridAdapter(getActivity().getApplicationContext(), dbHandler, SECTION, getCurrentEquipedItemPosition());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                CustomGridAdapter myAdapter = (CustomGridAdapter) parent.getAdapter();


                myAdapter.setSelectedItemPosition(position);
                myAdapter.notifyDataSetChanged();
                listener.selectedItemChanged(SECTION,((ImageView) (view.findViewById(R.id.item_image))).getDrawable(), adapter.getItem(position).get_name(), adapter.getItem(position).get_unlocked());

            }
        });
    }

    public void updateGridView() {
        adapter.notifyDataSetChanged();
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

    public void setEqiupedItemToSelectedItem() {
        adapter.setSelectedItemPosition(getCurrentEquipedItemPosition());
    }
}
