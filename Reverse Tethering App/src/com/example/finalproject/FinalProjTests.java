package com.example.finalproject;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


import org.junit.Test;

import android.app.Dialog;
import android.content.Context;
/**
 * A set of JUnit tests to work the back-end part
 * of this week's work
 * @author Jeff
 *
 */
public class FinalProjTests {

	/**
	 * The first in a series of tests to make sure bad URL's are 
	 * rejected as they should be
	 * This one is rejected for lack of a valid extension
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testBadURL1() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String badURL = "hello";
		int bad = holder.validateURL(badURL);
		assertFalse(bad == 0);
	}
	
	/**
	 * The second in a series of tests to make sure bad URL's are 
	 * rejected as they should be.
	 * This one should fail as there is a space
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testBadURL2() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String badURL = "hello world.com";
		int bad = holder.validateURL(badURL);
		assertFalse(bad == 0);
	}
	
	/**
	 * Tests to make sure that a good url is validated as it should be
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testGoodURL1() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "google.com";
		goodURL = holder.urlCleanup(goodURL);
		assertEquals("http://www.google.com", goodURL);
	}
	
	/**
	 * Tests to make sure that a good url is validated as it should be
	 * in a slightly different way
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testGoodURL2() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "http://google.com";
		goodURL = holder.urlCleanup(goodURL);
		assertEquals("http://google.com", goodURL);
	}

	/**
	 * Tests to make sure that a good url is validated as it should be
	 * This just makes sure that no unnecessary work is done
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testGoodURL3() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "http://www.google.com";
		goodURL = holder.urlCleanup(goodURL);
		assertEquals("http://www.google.com", goodURL);
	}
	
	/**
	 * Tests to make sure that an invalid URL is rejected as it should be
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testValidURL1() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "http://asfdjklwef.com/";
		int check = holder.isValidUrl(goodURL);
		assertFalse(check == 0);
	}

	
	/**
	 * Tests to make sure that a valid URL is
	 * accepted as it should be
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testValidURL2() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "http://www.google.com/";
		int check = holder.isValidUrl(goodURL);
		assertTrue(check == 0);
	}
	
	/**
	 * Runs a third test on the isValidUrl
	 * with a properly formatted url that does not exist 
	 * on the internet, and makes sure it returns false
	 */
	@Test
	public void testValidURL3()
	{
		WebVerify holder = new WebVerify();
		String validNotReal = "http://www.notarealsite.gov";
		int check = holder.isValidUrl(validNotReal);
		assertFalse(check == 0);
	}

	/**
	 * Tests to make sure that a valid URL is
	 * rejected as it should be through a system test
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testSystemInvalid() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String badURL = "hello world";
		int check = holder.validateURL(badURL);
		assertFalse(check == 0);
	}

	/**
	 * Tests to make sure that a valid URL is
	 * accepted as it should be via a system test
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testSystemValid() throws MalformedURLException, URISyntaxException 
	{
		WebVerify holder = new WebVerify();
		String goodURL = "http://www.google.com";
		int check = holder.validateURL(goodURL);
		assertEquals(check, 0);
	}
	
	/**
	 * Runs a series of tests on the newly separate 
	 * url cleanup function to make sure it is what it should be
	 */
	@Test
	public void testURLCleanuup()
	{
		String bad = "google.com";
		WebVerify holder = new WebVerify();
		String newStr = holder.urlCleanup(bad);
		assertTrue(newStr != bad);
		String good = "http://google.com";
		String noChange = holder.urlCleanup(good);
		assertTrue(noChange != good);
		String slight = "www.google.com";
		String minor = holder.urlCleanup(slight);
		assertTrue(minor != slight);
	}
	
	/**
	 * Tests the newly separated extension test function to make sure
	 * it fails with the correct flag when it should and passes when it should
	 */
	@Test
	public void testExtension()
	{
		WebVerify holder = new WebVerify();
		String good = "google.com";
		assertTrue(holder.extensionTest(good) == 0);
		String space = "hello there.com";
		assertTrue(holder.extensionTest(space) == -2);
		String noExt = "hello";
		assertTrue(holder.extensionTest(noExt) == -1);
	}
	
	
	/**
	 * Tests the regex validator to make sure it fails on strings,
	 * spaces, and invalid extensions, but passes on real formats, 
	 * noting that this does not validate connection!
	 */
	@Test
	public void testRegExLots()
	{
		WebVerify holder = new WebVerify();
		assertFalse(holder.isValidUrlRE("Hello"));
		assertFalse(holder.isValidUrlRE("Hello There"));
		assertTrue(holder.isValidUrlRE("http://google.com"));
		assertTrue(holder.isValidUrlRE("http://www.notarealurl.gov"));
		assertFalse(holder.isValidUrlRE("hellothere.notrealextension"));
		assertTrue(holder.isValidUrlRE("http://www.facebook.gov"));
		assertTrue(holder.isValidUrlRE("http://www.facebook.com/apicture.png"));
	}
	
