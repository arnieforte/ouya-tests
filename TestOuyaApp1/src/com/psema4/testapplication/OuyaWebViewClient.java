package com.psema4.testapplication;

import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.view.KeyEvent;

public class OuyaWebViewClient extends WebViewClient {
	public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
		int keyCode = event.getKeyCode();
		boolean result = false;
		
		switch(keyCode) {
		case KeyEvent.KEYCODE_BUTTON_A:
			result = true;
			break;
		
		case KeyEvent.KEYCODE_BUTTON_X:
			result = true;
			break;
	
		case KeyEvent.KEYCODE_BUTTON_Y:
			result = true;
			break;
			
		case KeyEvent.KEYCODE_BUTTON_B:
			result = true;
			break;
		}
		
		return result;
	}
}
