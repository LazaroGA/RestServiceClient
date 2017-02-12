package com.example.adan.restclientapp.models;

/**
 * Created by Adan on 11/02/2017.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class CatalogItem {

    private String Identifier;
    private String Description;

    public CatalogItem(JSONObject object) {
        try {
            this.Identifier = object.getString("Identifier");
            this.Description = object.getString("Description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CatalogItem(String Description) {
        this.Description = Description;
    }

    public String getIdentifier() {
        return this.Identifier;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String title) {
        this.Description = title;
    }

}