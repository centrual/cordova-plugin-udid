package com.oguzozcan.udid;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import java.util.UUID;

public class Udid extends CordovaPlugin {
	public static final String TAG = "Udid";
	private static String strUdid = "";

    public Udid() {}

	public void initialize(CordovaInterface cordova, CordovaWebView webView)
	{
        super.initialize(cordova, webView);
        strUdid = getUniquePsuedoID();
    }

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
	{
        if ("get".equals(action))
		{
            JSONObject r = new JSONObject();
            r.put("udid", strUdid);
            callbackContext.success(r);
			return true;
        }

        return false;
    }

	public static String getUniquePsuedoID()
	{
		String m_szDevIDShort = "35" +	(Build.BOARD.length() % 10) +
										(Build.BRAND.length() % 10) +
										(Build.CPU_ABI.length() % 10) +
										(Build.DEVICE.length() % 10) +
										(Build.MANUFACTURER.length() % 10) +
										(Build.MODEL.length() % 10) +
										(Build.PRODUCT.length() % 10);

		String serial = null;

		try
		{
			serial = android.os.Build.class.getField("SERIAL").get(null).toString();
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		}
		catch (Exception exception)
		{
			serial = "serial";
		}

		return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
	}
}

// for utf8 => ğüşıöçĞÜŞİÖÇ