package com.example.finalproject;

import java.net.MalformedURLException;


import android.view.View;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import android.widget.Button;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

/**
 * By Android conventions, that this extend activity makes it the de facto 
 * main for the function
 * @author Jeff
 *
 */
public class Splash extends Activity {
	/** Called when the activity is first created. */
	protected boolean _active = true;
	protected int _splashTime = 3000; // time to display the splash screen in ms
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.splash);
	 
	    // thread for displaying the SplashScreen
	    Thread splashTread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                int waited = 0;
	                while(_active && (waited < _splashTime)) {
	                    sleep(100);
	                    if(_active) {
	                        waited += 100;
	                    }
	                }
	            } catch(InterruptedException e) {
	                // do nothing
	            } finally {
	                finish();
  	                 Intent myIntent = new Intent( Splash.this, Home.class);
    					

  	              startActivityForResult(myIntent, 0);
	              //  stop();
	            }
	        }
	    };
	    splashTread.start();
	}
}
