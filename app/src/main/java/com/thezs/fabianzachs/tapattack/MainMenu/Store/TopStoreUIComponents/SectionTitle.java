package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.view.View;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class SectionTitle {

    private TextView sectionText;

    public SectionTitle(View view) {
        this.sectionText = view.findViewById(R.id.section_title_text);
    }

    public void setSectionText(String sectionName) {
        sectionText.setText(sectionName);
    }
}
