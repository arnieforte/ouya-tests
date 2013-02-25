package com.psema4.testapplication;

import android.content.Context;
import android.widget.Toast;
import tv.ouya.console.api.*;

public class WebAppInterface {
    Context mContext;

    public int O_1 = 0;
    public int U_1 = 0;
    public int Y_1 = 0;
    public int A_1 = 0;
    public float LS_X_1;
    public float LS_Y_1;
    public float RS_X_1;
    public float RS_Y_1;
    public float L2_1;
    public float R2_1;
    
    public int O_2 = 0;
    public int U_2 = 0;
    public int Y_2 = 0;
    public int A_2 = 0;
    public float LS_X_2;
    public float LS_Y_2;
    public float RS_X_2;
    public float RS_Y_2;
    public float L2_2;
    public float R2_2;
    
    public int buttonState_1;
    public int buttonState_2;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }
    
    // @ JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    
    public String getButtonState(int controller) {
    	String result = "";
    	
    	switch(controller) {
    	case 1:
    		result = "" + buttonState_1;
    		break;
    		
    	case 2:
    		result = "" + buttonState_2;
    		break;
    	}
    	
    	return result;
    }
    
    public String getO(int controller) {
    	String result = "";
    	
    	switch(controller) {
    	case 1:
    		result = "" + O_1;
    		break;
    		
    	case 2:
    		result = "" + O_2;
    		break;
    	}
    	
    	return result;
    }

    public String getU(int controller) {
    	String result = "";
    	
    	switch(controller) {
    	case 1:
    		result = "" + U_1;
    		break;
    		
    	case 2:
    		result = "" + U_2;
    		break;
    	}
    	
    	return result;
    }
    
    public String getY(int controller) {
    	String result = "";
    	
    	switch(controller) {
    	case 1:
    		result = "" + Y_1;
    		break;
    		
    	case 2:
    		result = "" + Y_2;
    		break;
    	}
    	
    	return result;
    }
    
    public String getA(int controller) {
    	String result = "";
    	
    	switch(controller) {
    	case 1:
    		result = "" + A_1;
    		break;
    		
    	case 2:
    		result = "" + A_2;
    		break;
    	}
    	
    	return result;
    }

    public float getLS_X(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = LS_X_1;
    		break;
    		
    	case 2:
    		result = LS_X_2;
    		break;
    	}
    	
    	return result;
    }

    public float getLS_Y(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = LS_Y_1;
    		break;
    		
    	case 2:
    		result = LS_Y_2;
    		break;
    	}
    	
    	return result;
    }

    public float getRS_X(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = RS_X_1;
    		break;
    		
    	case 2:
    		result = RS_X_2;
    		break;
    	}
    	
    	return result;
    }

    public float getRS_Y(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = RS_Y_1;
    		break;
    		
    	case 2:
    		result = RS_Y_2;
    		break;
    	}
    	
    	return result;
    }
    
    public float getL2(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = L2_1;
    		break;
    		
    	case 2:
    		result = L2_2;
    		break;
    	}
    	
    	return result;
    }

    public float getR2(int controller) {
    	float result = 0;
    	
    	switch(controller) {
    	case 1:
    		result = R2_1;
    		break;
    		
    	case 2:
    		result = R2_2;
    		break;
    	}
    	
    	return result;
    }    
}
