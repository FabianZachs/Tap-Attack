package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PaidUnlockAdapter extends ArrayAdapter<PaidUnlock> {


    private Context context;
    private int resource;

    public PaidUnlockAdapter(@NonNull Context context, int resource, ArrayList<PaidUnlock> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int price = getItem(position).getPrice();
        int quantity = getItem(position).getQuantity();
        int drawableId = getItem(position).getDrawableId();


        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView itemImage = convertView.findViewById(R.id.item_image);
        TextView quantityText = convertView.findViewById(R.id.item_quantity);
        TextView itemPrice = convertView.findViewById(R.id.item_price);

        itemImage.setImageResource(drawableId);
        quantityText.setText(quantity+"");
        itemPrice.setText(price+"");




        return convertView;

        // todo do background depending on type

    }
}
