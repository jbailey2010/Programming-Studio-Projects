package com.example.finalproject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;

import android.view.View;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

import android.widget.EditText;
/**
 * A pretty basic class, just holds the helper functions that are 
 * called to verify a URL input
 * @author Jeff
 *
 */
public class WebVerify {

    
    /**
     * Simply validates the input url to make sure it's a real thing
     * @param url -- the inputted url
     */
    public int validateURL(String url) throws MalformedURLException, URISyntaxException
    {
    	int checker = this.extensionTest(url);
    	if(checker == 0)
    	{
    		if(!isValidUrlRE(url))
    		{
    			return -4;
    		}
    		/*int check = isValidUrl(url);
    		if(check == 1)
    		{
    			return -3;
    		}
    		if(check == -1)
    		{
    			return -5;
    		}*/
    		return 0;
    	}
    	return checker;
    }
    
    /**
     * A quick, basic hand-written test to make sure the basic stuff is taken care of
     * @param url -- the url string used originally
     * @return -- and int to flag what happened
     */
    public int extensionTest(String url)
    {
    	int netIndex = url.indexOf(".net");
    	int comIndex = url.indexOf(".com");
    	int orgIndex = url.indexOf(".org");
    	int govIndex = url.indexOf(".gov");
    	int eduIndex = url.indexOf(".edu");
    	int netCom = 0;
    	int orgGov = 0;
    	int orgGE = 0;
    	int all = 0;
    	if(netIndex == -1 && comIndex == -1)
    	{
    		netCom = -1;
    	}
    	if(orgIndex == -1 && govIndex == -1)
    	{
    		orgGov = -1;
    	}
    	if(orgGov == -1 && eduIndex == -1)
    	{
    		orgGE = -1;
    	}
    	if(orgGE == -1 && netCom == -1)
    	{
    		all = -1;
    	}
    	if(all == -1)
    	{
    		return -1;
    	}
    	if(url.indexOf(" ") != -1)
    	{
    		return -2;
    	}    	
    	return 0;
    }
    /**
     * Makes sure the url is formatted in a valid way before using it
     * @param url -- the user input string
     */
    public String urlCleanup(String url)
    {
    	String pre = "";
    	int flag = 0;
    	if(url.indexOf("http://") == -1 && url.indexOf("https://") == -1)
    	{
    		String preSec = "http://";
    		pre = pre+preSec;
    		flag = 1;
    	}
    	if(url.indexOf("www.") == -1 && flag == 1)
    	{
    		String preThir = "www.";
    		pre = pre + preThir;
    	}
    	pre = pre + url;
    	return pre;
    }
    
    /**
     * Augmented from week one, takes the url, makes sure it's
     * basically formatted correctly, then sees if it can connect to 
     * the web
     * @param attempt -- the url string attempted
     * @return -- whether or not it's a good url
     */
    public static int isValidUrl(String attempt)
    {
    	try 
    	{
    	    URL url = new URL(attempt);
    	    URLConnection conn = url.openConnection();
    	    conn.connect();
    	} 
    	catch (MalformedURLException e) 
    	{
    	    return 1;
    	} 
    	catch (IOException e) 
    	{
    	    return -1;
    	}    	
    	return 0;
    }
    
    /**
     * Validates the url as a whole SOURCE: ANDROID SOURCE CODE
     * @param url -- the input url
     * @return true if it's good, false if not
     */
    public static boolean isValidUrlRE(String url) 
    {
    	Pattern WEB  = Pattern.compile(
    	        new StringBuilder()
    	                .append("((?:(http|https|Http|Https|rtsp|Rtsp):")
    	                .append("\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)")
    	                        .append("\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_")
    	                        .append("\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?")
    	                        .append("((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+")   // named host
    	                        .append("(?:")   // plus top level domain
    	                        .append("(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])")
    	                        .append("|(?:biz|b[abdefghijmnorstvwyz])")
    	                        .append("|(?:cat|com|coop|c[acdfghiklmnoruvxyz])")
    	                        .append("|d[ejkmoz]")
    	                        .append("|(?:edu|e[cegrstu])")
    	                        .append("|f[ijkmor]")
    	                        .append("|(?:gov|g[abdefghilmnpqrstuwy])")
    	                        .append("|h[kmnrtu]")
    	                        .append("|(?:info|int|i[delmnoqrst])")
    	                        .append("|(?:jobs|j[emop])")
    	                        .append("|k[eghimnrwyz]")
    	                        .append("|l[abcikrstuvy]")
    	                        .append("|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])")
    	                        .append("|(?:name|net|n[acefgilopruz])")
    	                        .append("|(?:org|om)")
    	                        .append("|(?:pro|p[aefghklmnrstwy])")
    	                        .append("|qa")
    	                        .append("|r[eouw]")
    	                        .append("|s[abcdeghijklmnortuvyz]")
    	                        .append("|(?:tel|travel|t[cdfghjklmnoprtvwz])")
    	                        .append("|u[agkmsyz]")
    	                        .append("|v[aceginu]")
    	                        .append("|w[fs]")
    	                        .append("|y[etu]")
    	                        .append("|z[amw]))")
    	                        .append("|(?:(?:25[0-5]|2[0-4]") 
    	                        .append("[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]")
    	                        .append("|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]")
    	                        .append("[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}")
    	                        .append("|[1-9][0-9]|[0-9])))")
    	                        .append("(?:\\:\\d{1,5})?)")
    	                        .append("(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~")  
    	                        .append("\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?")
    	                        .append("(?:\\b|$)").toString()
    	                );
    	Matcher m = WEB.matcher(url);
    	return m.matches();
    	
    }
}
