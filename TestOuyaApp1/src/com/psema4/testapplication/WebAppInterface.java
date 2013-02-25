package com.psema4.testapplication;

import android.content.Context;
import android.widget.Toast;
import tv.ouya.console.api.*;

public class WebAppInterface {
    Context mContext;

    public int O = 0;
    public int U = 0;
    public int Y = 0;
    public int A = 0;
    public float LS_X;
    public float LS_Y;
    public float RS_X;
    public float RS_Y;
    public float L2;
    public float R2;
    
    public int buttonState;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }
    
    // @ JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    
    public String getButtonState() {
    	return "" + buttonState;
    }
    
    public String getO() {
    	//System.out.println("getO called, O:" + O);
    	return "" + O;
    }

    public String getU() {
    	//System.out.println("getU called, U:" + U);
    	return "" + U;
    }

    public String getY() {
    	//System.out.println("getY called, Y:" + Y);
    	return "" + Y;
    }
    
    public String getA() {
    	//System.out.println("getA called, A:" + A);
    	return "" + A;
    }

    public float getLS_X() {
    	return LS_X;
    }

    public float getLS_Y() {
    	return LS_Y;
    }

    public float getRS_X() {
    	return RS_X;
    }

    public float getRS_Y() {
    	return RS_Y;
    }
    
    public float getL2() {
    	return L2;
    }

    public float getR2() {
    	return R2;
    }
    
}
