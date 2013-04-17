package MockTests;

import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import Code.Mazes;
import Code.Nodes;
import Code.Position;
import Code.UserInterface;

/**
 * A series of mock tests over the user interface calls
 * @author Jeff
 *
 */
public class MockTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze0() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze0.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100);
		verify(testMaze).readImage(input, 100);
	}
	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze1() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze1.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100);
		verify(testMaze).readImage(input, 100);
	}
	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze2() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze2.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100); 
		verify(testMaze).readImage(input, 100);
	}
	
	/**
	 * Runs a quick test on the size of the maze itself
	 * @throws IOException
	 */
	@Test
	public void testWidth() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.height = image.getHeight();
		when(tbsMaze.getHeight()).thenReturn(tbsMaze.height);
		assertEquals(tbsMaze.getHeight(), image.getHeight());
	}
	
	/**
	 * Runs a quick test on the width
	 * @throws IOException
	 */
	@Test
	public void testHeight() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.width = image.getWidth();
		when(tbsMaze.getWidth()).thenReturn(tbsMaze.width);
		assertEquals(tbsMaze.getWidth(), image.getWidth());
	}
	
	/**
	 * Tests to make sure the beginning x Coord is right
	 * @throws IOException
	 */
	@Test
	public void testBeginningX() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.beginning = mock(Position.class);//new Position(0,0);
		when(tbsMaze.beginning.getxCoord()).thenReturn(0);
		assertEquals(tbsMaze.beginning.getxCoord(), 0);
	}
	
	/**
	 * Tests to make sure that the beginning y Coordinate is set
	 * @throws IOException
	 */
	@Test
	public void testBeginningY() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.beginning = mock(Position.class);//new Position(0,0);
		when(tbsMaze.beginning.getyCoord()).thenReturn(0);
		assertEquals(tbsMaze.beginning.getyCoord(), 0);
	}
	
	/**
	 * Tests to make sure that the ending coordinate is set as it should be
	 * @throws IOException
	 */
	@Test
	public void testEndX() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.end = mock(Position.class);//new Position(0,0);
		when(tbsMaze.end.getxCoord()).thenReturn(319);
		assertEquals(tbsMaze.end.getxCoord(), 319);
	}
	
	/**
	 * Tests to make sure that the ending coordinate is set as it should be
	 * @throws IOException
	 */
	@Test
	public void testEndY() throws IOException
	{
		Mazes tbsMaze = mock(Mazes.class);//new Mazes();
		File fileName = new File("maze0.bmp");
		BufferedImage image = ImageIO.read(fileName);
		tbsMaze.end = mock(Position.class);//new Position(0,0);
		when(tbsMaze.end.getyCoord()).thenReturn(239);
		assertEquals(tbsMaze.end.getyCoord(), 239);
	}
	
	/**
	 * Tests the conversion of the nodes into arrays
	 * @throws IOException
	 */
	@Test
	public void mockSys() throws IOException 
	{
		Mazes tbsMaze = mock(Mazes.class);
		when(tbsMaze.getHeight()).thenReturn(3);
		when(tbsMaze.getWidth()).thenReturn(3);
		tbsMaze.beginning = mock(Position.class);
		when(tbsMaze.beginning.getxCoord()).thenReturn(0);
		when(tbsMaze.beginning.getyCoord()).thenReturn(0);
		tbsMaze.end = mock(Position.class);
		when(tbsMaze.end.getxCoord()).thenReturn(2);
		when(tbsMaze.end.getyCoord()).thenReturn(2);
		int[][] theMaze = {{2,0,0},{0,1,0},{0,0,3}};
		when(tbsMaze.getTheMaze()).thenReturn(theMaze);
		assertTrue(tbsMaze.getTheMaze().equals(theMaze));
	}
	
	/**
	 * Tests the conversion of the array into a node array
	 */
	@Test 
	public void mockNodes()
	{
		Mazes tbsMaze = mock(Mazes.class);
		Mazes realMaze = new Mazes();
		when(tbsMaze.getHeight()).thenReturn(3);
		when(tbsMaze.getWidth()).thenReturn(3);
		realMaze.width = 3;
		realMaze.height = 3;
		tbsMaze.beginning = mock(Position.class);
		realMaze.beginning = new Position(0,0);
		when(tbsMaze.beginning.getxCoord()).thenReturn(0);
		when(tbsMaze.beginning.getyCoord()).thenReturn(0);
		tbsMaze.end = mock(Position.class);
		realMaze.end = new Position(2,2);
		when(tbsMaze.end.getxCoord()).thenReturn(2);
		when(tbsMaze.end.getyCoord()).thenReturn(2);
		int[][] theMaze = {{2,0,0},{0,1,0},{0,0,3}};
		realMaze.theMaze = theMaze;
		when(tbsMaze.getTheMaze()).thenReturn(theMaze);
		Nodes imgNodeMaze[][] = realMaze.convertMaze(tbsMaze.getTheMaze(), theMaze.length, theMaze[0].length);
		when(tbsMaze.getNodeMaze()).thenReturn(imgNodeMaze);
		assertTrue(imgNodeMaze.length == theMaze.length);
		assertTrue(imgNodeMaze[0].length == theMaze[0].length);
		assertTrue(imgNodeMaze[1][1].terrainStatus == 1);
	}
	
	/**
	 * Tests the conversion of the array into a node array
	 */
	@Test 
	public void mockNodesStartEnd()
	{
		Mazes tbsMaze = mock(Mazes.class);
		Mazes realMaze = new Mazes();
		when(tbsMaze.getHeight()).thenReturn(3);
		when(tbsMaze.getWidth()).thenReturn(3);
		realMaze.width = 3;
		realMaze.height = 3;
		tbsMaze.beginning = mock(Position.class);
		realMaze.beginning = new Position(0,0);
		when(tbsMaze.beginning.getxCoord()).thenReturn(0);
		when(tbsMaze.beginning.getyCoord()).thenReturn(0);
		tbsMaze.end = mock(Position.class);
		realMaze.end = new Position(2,2);
		when(tbsMaze.end.getxCoord()).thenReturn(2);
		when(tbsMaze.end.getyCoord()).thenReturn(2);
		int[][] theMaze = {{2,0,0},{0,1,0},{0,0,3}};
		realMaze.theMaze = theMaze;
		when(tbsMaze.getTheMaze()).thenReturn(theMaze);
		Nodes imgNodeMaze[][] = realMaze.convertMaze(tbsMaze.getTheMaze(), theMaze.length, theMaze[0].length);
		when(tbsMaze.getNodeMaze()).thenReturn(imgNodeMaze);
		assertTrue(imgNodeMaze[tbsMaze.beginning.getxCoord()][tbsMaze.beginning.getyCoord()].terrainStatus == 
				(tbsMaze.getTheMaze()[tbsMaze.beginning.getxCoord()][tbsMaze.beginning.getyCoord()] - 2));
		assertTrue(imgNodeMaze[tbsMaze.end.getxCoord()][tbsMaze.end.getyCoord()].terrainStatus == 
				(tbsMaze.getTheMaze()[tbsMaze.end.getxCoord()][tbsMaze.end.getyCoord()] - 3));
	}
}
