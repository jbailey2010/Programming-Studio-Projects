package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * An extremely basic class that just loads the help xml file and displays it
 * @author Jeff
 *
 */
public class Help extends Activity implements View.OnClickListener{
	Button images;
	Button js;
	Button css;
	Button size;
	Button url;
	final Context cont = this;
	private Button button2;
	/**
	 * Set the view to be the xml file
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        images = (Button)findViewById(R.id.imagestutorial);
        images.setOnClickListener(this);
        js = (Button)findViewById(R.id.jstutorial);
        js.setOnClickListener(this);
        css = (Button)findViewById(R.id.csstutorial);
        css.setOnClickListener(this);
        size = (Button)findViewById(R.id.sizetutorial);
        size.setOnClickListener(this);
        url = (Button)findViewById(R.id.urltutorial);
        url.setOnClickListener(this);
    }
    
    /**
     * Handles the onclicks to make sure they open the right xml
     */
    @Override
    public void onClick(View v) 
    {
    	final Dialog dialog = new Dialog(cont);
    	if(v == images)
    	{
    		imagesHelp(dialog);
        }
    	else if(v == js)
    	{
    		jsHelp(dialog);
        }
    	else if(v == css)
    	{
    		cssHelp(dialog);
        }
    	else if(v == size)
    	{
    		sizeHelp(dialog);
        }
    	else if(v == url)
    	{
    		urlHelp(dialog);
    	}
		Button dialogButton = (Button)dialog.findViewById(R.id.submitButton);
		dialogButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				dialog.dismiss();
	    	}	
		});
    	dialog.show();
    }
    
    /**
     * Handles the request for images
     */
    public void imagesHelp(Dialog dialog)
    {
		dialog.setContentView(R.layout.images);      	    	
    }
    
    /**
     * Handles the request for javascript
     */
    public void jsHelp(Dialog dialog)
    {
		dialog.setContentView(R.layout.js);        	
    }
    
    /**
     * Handles the request for css help
     */
    public void cssHelp(Dialog dialog)
    {
		dialog.setContentView(R.layout.css);
    }
    
    /**
     * Handles the request for size
     */
    public void sizeHelp(Dialog dialog)
    {
		dialog.setContentView(R.layout.size);      	
    }
    
    /**
     * Handles the request for the url input help
     */
    public void urlHelp(Dialog dialog)
    {
		dialog.setContentView(R.layout.url);       	
    }

    
    /**
     * Another on create, but pops up the menu (inflates it)
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	/**
	 * Runs the on selection part of the menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case R.id.add:
		        Intent intent = new Intent(this, AboutUs.class);
		        this.startActivity(intent);				
		        return true;
			case R.id.home:
		        Intent intentHelp = new Intent(this, Home.class);
		        this.startActivity(intentHelp);				
		        return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
