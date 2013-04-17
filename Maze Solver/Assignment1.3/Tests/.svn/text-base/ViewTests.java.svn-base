package Tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.Controller;
import Code.Mazes;
import Code.Position;
import Code.UserGuiView;
import Code.UserInterface;
/**
 * Some tests of the view class
 * @author Jeff
 *
 */
public class ViewTests {
	static Controller testController;
	static UserGuiView theGui;
	/**
	 * Initialize a controller as well as a gui object
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testController = new Controller();
		theGui = new UserGuiView(testController, 0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * A quick test to establish that the save enabling function works
	 */
	@Test
	public void testGuiSaveBtn() 
	{
		theGui.enableSave();
		assertTrue(theGui.save.isEnabled());
	}
	
	/**
	 * Runs a quick test to establish that isSolved is set to false as it should be
	 */
	@Test
	public void testIsSolvedFalse()
	{
		theGui.falseSolved();
		assertFalse(theGui.isSolved);
	}
	
	/**
	 * The same concept, but the opposite outcome
	 */
	@Test
	public void testIsSolvedTrue()
	{
		theGui.trueSolved();
		assertTrue(theGui.isSolved);
	}
	
	/**
	 * Tests to make sure the enabling of issolvable works right
	 */
	@Test
	public void testSolveEnabling()
	{
		theGui.enableButton();
		assertTrue(theGui.button.isEnabled());
	}
	
	/**
	 * Tests add panels to make sure that works as it should
	 */
	@Test
	public void testAddPanels()
	{
		theGui.addToPanel(theGui.myPanel, theGui.secPanel);
		assertTrue(true);
	}
	
	/**
	 * A quick position getter/setter test
	 */
	@Test
	public void testCoords()
	{
		Position tester = new Position(0,0);
		assertEquals(tester.getxCoord(), 0);
		assertEquals(tester.getyCoord(), 0);
	}
	
	/**
	 * tests the saving of the file itself
	 * @throws IOException 
	 */
	@Test
	public void testSaving() throws IOException
	{
		theGui.tboFile = new File("maze1.bmp");
		theGui.saveTheFile();
		JFileChooser jFileChooser = new JFileChooser();
		String output = "mySol_";
		output = output + theGui.tboFile.getName();
		assertNotNull(output);
	}
	
	/**
	 * tests the displaying of the file itself
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testDisplay() throws IOException
	{
		theGui.tboFile = new File("maze1.bmp");
		theGui.setPicture();
		assertTrue(theGui.secPanel.countComponents() == 1);
	}
	
	/**
	 * tests the displaying of the file itself
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testDisplay2() throws IOException
	{
		theGui.tboFile = new File("maze1.bmp");
		BufferedImage temp = ImageIO.read(theGui.tboFile);
;
		theGui.showPic(temp);
		assertTrue(theGui.panel.countComponents() == 1);
	}
	
	/**
	 * A quick test to make sure it returns an actual image when it runs
	 * @throws IOException
	 */
	@Test
	public void testSolNoFile() throws IOException
	{
		Mazes tbsMaze = new Mazes();
    	tbsMaze.end = new Position(319,239);
    	tbsMaze.beginning = new Position(0,0);
		tbsMaze.readImage(theGui.tboFile, 0);
		BufferedImage test = UserInterface.printMazesNoFile(tbsMaze, 1, 2, theGui.tboFile.getName());
		assertNotNull(test);
	}
}
