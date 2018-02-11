package com.android.CellTypeOverride;

import java.lang.reflect.Method;

import android.content.Context;
import android.renderscript.Int2;
import android.util.Log;
import com.saurik.substrate.*;

public class Hook {
	
//	PHONE_TYPE_NONE = 0
//  PHONE_TYPE_GSM = 1 
//  PHONE_TYPE_CDMA = 2

//  NETWORK_TYPE_UNKNOWN = 0
//  NETWORK_TYPE_CDMA = 4
//  NETWORK_TYPE_GPRS = 1
//  NETWORK_TYPE_LTE = 0xd
//  NETWORK_TYPE_EDGE = 2

	private static int[][] _multi = new int[][]{
		  { 0, 0 }, // NONE
		  { 1, 2 }, // GSM
		  { 2, 4 }, // CDMA
		  { 0, 0xd }, // LTE
		};
	
	private static int typeChosen = 3;
		
	private static String _TAG = "OverrideCellType";
	   static void initialize() {

            MS.hookClassLoad("android.telephony.TelephonyManager", new MS.ClassLoadHook() {
                public void classLoaded(Class<?> _class) {
                	
                    hookMethod(_class, "getNetworkType", 
                    		new Class<?>[] {String.class}, 
                    		_multi[typeChosen][1]);
                    hookMethod(_class, "getPhoneType", 
                    		new Class<?>[] {android.net.Uri.class, Integer.TYPE}, 
                    		_multi[typeChosen][0]);
                    
                }
            });
            
        }

	protected static void hookMethod(Class<?> _class, String methodName,
									Class<?>[] params, final Object ret) {
        Method method;
        
        try {
            method = _class.getMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            method = null;
            Log.i(_TAG, "NoSuchMethod: " + methodName + " with " + 
            			params.length + " args" + ", Error: " + e);
	        for (int j = 0; j < params.length; j++) 
	        	Log.i(_TAG, "Arg "+ (j+1) +" type: " + params[j]);
        }
 
        if (method != null) {
        	Log.i(_TAG, "Hooking " + methodName + "() with " + params.length + " args");
            MS.hookMethod(_class, method,
    			new MS.MethodAlteration<Context, Object>() {
                public Object invoked(final Context hooked,
                		final Object... args) throws Throwable {
                			return ret;
                }
        		});
        }
	}
}
