/**
 * 
 */
package com.example.finalproject;

/**
 * @author Devin
 * Class that stores all user settings, set by UI menu/home page
 */
public class Settings {
	private boolean fetchCSS;
	private boolean fetchJS;
	private boolean fetchIMG;
	
	public Settings(boolean fetchCSS, boolean fetchJS, boolean fetchIMG) {
		this.setCSS(fetchCSS);
		this.setIMG(fetchIMG);
		this.setJS(fetchJS);
	}

	/**
	 * @param fetchCSS the fetchCSS to set
	 */
	public void setCSS(boolean fetchCSS) {
		this.fetchCSS = fetchCSS;
	}

	/**
	 * @return the fetchCSS
	 */
	public boolean getCSS() {
		return fetchCSS;
	}

	/**
	 * @param fetchJS the fetchJS to set
	 */
	public void setJS(boolean fetchJS) {
		this.fetchJS = fetchJS;
	}

	/**
	 * @return the fetchJS
	 */
	public boolean getJS() {
		return fetchJS;
	}

	/**
	 * @param fetchIMG the fetchIMG to set
	 */
	public void setIMG(boolean fetchIMG) {
		this.fetchIMG = fetchIMG;
	}

	/**
	 * @return the fetchIMG
	 */
	public boolean getIMG() {
		return fetchIMG;
	}
}