	//////////////////////////Week 3 Tests Below Here///////////////////////////////////////////////////
	/**
	 * A copy of the concat function to eliminate class errors
	 * @param a
	 * @param b
	 * @return
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
    	//Settings temp = new Settings(images, js, css, size);
    	return a;	
    }
	/**
	 * Makes sure a basic example concatenates
	 */
	@Test
	public void testConcat1()
	{
		String a = "Hello ";
		String b = "World";
		String sol = "Hello World";
		String ans = concat(a, b);
		assertEquals(sol, ans);
	}
	
	/**
	 * Makes sure it handles empty strings correctly
	 */
	@Test
	public void testConcat2()
	{
		String a = "Hello";
		String b = "";
		String sol = "Hello";
		String ans = concat(a, b);
		assertEquals(sol, ans);
	}
	
	/**
	 * Makes sure it handles multi word strings
	 */
	@Test
	public void testConcat3()
	{
		String a = "Hello there";
		String b = " multi word test";
		String sol = "Hello there multi word test";
		String ans = concat(a, b);
		assertEquals(sol, ans);		
	}
	
	/**
	 * Total emptiness test
	 */
	@Test
	public void testConcat4()
	{
		String a = "";
		String b = "";
		String sol = "";
		String ans = concat(a, b);
		assertEquals(sol, ans);
	}

	/**
	 * Tests the call with all booleans set to false
	 */
	@Test
	public void testToast1()
	{
		String ret = toastCaller(false,false,false,false,0);
		String ans = "Input Was A Valid URL, Fetching HTML, Without a Limit on Page Size";
		assertEquals(ret, ans);
	}
	
	/**
	 * Tests the call with one boolean true
	 */
	@Test
	public void testToast2()
	{
		String ret = toastCaller(true,false,false,false,0);
		String ans = "Input Was A Valid URL, Fetching HTML and Images, Without a Limit on Page Size";
		assertEquals(ret, ans);
		ret = toastCaller(false, true,false,false,0);
		ans = "Input Was A Valid URL, Fetching HTML and JavaScript, Without a Limit on Page Size";
		assertEquals(ret, ans);
		ret = toastCaller(false,false,true,false,0);
		ans = "Input Was A Valid URL, Fetching HTML and CSS, Without a Limit on Page Size";
		assertEquals(ret, ans);
	}
	
	/**
	 * Tests the call with two true booleans
	 */
	@Test
	public void testToast3()
	{
		String ret = toastCaller(true,true,false,false,0);
		String ans = "Input Was A Valid URL, Fetching HTML, Images, and JavaScript, Without a Limit on Page Size";
		assertEquals(ret, ans);		
		ret = toastCaller(true,false,true,false,0);
		ans = "Input Was A Valid URL, Fetching HTML, Images, and CSS, Without a Limit on Page Size";
		assertEquals(ret, ans);
		ret = toastCaller(false,true,true,false,0);
		ans = "Input Was A Valid URL, Fetching HTML, JavaScript, and CSS, Without a Limit on Page Size";
		assertEquals(ret, ans);
	}
	
	/**
	 * Tests the call with all true content booleans, false web
	 */
	@Test
	public void testToast4()
	{
		String ret = toastCaller(true,true,true,false,0);
		String ans = "Input Was A Valid URL, Fetching HTML, Images, JavaScript, and CSS, Without a Limit on Page Size";
		assertEquals(ret, ans);			
	}
	
	/**
	 * Tests the call with all true booleans
	 */
	@Test
	public void testToast5()
	{
		String ret = toastCaller(true,true,true,true,300);
		String ans = "Input Was A Valid URL, Fetching HTML, Images, JavaScript, and CSS, With a Maximum Page Download Size of 300 kb";
		assertEquals(ret,ans);
	}

}
