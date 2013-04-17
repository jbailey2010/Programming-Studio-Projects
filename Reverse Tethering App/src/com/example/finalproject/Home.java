package com.example.finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar; 
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Button;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
/**
 * The home page class, it basically does the front end of the back end 
 * in terms of the input from the user
 * @author Jeff
 *
 */
public class Home extends Activity implements View.OnClickListener{
	CheckBox satView;
	final Context context = this;
	private Button button;
    TextView mProgressText;
    TextView mTrackingText;
    static int size = 0;
    private SharedPreferences prefs;
	private String toastMsg;
	public static final int TIMEOUT = 15;
	Intent i = null;
	ServerSocket server = null;
	public static Connection conn;
	public static Dialog dialog;
	public static Dialog myDialog;
	private Handler mHandler;
	private final int port = 38301;
	private static final String startWord = "M6y381kWHX7vdjK1F";
	private static final String endWord = "Y336wqu7bEBv9c05847";
	private static Input userInput;
	private static File respFile;
	private static String oldURL;
    Button btn;
    Button history;
    String[] historyArr;
	
	/**
	 * The function that runs upon creation and sets the 'view' of the app
	 * It then calls the next function, which gets the various data from 
	 * the view itself. This makes an actionlistener that gets the data
	 * from the text field and cleans it up, ultimately
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
            	myDialog = new Dialog(context);
    			myDialog.setContentView(R.layout.loading);
       			//myDialog.show();

       			Log.d("info", "dialog in connect onClick is called");
       			//Thread connect = new Thread(initializeConnection);
       			//connect.start();
       			Log.d("debug", "Thread launched");
       			/*try {
					connect.join();
					Log.d("debug", "Threads joined!");
				} catch (InterruptedException e) {
					Log.d("debug", "Thread did not join properly: " + e.getMessage());
					e.printStackTrace();
				}*/
				Log.d("info", "Connection fully established, moving to home");
       			//myDialog.dismiss();
				runHome();
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
				myDialog.dismiss();
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
				
