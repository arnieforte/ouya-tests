package com.psema4.testapplication;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import tv.ouya.console.api.*;

public class MainActivity extends Activity {
	public WebView webView;
	public WebAppInterface webAppInterface;
	
	public String url = "http://projects.psema4.com/ouya/dc/index.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.webView = (WebView) findViewById(R.id.webView1);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setBackgroundColor(Color.BLACK);
		
		WebViewClient wvc = new OuyaWebViewClient();
		webView.setWebViewClient(wvc);
		
		WebSettings webViewSettings = webView.getSettings();
		webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setPluginsEnabled(true);
		webViewSettings.setBuiltInZoomControls(true);
		webViewSettings.setPluginState(PluginState.ON);

		// js-java bridge
		this.webAppInterface = new WebAppInterface(this);
		this.webView.addJavascriptInterface(webAppInterface, "xouya");
		
		this.webView.loadUrl(url);
		System.out.println("launched");
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean handled = false;
		
	    webAppInterface.O_1 = 0;
	    webAppInterface.U_1 = 0;
	    webAppInterface.Y_1 = 0;
	    webAppInterface.A_1 = 0;
	    
	    webAppInterface.O_2 = 0;
	    webAppInterface.U_2 = 0;
	    webAppInterface.Y_2 = 0;
	    webAppInterface.A_2 = 0;
		
		return handled || super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    boolean handled = false;
	    
	    int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
	    
	    webAppInterface.O_1 = 0;
	    webAppInterface.U_1 = 0;
	    webAppInterface.Y_1 = 0;
	    webAppInterface.A_1 = 0;
	    
	    webAppInterface.O_2 = 0;
	    webAppInterface.U_2 = 0;
	    webAppInterface.Y_2 = 0;
	    webAppInterface.A_2 = 0;
	    
	    switch(keyCode) {
        case OuyaController.BUTTON_O:
        	switch(player) {
        	case 0:
        		webAppInterface.O_1 = 1;
        		break;
        	case 1:
        		webAppInterface.O_2 = 1;
        		break;
        	}
            handled = true;
            break;

        case OuyaController.BUTTON_U:
        	switch(player) {
        	case 0:
        		webAppInterface.U_1 = 1;
        		break;
        	case 1:
        		webAppInterface.U_2 = 1;
        		break;
        	}
            handled = true;
            break;

        case OuyaController.BUTTON_Y:
        	switch(player) {
        	case 0:
        		webAppInterface.Y_1 = 1;
        		break;
        	case 1:
        		webAppInterface.Y_2 = 1;
        		break;
        	}
            handled = true;
            break;

	    case OuyaController.BUTTON_A:
        	switch(player) {
        	case 0:
        		webAppInterface.A_1 = 1;
        		break;
        	case 1:
        		webAppInterface.A_2 = 1;
        		break;
        	}
            handled = true;
            break;
	    }

	    return handled || super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onGenericMotionEvent(final MotionEvent event) {
	    int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
	    float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);
	    float LS_Y = event.getAxisValue(OuyaController.AXIS_LS_Y);
	    float RS_X = event.getAxisValue(OuyaController.AXIS_RS_X);
	    float RS_Y = event.getAxisValue(OuyaController.AXIS_RS_Y);
	    float L2   = event.getAxisValue(OuyaController.AXIS_L2);
	    float R2   = event.getAxisValue(OuyaController.AXIS_R2);
	    
	    switch(player) {
	    case 0:
		    webAppInterface.LS_X_1 = LS_X;
		    webAppInterface.LS_Y_1 = LS_Y;
		    webAppInterface.RS_X_1 = RS_X;
		    webAppInterface.RS_Y_1 = RS_Y;
		    webAppInterface.L2_1 = L2;
		    webAppInterface.R2_1 = R2;
	    	break;
	    	
	    case 1:
		    webAppInterface.LS_X_2 = LS_X;
		    webAppInterface.LS_Y_2 = LS_Y;
		    webAppInterface.RS_X_2 = RS_X;
		    webAppInterface.RS_Y_2 = RS_Y;
		    webAppInterface.L2_2 = L2;
		    webAppInterface.R2_2 = R2;
	    	break;
	    }
	    
	    return true;
	}

}
