package com.example.adan.restclientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.adan.restclientapp.adapters.CatalogItemAdapter;
import com.example.adan.restclientapp.clients.CatalogItemRestClient;
import com.example.adan.restclientapp.models.CatalogItem;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends AppCompatActivity {

    private ListView catalogItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCatalog();
    }

    private void getCatalog() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        CatalogItemRestClient.get(MainActivity.this, "index.php", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<CatalogItem> catalogItemArray = new ArrayList<CatalogItem>();
                        CatalogItemAdapter catalogItemAdapter = new CatalogItemAdapter(MainActivity.this, catalogItemArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                catalogItemAdapter.add(new CatalogItem(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        catalogItemList = (ListView) findViewById(R.id.list_catalog_items);
                        catalogItemList.setAdapter(catalogItemAdapter);
                    }
                });
    }
}