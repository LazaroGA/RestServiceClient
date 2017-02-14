package com.example.adan.restclientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adan.restclientapp.adapters.CatalogItemAdapter;
import com.example.adan.restclientapp.clients.CatalogItemRestClient;
import com.example.adan.restclientapp.models.CatalogItem;
import com.example.adan.restclientapp.models.Request.CatalogRequest;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private ListView catalogItemList;

    EditText param;
    Button buttonSend;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend =  (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                jsonString = getRequest();
                getCatalogByPost(jsonString);
            }
        });

    }

    private String getRequest()
    {
        param=(EditText) findViewById(R.id.editText);

        CatalogRequest request = new CatalogRequest();
        request.RequestedCatalogName = param.getText().toString();
        request.PagingAll = true;

        Gson gson = new Gson();
        String json = gson.toJson(request);

        return  json;
    }


    private void getCatalogByPost(String jsonString) {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        CatalogItemRestClient.post(MainActivity.this, "GetCatalog.php", headers.toArray(new Header[headers.size()]),
                null,
                jsonString,
                new JsonHttpResponseHandler() {
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

                    public void onFailure(int statusCode,
                                          cz.msebera.android.httpclient.Header[] headers,
                                          java.lang.String responseString,
                                          java.lang.Throwable throwable){

                        param.setText(statusCode + responseString);
                    }
                });
    }
}