				//Conduct handshake with Python
				conn.getSocketOut().println("Connection Established");
				Log.d("info", "msg sent to python");
				boolean temp = false;
				while (!temp)
				{
					if (conn.getSocketIn().hasNext())
					{
						Log.d("debug", "hasNext true in handshake");
						String socketData = conn.getSocketIn().nextLine();
						Log.d("info", "msg received from python: " + socketData);
						if (socketData.equals("ack"))
						{
							Log.d("info", "ack matched");
							temp = true;
						}
						else
						{
							Log.e("error", "ack received: " + socketData + " vs. ack");
							temp = true;
						}
					}				
				}
			}
		}
	};
	
	public void runHome()
	{
        setContentView(R.layout.activity_final__project);
        prefs= getSharedPreferences("FinalPrefs", 0);
        historyArr = new String[200];
        getPref();
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
        history = (Button)findViewById(R.id.button2);
        history.setOnClickListener(this);
        satView = (CheckBox)findViewById(R.id.checkBox5);
        satView.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
        	@Override
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
        	{
        		if(satView.isChecked())
        		{
        			final Dialog dialog = new Dialog(context);
        			dialog.setContentView(R.layout.styles);
        			Button dialogButton = (Button)dialog.findViewById(R.id.dialogButtonOK);
        			//set up dynamic progress bar based on radiobuttons
        			if(size != 0)
        			{
        				setSize(dialog);
        			}
        			// if button is clicked, close the custom dialog
        			dialogButton.setOnClickListener(new OnClickListener() 
        			{
        				@Override
        				public void onClick(View v) {
        		    		if(radioPressed(dialog))
        		    		{
        		    			dialog.dismiss();
        		    		}
        				}	
        			});
        			Log.d("debug", "Calling runHome dialog now");
           			dialog.show();        	
        		}
        	}
        });	
	}
	
	private Runnable makeToast = new Runnable() {
		public void run() {
			Toast.makeText(getBaseContext(), toastMsg,
					Toast.LENGTH_SHORT).show();
		}
	};   
    /**
     * On click of the button, it calls get text field, which then
     * calls the validation functions to make sure the input is valid and all
     * that. If so, it then cleans it up and makes sure it's a real url
     */
    public void onClick(View view)
    {
    	if(view == btn)
    	{
	    	try {
	        	storePref();
	        	getPref();
				getTextField();
				//makeSpinner();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				Log.d("debug", "Json exception: " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(view == history)
    	{
        	storePref();
        	getPref();
    		historyDialog();
    	}
    }
    
    public void makeSpinner()
    {
    	storePref();
    	getPref();
        setContentView(R.layout.hacky);
    }
    
	 /**
     * Gets the content from the textfield on click of the submit button
     * Once it's fetched, it calls the URL cleanup and validation functions
     * to make sure it's all hunky dory
     * @throws URISyntaxException 
     * @throws JSONException 
	 * @throws IOException 
     */
    @SuppressWarnings("deprecation")
	public void getTextField() throws URISyntaxException, JSONException, IOException
    {
    	WebVerify holder = new WebVerify();
    	EditText editText = (EditText)findViewById(R.id.editText1);
    	String editTextStr = editText.getText().toString();
    	int valid = holder.validateURL(editTextStr);
    	if(valid == 0)
    	{
    		CheckBox imagesCheck = (CheckBox)findViewById(R.id.checkBox1);
    		CheckBox jsCheck = (CheckBox)findViewById(R.id.checkBox3);
    		CheckBox cssCheck = (CheckBox)findViewById(R.id.checkBox2);
    		CheckBox webCheck = (CheckBox)findViewById(R.id.checkBox5);
    		boolean images = imagesCheck.isChecked();
    		boolean js = jsCheck.isChecked();
    		boolean css = cssCheck.isChecked();
    		boolean web = webCheck.isChecked();
    		userInput = new Input(images,js,css,size,editTextStr); 
    		String settingDisp = this.toastCaller(images, js, css, web, size);
    		addToArray(editTextStr);

    		storePref();
    	/*	
    		writeRequest();
    		getHTMLResponse();
    		
    		if (userInput.styling)
    		{
        		ExecutorService executor = Executors.newCachedThreadPool();
        		Callable<Object> task = new Callable<Object>() {
        		   public Object call() throws IOException {
        		      getCSSResponse();
        		      return null;
        		   }
        		};
        		Future<Object> future = executor.submit(task);
        		try {
        		   Object result = future.get(2, TimeUnit.SECONDS); 
        		} catch (TimeoutException ex) {
        		   // handle the timeout
        		} catch (InterruptedException e) {
        		   // handle the interrupts
        		} catch (ExecutionException e) {
        		   // handle other exceptions
        		} finally {
        		   future.cancel(true); // may or may not desire this
        		}    			
    		}

    		if (userInput.image)
    			getImgResponse();
			openBrowser();
			*/
			//Toast.makeText(this, settingDisp, Toast.LENGTH_LONG).show();    		
    	}
    	else
    	{
    		toastError(valid);
    	}
    }
    
    public void getHTMLResponse() throws IOException
    {
		boolean gotResponse = false;
		String currentResp = "";
		List<Character> totalResp = new ArrayList<Character>();
		FileOutputStream fos;
		fos = openFileOutput("response.html", Context.MODE_WORLD_READABLE);
		Queue<Character> cache = new LinkedList<Character>();
		char[] endComp = endWord.toCharArray();
		int index = 0;
		boolean passFound = false;
		
		while (conn.getSocketIn().hasNext())
		{
			//Log.d("debug", "before currentResp");
			currentResp = conn.getSocketIn().nextLine();
			//Log.d("debug", "before If");
			//fos.write(currentResp.getBytes());
			
			if (currentResp.contains(endWord))
			{
				Log.d("debug", "endWord found in currentResp, breaking loop");
				String[] tempArr = currentResp.split(endWord);
				fos.write(tempArr[0].getBytes());
				break;
			}
			else 
			{
				for (char curr : currentResp.toCharArray())
				{
					if (index >= endWord.length() - 1)
						passFound = true;
					else if (curr == endComp[index])  {
						//Log.d("debug", "matching char: " + curr + " index: " + index);
						index++;
					}
					else
						index = 0;
				}
				//Log.d("debug", "after for passfound: " + passFound);
			}
			
			if (passFound)
			{
				//Log.d("debug", "passFound true, breaking loop");
				String[] tempArr = currentResp.split(endWord);
				fos.write(tempArr[0].getBytes());
				break;
			}
			else
			{
				//Log.d("debug", "passFound false, continuing loop");
				fos.write(currentResp.getBytes());
			}
					
					/*if (cache.size() < 21)
					{
						cache.offer(curr);
						String cacheWord = "";
						for (int i = 0; i < cache.size(); i++)
						{
							cacheWord += ((LinkedList<Character>) cache).get(i);
						}
						if (cacheWord.contains(endWord))
						{
							String[] tempArr = currentResp.split(endWord);
							fos.write(tempArr[0].getBytes());
							break;	
						}
					}
					else
					{
						cache.poll();
						cache.offer(curr);
					}*/
			
			
		
			/*if (currentResp.contains("*"))  {
				Log.d("debug", "after if");
				break;
			}*/
			//String concatResp = cache + "" + currentResp;
			
			/*if (currentResp.contains(endWord))
			{
				//add stuff to file
				break;
			}
			else if (concatResp.contains(endWord))
			{
				//do stuff
				break;
			}
			else
			{
				if (currentResp.length() < 21 && cache.length() < 40)
					cache += currentResp;
				else if (currentResp.length() < 21)
					cache = currentResp;
				else
					cache = currentResp.substring(currentResp.length() - 21);
			}*/
			//Log.d("debug", "resp coming: " + currentResp.length());
			
	    	/*FileOutputStream fos;
			fos = openFileOutput("response.html", Context.MODE_WORLD_READABLE);
			fos.write(.getBytes());
			fos.close();*/
			
			/*if (currentResp.contains(endWord))
			{
				Log.d("debug", "End word found");
				if (currentResp.contains(startWord))
				{
					Log.d("debug", "Start word also found");
					String regexPattern = "^.*M6y381kWHX7vdjK1F(.*?)Y336wqu7bEBv9c05847.*$";
					Matcher matcher = Pattern.compile(regexPattern).matcher(currentResp);
					if (matcher.matches())
					{
						String msg = matcher.group(0);
						String response = matcher.group(1);
						Log.d("debug", "msg: " + msg);
					}
				}
			}*/
			//Log.d("info", "msg received from python so far: " + currentResp);
		}
		fos.close();
		Log.d("debug", "response received");
    }
    
    public void getCSSResponse() throws IOException
    {
    	Log.d("debug", "CSS Response starting");
    	boolean gotResponse = false;
		String currentResp = "";
		//List<Character> totalResp = new ArrayList<Character>();
		FileOutputStream fos;
		//fos = openFileOutput("response.html", Context.MODE_WORLD_READABLE);
		//Queue<Character> cache = new LinkedList<Character>();
		char[] endComp = endWord.toCharArray();
		int index = 0;
		boolean passFound = false;
		int counter = 0;
		fos = openFileOutput("css" + counter + ".css", Context.MODE_WORLD_READABLE);
		Log.d("debug", "before loop");
		while (conn.getSocketIn().hasNext())
		{
			Log.d("debug", "in loop");
			currentResp = conn.getSocketIn().nextLine();
			/*
			
			if (currentResp.contains(endWord))
			{
				Log.d("debug", "endWord found in currentResp, breaking loop");
				String[] tempArr = currentResp.split(endWord);
				fos.write(tempArr[0].getBytes());
				counter++;
				fos = openFileOutput("css" + counter + ".css", Context.MODE_WORLD_READABLE);
				if (currentResp.contains(endWord + "*"))
					return;

			}
			else 
			{
			*/
			
			boolean eof = false;
			
			for (char curr : currentResp.toCharArray())
			{
				if (eof && curr == '*') {
					//String[] tempArr = currentResp.split(endWord,1);
					//fos.write(tempArr[0].getBytes());
					fos.close();
					Log.d("debug", "returning out of cssresp");
					return;
				}
				else if (index >= (endWord.length() - 1))  {
					index = 0;
					eof = true;
					counter++;
					fos.close();
					fos = openFileOutput("css" + counter + ".css", Context.MODE_WORLD_READABLE);
				}
				else if (curr == endComp[index])  {
					Log.d("debug", "matching char: " + curr + " index: " + index);
					index++;
					eof = false;
				}
				else  {
					index = 0;	
					eof = false;
				}
					
				fos.write(curr);
			}
					
		}
		//fos.close();
		Log.d("debug", "response received");
    }
    
    public void getImgResponse() throws IOException
    {
    	Log.d("debug", "IMG Response starting");
    	boolean gotResponse = false;
		byte currentResp;
		//List<Character> totalResp = new ArrayList<Character>();
		FileOutputStream fos;
		//fos = openFileOutput("response.html", Context.MODE_WORLD_READABLE);
		//Queue<Character> cache = new LinkedList<Character>();
		byte[] endComp = endWord.getBytes();
		int index = 0;

		int counter = 0;
		Log.d("debug", "starting while in imgresp");
		while (conn.getSocketIn().hasNext())
		{
			fos = openFileOutput("img" + counter + ".jpg", Context.MODE_WORLD_READABLE);
			Log.d("debug", "in loop");
			currentResp = conn.getSocketIn().nextByte();

			boolean eof = false;
			
			if (eof && currentResp == '*') {
				//String[] tempArr = currentResp.split(endWord,1);
				//fos.write(tempArr[0].getBytes());
				fos.close();
				Log.d("debug", "returning out of imgresp");
				return;
			}
			else if (index >= (endWord.length() - 1))  {
				index = 0;
				eof = true;
				counter++;
				fos.close();
				fos = openFileOutput("img" + counter + ".jpg", Context.MODE_WORLD_READABLE);
			}
			else if (currentResp == endComp[index])  {
				Log.d("debug", "matching char: " + currentResp + " index: " + index);
				index++;
				eof = false;
			}
			else  {
				index = 0;	
				eof = false;
			}
				
			fos.write(currentResp);
					
		}
		//fos.close();
		Log.d("debug", "img response received");
    }
    
    public void writeRequest() throws JSONException
    {
    	FileIO ioObj = new FileIO();
    	JSONObject jsonRequest = ioObj.createJSON
    			(userInput.url, userInput.image, userInput.styling, userInput.javascript);
    	oldURL = userInput.url;
    	conn.getSocketOut().println(startWord + "" + jsonRequest.toString() + "" + endWord);
    	Log.d("info", "msg: " + jsonRequest.toString() + " sent.");	
    }
    
    /**
     * Function that opens the browser with the contents of the response.html file
     * on the SDCard
     */
    public void openBrowser() {
        //Create file pointer
    	Log.d("debug", this.getFilesDir().toString() + "/response.html");        
    	respFile = new File(this.getFilesDir() + "/","response.html");
    	
        //Create webview client to intercept clicks
        WebViewClient wvClient = new WebViewClient() {
        	// Override the URL clicks, receive new requests
        	@Override
            public boolean shouldOverrideUrlLoading(WebView  view, String  url){
        		if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".gif") || url.endsWith(".png")) {
        			Log.d("info", "Image request, skipping");
        			return false;
        		}
        		else {
        			if (!url.contains("www") && !url.contains("http"))
        			{
        				Log.d("debug", "Requested url relative: " + url + " oldURL: " + oldURL);
        				String strippedURL = url;
        				String assetsDir = "data/data/com.example.finalproject/files";
        				String filePath = "file:///";
        				if (strippedURL.contains(filePath))
        				{
        					strippedURL = strippedURL.substring(filePath.length());
        					Log.d("debug", "stripped file:////, strippedURL: " + strippedURL);
        				}
        				if (strippedURL.contains(assetsDir))
        				{
        					strippedURL = strippedURL.substring(assetsDir.length());
        					Log.d("debug", "stripped file:////, strippedURL: " + strippedURL);
        				}
        				if (oldURL.lastIndexOf("/") < oldURL.indexOf("w"))
        					url = oldURL + "/" + strippedURL;
        				else
        					url = oldURL.substring(0, oldURL.lastIndexOf("/") + 1) + strippedURL;
        				Log.d("debug", "new relative url: " + url);
        			}
        			Log.d("info", "Click intercepted, URL: " + url);
        			userInput.url = url;
                    try {
						writeRequest();
						getHTMLResponse();
						if (userInput.styling)
							getCSSResponse();
				        view.loadUrl("file:///" + respFile.getAbsolutePath());
						Log.d("debug", "Response written to: " + respFile.getAbsolutePath());
					} catch (JSONException e) {
						Log.d("error", "writeRequest threw JSON Exception" + e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			return true;
        		}
            }
        };
        
        //Create webview and load file from SDCard
        WebView webview = new WebView(this);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(wvClient);
        
        webview.requestFocus(View.FOCUS_DOWN);
        
        setContentView(webview);
        webview.loadUrl("file:///" + respFile.getAbsolutePath());
        Log.d("info", "Browser loaded");
        Log.d("debug", "MYFILE: " + respFile.getAbsolutePath());
    }
    
    /**
     * Gets the preferences upon opening the app
     */
    public void getPref()
    {
    	prefs = getSharedPreferences("FinalPrefs", 0); 
		CheckBox imagesCheck = (CheckBox)findViewById(R.id.checkBox1);
		CheckBox jsCheck = (CheckBox)findViewById(R.id.checkBox3);
		CheckBox cssCheck = (CheckBox)findViewById(R.id.checkBox2);
		CheckBox webCheck = (CheckBox)findViewById(R.id.checkBox5);
    	EditText editText = (EditText)findViewById(R.id.editText1);
    	imagesCheck.setChecked(prefs.getBoolean("1", false));
    	jsCheck.setChecked(prefs.getBoolean("2", false));
    	cssCheck.setChecked(prefs.getBoolean("3", false));
    	webCheck.setChecked(prefs.getBoolean("4", false));
    	size = prefs.getInt("5", 0);
    	editText.setText(prefs.getString("6", "Enter URL Here"));
    	String currentString = prefs.getString("7", "No History");
    	if(currentString != "No History")
    	{
    		historyArr = currentString.split(",");
    	}
    }
 
    /**
     * Stores the preferences upon closing the app
     */
	public void storePref()
    {
    	SharedPreferences.Editor editor = getSharedPreferences("FinalPrefs", 0).edit();
		CheckBox imagesCheck = (CheckBox)findViewById(R.id.checkBox1);
		CheckBox jsCheck = (CheckBox)findViewById(R.id.checkBox3);
		CheckBox cssCheck = (CheckBox)findViewById(R.id.checkBox2);
		CheckBox webCheck = (CheckBox)findViewById(R.id.checkBox5);
		boolean images = imagesCheck.isChecked();
		boolean js = jsCheck.isChecked();
		boolean css = cssCheck.isChecked();
		boolean web = webCheck.isChecked();
    	EditText editText = (EditText)findViewById(R.id.editText1);
    	String editTextStr = editText.getText().toString();
    	editor.putBoolean("1", images);
    	editor.putBoolean("2", js);
    	editor.putBoolean("3", css);
    	editor.putBoolean("4", web);
    	editor.putInt("5", size);
    	editor.putString("6", editTextStr);
    	String history = "";
    	for(int i = 0; i < historyArr.length; i++)
    	{
    		if(i != 0)
    		{
    			history = history + ",";
    		}
    		history = history + historyArr[i];
    	}
    	editor.putString("7", history);
    	editor.commit();
    }




    
	/**
	 * Dynamically sets the size of the bar upon opening it
	 * @param dialog
	 */
	public void setSize(Dialog dialog)
	{
		final SeekBar r1 = (SeekBar)dialog.findViewById(R.id.seekBar1);
    	final TextView sizeSet = (TextView)dialog.findViewById(R.id.textView1);
    	r1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    	    @Override
    	    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
    	    	int hold = r1.getProgress();
    	    	size = hold * 100;
    	    	String size = Integer.toString(hold * 100) + " KB";
    	    	sizeSet.setText(size);
    	    }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
    	});
	}


 

    /**
     * Adds to the history array as needed
     */
    public void addToArray(String editTextStr)
    {
    	for(int i = 0; i < historyArr.length; i++)
    	{
    		if(historyArr[i] == editTextStr)
    		{
    			return;
    		}
    	}
    	String[] temp = historyArr;
    	historyArr = new String[temp.length + 1];
    	historyArr[0] = editTextStr;
    	for(int i = 0; i < temp.length; i++)
    	{
    		historyArr[i+1] = temp[i];
    	}
    }
   
    /**
     * Handles the creation of the history dialog
     */
    public void historyDialog()
    {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.history);

		final Spinner histSet = (Spinner)dialog.findViewById(R.id.dismissHistory);
		List<String> intermediate = new ArrayList<String>();
		int counter = 0;
		for(int i = 0; i < historyArr.length ; i++)
		{
			if(historyArr[i].length() > 4)
			{
				intermediate.add(historyArr[i]);
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intermediate);
		histSet.setAdapter(adapter);
		Button dialogButton = (Button)dialog.findViewById(R.id.spinner1);

		dialogButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {

    			dialog.dismiss();
    			EditText urlHolder = (EditText)findViewById(R.id.editText1);
    			if((histSet.getSelectedItem().toString() != "No History"))
    			{
    				urlHolder.setText(histSet.getSelectedItem().toString());
    			}
			}
		});
		dialog.show();        	  	
    }

    /**
     * Just a function that makes sure one of the radio buttons is pressed
     * @return
     */
    public static boolean radioPressed(Dialog dialog)
    {
    	if(size == 0)
    	{
    		return false;
    	}
    	return true;
    }

   

    
    /**
     * A super basic string concatenation function
     * @param a -- the first string
     * @param b -- the second string
     * @return -- the concatenated one
     */
    public static String concat(String a, String b)
    {
    	return a + b;
    }
    
    /**
     * A function that prints a specific message based on user input
     * @param images -- whether or not images was selected
     * @param js -- whether or not javascript was selected
     * @param css -- whether or not css was selected
     */
    public static String toastCaller(boolean images, boolean js, boolean css, boolean web, int size)
    {
    	String a = "Input Was A Valid URL, Fetching HTML";
    	if(images && !js && !css)
    	{
    		a = concat(a, " and Images");
    	}
    	else if(!images && js && !css)
    	{
    		a = concat(a, " and JavaScript");
    	}
    	else if(!images && !js && css)
    	{
    		a = concat(a, " and CSS");
    	}
    	else if(images && js && !css)
    	{
    		a = concat(a, ", Images, and JavaScript");
    	}
    	else if(images && !js && css)
    	{
    		a = concat(a, ", Images, and CSS");
    	}
    	else if(!images && js && css)
    	{
    		a = concat(a, ", JavaScript, and CSS");
    	}
    	else if(images && js && css)
    	{
    		a = concat(a, ", Images, JavaScript, and CSS");
    	}
    	if(web)
    	{
    		a = concat(a, ", With a Maximum Page Download Size of " + Integer.toString(size) + " kb");
    	}
    	if(!web)
    	{
    		a = concat(a, ", Without a Limit on Page Size");
    	}
    	//Toast.makeText(this, a, Toast.LENGTH_LONG).show();
    	return a;
    }

    /**
     * Simply  for the sake of breaking up code, this handles
     * the various errors that could be met 
     * @param valid
     */
    public void toastError(int valid)
    {
    	if(valid == -1)
    	{
			Toast.makeText(this, "Input Was Invalid, Invalid Extension, Please Try Again", Toast.LENGTH_LONG).show();
    	}
    	else if(valid == -2)
    	{
    		Toast.makeText(this, "Input Was Invalid, Use of Space Key, Please Try Again", Toast.LENGTH_LONG).show();
    	}
    	else if(valid == -3)
    	{
    		Toast.makeText(this, "Input Was Invalid, Not A Real Site, Please Try Again", Toast.LENGTH_LONG).show();
    	}
    	else if(valid == -4)
    	{
    		Toast.makeText(this, "Input Was Invalid, Use of Space Key, Please Try Again", Toast.LENGTH_LONG).show();
    	}
    	else if(valid == -5)
    	{
    		Toast.makeText(this, "Input Was Invalid, IO Exception, Please Try Again", Toast.LENGTH_LONG).show();   		
    	}
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
			case R.id.help:
		        Intent intentHelp = new Intent(this, Help.class);
		        this.startActivity(intentHelp);				
		        return true;
			case R.id.home:
		        Intent intentHome = new Intent(this, Help.class);
		        this.startActivity(intentHome);				
		        return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
