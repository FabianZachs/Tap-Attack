package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.view.View;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class SectionTitle {

    private TextView sectionText;
    private StoreFragment storeFragment;

    public SectionTitle(View view, StoreFragment storeFragment) {
        this.sectionText = view.findViewById(R.id.section_title_text);
        this.storeFragment =storeFragment;
        setSectionText();
    }

    public void setSectionText() {
        sectionText.setText(storeFragment.getCurrentlyDisplayedItemFragmentTAG().toUpperCase());
    }
}
