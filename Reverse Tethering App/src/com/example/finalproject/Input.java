package com.example.finalproject;

import android.R.string;

/**
 * A class that should hold all of the input from the user
 * @author Jeff
 *
 */
public class Input 
{
	public boolean image;
	public boolean javascript;
	public boolean styling;
	public int maxSize;
	public String url;
	/**
	 * Takes the users input and makes a custom class out of it
	 * @param images
	 * @param js
	 * @param css
	 * @param size
	 */
	public Input(boolean images, boolean js, boolean css, int size, String userUrl) 
	{
	    image = images;
	    javascript = js;
	    styling = css;
	    maxSize = size;
	    url = userUrl;
	}
}
