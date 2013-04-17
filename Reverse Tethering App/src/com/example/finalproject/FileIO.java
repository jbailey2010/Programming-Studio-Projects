/**
 * 
 */
package com.example.finalproject;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Devin
 *
 */
public class FileIO {
	
    /**
     * Function that creates the JSON request to write to the SDCard
     * @param url String that contains URL of request
     * @param fetchImg boolean true if fetch images and save to disk
     * @param fetchCSS boolean true if fetch CSS and save to disk
     * @param fetchJS boolean true if fetch JavaScript and save to disk
     * @return JSONArray object that contains the request
     * @throws JSONException When something goes wrong in the encoding, should NOT happen
     */
    public JSONObject createJSON(String url, boolean fetchImg, boolean fetchCSS, boolean fetchJS) throws JSONException
    {
    	Log.d("info", "Request: \nurl: " + url + " img: " + fetchImg + " css: " + fetchCSS + " js: " + fetchJS);
    	JSONObject requestObj = new JSONObject();
    	requestObj.put("url", url);
    	requestObj.put("img", fetchImg);
    	requestObj.put("css", fetchCSS);
    	requestObj.put("js", fetchJS);
    	return requestObj;
    }
}
