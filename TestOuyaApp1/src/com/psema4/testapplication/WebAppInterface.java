package com.psema4.testapplication;

import java.util.List;

import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;
import tv.ouya.console.api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WebAppInterface {
    Context mContext;
    WebView webView;
    List <XOuyaController> controllers;

    WebAppInterface(Context c, WebView wv, List <XOuyaController> xoc) {
        mContext = c;
        webView = wv;
        controllers = xoc;
    }
    
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
        
    public String getController(String player) {
    	int p = Integer.parseInt(player);
    	String cDataJSON = controllers.get(p).getJSON();
    	return cDataJSON;
    }
}
