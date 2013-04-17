package Tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.Mazes;
import Code.Position;
import Code.UserInterface;

public class TestUserInterface {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Tests to make sure algChoice works for A*, and, by proxy, Dijkstra
	 */
	@Test
	public void testAlgChoiceAStar() {
		System.out.println("Please type 1");
		int algChoice = UserInterface.algCommand();
		assertEquals(algChoice, 1, 0);
	}

	/**
	 * Tests to make sure heurChoice works as it should
	 */
	@Test
	public void testHeurChoice()
	{
		System.out.println("Please type 2");
		int heurChoice = UserInterface.heurCommand(1);
		assertEquals(heurChoice,2,0);
	}

	/**
	 * Tests to make sure the string file input works as it should
	 */
	@Test
	public void testName()
	{
		System.out.println("Please enter maze0.bmp");
		String fileName = UserInterface.fileCommand();
		assertEquals(fileName, "maze0.bmp");
	}
	
	/**
	 * Makes sure that the grayscale function works as it should
	 */
	@Test
	public void testGray()
	{
		System.out.println("Please enter 100");
		int grayChoice = UserInterface.getGrayScale();
		assertEquals(grayChoice, 100);
	}
	
	/**
	 * Tests to make sure the start/end inputs work as they should
	 */
	@Test
	public void testStartEnd()
	{
		Mazes tbsMaze = new Mazes();
		System.out.println("Please enter 0 -- 0 -- 319 -- 239");
		UserInterface.setStartEnd(tbsMaze);
		assertEquals(tbsMaze.beginning.xCoord, 0);
		assertEquals(tbsMaze.beginning.yCoord, 0);
		assertEquals(tbsMaze.end.xCoord, 319, 0);
		assertEquals(tbsMaze.end.yCoord, 239, 0);
	}
	
	/**
	 * Tests to make sure that the confirmation works
	 */
	@Test
	public void testConf()
	{
		UserInterface.confirmSolve();
	}
	
	/**
	 * Tests the system (ish)
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testSys() throws IOException
	{
		Mazes tbsMaze = new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.height = image.getHeight();
		tbsMaze.width = image.getWidth();
		tbsMaze.beginning = new Position(0,0);
		tbsMaze.end = new Position(319,239);
		tbsMaze.readImage(fileName, 127);
		File check = UserInterface.printMazes(tbsMaze, 1, 2, "maze0.bmp");
		assertTrue(check.exists());
	}
}
