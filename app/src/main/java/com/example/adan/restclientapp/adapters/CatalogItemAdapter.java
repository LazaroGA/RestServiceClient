package com.example.adan.restclientapp.adapters;

/**
 * Created by Adan on 11/02/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adan.restclientapp.R;
import com.example.adan.restclientapp.models.CatalogItem;

import java.util.ArrayList;

public class CatalogItemAdapter extends ArrayAdapter<CatalogItem> {
    private static class ViewHolder {
        TextView identifier;
        TextView description;
    }

    public CatalogItemAdapter(Context context, ArrayList<CatalogItem> items) {
        super(context, R.layout.item_catalog, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CatalogItem catalogItem = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_catalog, parent, false);

            viewHolder.identifier = (TextView) convertView.findViewById(R.id.value_catalog_item_identifier);
            viewHolder.description = (TextView) convertView.findViewById(R.id.value_catalog_item_description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.identifier.setText(catalogItem.getIdentifier());
        viewHolder.description.setText(catalogItem.getDescription());

        return convertView;
    }
}