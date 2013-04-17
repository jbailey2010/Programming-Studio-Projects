package com.example.finalproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * A basic class to set the connection before heading to home
 * @author Devin, Jeff
 *
 */
public class Connect extends Activity {
	
	final Context context = this;
	private String toastMsg;
	public static final int TIMEOUT = 15;
	Intent i = null;
	ServerSocket server = null;
	public static Connection conn;
	public static Dialog dialog;
	private Handler mHandler;
	private final int port = 38301;
	
	/**
	 * Does all of the non-existent work
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        ImageView icon = (ImageView)findViewById(R.id.icon);
        mHandler = new Handler();
        icon.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View view) 
            {
            	dialog = new Dialog(context);
    			dialog.setContentView(R.layout.loading);
       			dialog.show();
       			new Thread(initializeConnection).start();
       			Log.d("debug", "Thread launched");
            }
        });     
    }
    
    /**
     * Runnable thread that will initialize and 
     */
	private Runnable initializeConnection = new Thread() {
		/**
		 * 
		 */
		public void run() {
			Socket client = null;
			
			// initialize server socket
			try {
				Log.d("info", "About to listen for clients");
				server = new ServerSocket(port);
				server.setSoTimeout(TIMEOUT * 1000);

				// attempt to accept a connection
				client = server.accept();
				Scanner socketIn = new Scanner(client.getInputStream());
				PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
				
				//Create connection object to store input and output sockets
				conn = new Connection(socketIn, socketOut);
			} catch (SocketTimeoutException e) {
				// Hide connecting dialog 
				dialog.dismiss();
				// print out TIMEOUT
				toastMsg = "Connection has timed out! Please try again";
				mHandler.post(makeToast);
			} catch (IOException e) {
				Log.d("debug", e.getMessage());
			} finally {
				// close the server socket
				try {
					if (server != null)
					{
						server.close();
						Log.d("debug", "Closing server socket");
					}
				} catch (IOException e) {
					Log.d("debug", "Cannot close server socket" + e.getMessage());
				}
			}
			if (client != null) {
				Log.d("info", "Accepted Connection!");
				Log.d("debug", "client:" + client);
				// print out success
				Log.d("debug", "connected!");
				
				//CONDUCT HANDSHAKE WITH PYTHON HERE
				conn.getSocketOut().println("Connection Established");
				Log.d("debug", "msg sent to python");
				Log.d("debug", "socket next: " + conn.getSocketIn().hasNext());
				boolean temp = false;
				while (!temp)
				{
					if (conn.getSocketIn().hasNext())
					{
						String socketData = conn.getSocketIn().next();
						Log.d("debug", "msg received from python: " + socketData);
						temp = true;
					}				
				}

				// Pass connection into intent so that home can write request and wait for response
				Intent myIntent = new Intent(Connect.this, Home.class);
				Connect.this.startActivity(myIntent);
				/*String reply = "";
				while (socketIn.hasNext()) {
					socketData = socketIn.next();
					reply += socketData;

					//msgHandler.post(socketStatus);
					//connectionStatus = socketData;
					//msgHandler.post(showConnectionStatus);
					Log.d("debug", socketData);
				}*/
			}
		}
	};
	
	private Runnable makeToast = new Runnable() {
		public void run() {
			Toast.makeText(getBaseContext(), toastMsg,
					Toast.LENGTH_SHORT).show();
		}
	};
}