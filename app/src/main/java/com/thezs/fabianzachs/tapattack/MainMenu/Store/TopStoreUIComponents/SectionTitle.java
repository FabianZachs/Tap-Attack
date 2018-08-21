package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.view.View;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class SectionTitle {

    private TextView sectionText;
    private StoreFragment storeFragment;

    public SectionTitle(View view, StoreFragment storeFragment) {
        this.sectionText = view.findViewById(R.id.section_title_text);
        this.storeFragment =storeFragment;
    }

    public void updateSectionText() {
        sectionText.setText(storeFragment.getCurrentlyDisplayedItemFragmentTAG().toUpperCase());
    }
}
