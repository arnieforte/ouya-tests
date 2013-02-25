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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.webView = (WebView) findViewById(R.id.webView1);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setBackgroundColor(Color.BLACK);
		webView.setWebViewClient(new WebViewClient());
		
		WebSettings webViewSettings = webView.getSettings();
		webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setPluginsEnabled(true);
		webViewSettings.setBuiltInZoomControls(true);
		webViewSettings.setPluginState(PluginState.ON);

		// js-java bridge
		this.webAppInterface = new WebAppInterface(this);
		this.webView.addJavascriptInterface(webAppInterface, "xouya");
		
		this.webView.loadUrl("http://projects.psema4.com/ouya/dc/index.html");
		System.out.println("launched");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    boolean handled = false;
	    System.out.println("keyCode: " + keyCode);
	    
/*		
	    int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
	    System.out.println("player:" + player);
	    
	    webAppInterface.O = 0;
	    webAppInterface.U = 0;
	    webAppInterface.Y = 0;
	    webAppInterface.A = 0;
	    
	    switch(keyCode) {
        case OuyaController.BUTTON_O:
//        case KeyEvent.KEYCODE_BUTTON_A:
        	System.out.println("Got O");
        	webAppInterface.O = 1;
            handled = true;
            break;

        case OuyaController.BUTTON_U:
//        case KeyEvent.KEYCODE_BUTTON_X;
        	System.out.println("Got U");
        	webAppInterface.U = 1;
            handled = true;
            break;

        case OuyaController.BUTTON_Y:
//        case KeyEvent.KEYCODE_BUTTON_Y:
        	System.out.println("Got Y");
        	webAppInterface.Y = 1;
            handled = true;
            break;

	    case OuyaController.BUTTON_A:
//        case KeyEvent.KEYCODE_BUTTON_B:
        	System.out.println("Got O");
        	webAppInterface.A = 1;
            handled = true;
            break;
	    }
*/
	    return handled || super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onGenericMotionEvent(final MotionEvent event) {
	    //Get the player #
	    int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
	    
	    //Get all the axis for the event
	    float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);
	    float LS_Y = event.getAxisValue(OuyaController.AXIS_LS_Y);
	    float RS_X = event.getAxisValue(OuyaController.AXIS_RS_X);
	    float RS_Y = event.getAxisValue(OuyaController.AXIS_RS_Y);
	    float L2 = event.getAxisValue(OuyaController.AXIS_L2);
	    float R2 = event.getAxisValue(OuyaController.AXIS_R2);

	    //Do something with the input
	    updatePlayerInput(player, LS_X, LS_Y, RS_X, RS_Y, L2, R2);
	    
	    return true;
	}
	
	public void updatePlayerInput(int player, float LS_X, float LS_Y, float RS_X, float RS_Y, float L2, float R2) {
	    webAppInterface.LS_X = LS_X;
	    webAppInterface.LS_Y = LS_Y;
	    webAppInterface.RS_X = RS_X;
	    webAppInterface.RS_Y = RS_Y;
	    webAppInterface.L2 = L2;
	    webAppInterface.R2 = R2;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
