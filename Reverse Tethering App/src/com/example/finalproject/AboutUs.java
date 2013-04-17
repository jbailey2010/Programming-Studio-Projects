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

/**
 * Very basic, just sets the view to be the about us page
 * @author Jeff
 *
 */
public class AboutUs extends Activity implements View.OnClickListener{
	Button devin;
	Button jeff;
	Button sid;
	final Context cont = this;
	/**
	 * Sets the view to be the about us information
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaboutus);
        devin = (Button)findViewById(R.id.devin);
        devin.setOnClickListener(this);
        jeff = (Button)findViewById(R.id.jeff);
        jeff.setOnClickListener(this);
        sid = (Button)findViewById(R.id.sid);
        sid.setOnClickListener(this);
    }

    /**
     * Handles the onclicks to make sure they open the right xml
     */
    @Override
    public void onClick(View v) 
    {
    	final Dialog dialog = new Dialog(cont);
    	if(v == devin)
    	{
    		devin(dialog);
        }
    	else if(v == jeff)
    	{
    		jeff(dialog);
        }
    	else if(v == sid)
    	{
    		sid(dialog);
        }
		Button dialogButton = (Button)dialog.findViewById(R.id.submit);
		dialogButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				dialog.dismiss();
	    	}	
		});
    	dialog.show();
    }
    
    public void devin(Dialog dialog)
    {
		dialog.setContentView(R.layout.devin);      	    	
    	
    }
    
    public void jeff(Dialog dialog)
    {
		dialog.setContentView(R.layout.jeff);      	    	
   	
    }
    
    public void sid(Dialog dialog)
    {
		dialog.setContentView(R.layout.sid);      	    	
   	
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
			case R.id.home:
		        Intent intent = new Intent(this, Home.class);
		        this.startActivity(intent);				
		        return true;
			case R.id.help:
		        Intent intentHelp = new Intent(this, Help.class);
		        this.startActivity(intentHelp);				
		        return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}