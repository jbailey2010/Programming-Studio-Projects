/**
 * 
 */
package com.example.finalproject;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Devin
 *
 */
public class Connection {
	private static Scanner socketIn;
	private static PrintWriter socketOut;
	
	public Connection(Scanner in, PrintWriter out) {
		socketIn = in;
		socketOut = out;
	}
	
	public Scanner getSocketIn() {
		return socketIn;
	}
	
	public PrintWriter getSocketOut() {
		return socketOut;
	}
	
}
