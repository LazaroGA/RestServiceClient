package com.example.adan.restclientapp.clients;

/**
 * Created by Adan on 11/02/2017.
 */


import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class CatalogItemRestClient {
    //private static final String BASE_URL = "http://192.168.1.108/restfulservice/";
    private static final String BASE_URL = "http://www.zarhofer.com/serviciocatalogos/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context,
                           String url,
                           Header[] headers,
                           RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    public static void post(Context context,
                            String url,
                            Header[] headers,
                            RequestParams params,
                            String jsonObj,
                            AsyncHttpResponseHandler responseHandler ) {

        StringEntity entity = null;
        try {
                entity = new StringEntity(jsonObj);
            } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}