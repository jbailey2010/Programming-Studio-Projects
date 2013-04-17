package Tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.Mazes;
import Code.Nodes;
 
public class MazeTests { 

	//A tough maze to be solved/tested with
	static int[][] toughBinMaze = { {1,1,1,1,1,1,1,1,1}, {1,2,0,0,1,0,1,0,1},  
		{1,0,1,0,1,0,0,0,1}, {1,0,1,1,1,0,1,0,1}, {1,0,0,0,0,0,1,0,1}, {1,0,0,0,1,1,1,0,1}, 
		{1,0,1,0,1,0,0,0,1}, {1,0,1,0,0,0,1,3,1}, {1,1,1,1,1,1,1,1,1} };
	static char[][] toughSol = {{'|','|','|','|','|','|','|','|','|'},{'|','B',' ',' ','|',' ','|',' ','|'},
							{'|','X','|',' ','|',' ',' ',' ','|'},{'|','X','|','|','|',' ','|',' ','|'},
							{'|','X',' ',' ',' ',' ','|',' ','|'},{'|',' ','X',' ','|','|','|',' ','|'},
							{'|',' ','|','X','|',' ','X',' ','|'},{'|',' ','|',' ','X','X','|','E','|'},
							{'|','|','|','|','|','|','|','|','|'}};
	static Mazes toughMaze = new Mazes(9, 9, toughBinMaze);
	
	//A not hard, but not easy maze to be solved/tested with
	static int[][] binMaze = { {1,1,1,1,1,1,1}, {1,2,0,0,0,0,1}, {1,0,1,0,1,0,1}, {1,0,1,0,1,0,1}, 
							{1,0,1,1,1,0,1}, {1,0,0,0,0,3,1}, {1,1,1,1,1,1,1} };
	static char[][] medSol = {{'|', '|', '|', '|','|','|','|'},{'|','B','X','X','X',' ','|'},{'|',' ','|',' ','|','X','|'},
						{'|',' ','|',' ','|','X','|'},{'|',' ','|','|','|','X','|'},{'|',' ',' ',' ',' ','E','|'},
						{'|','|','|','|','|','|','|'}};
	static Mazes medMaze = new Mazes(7, 7, binMaze);
	
	//An easy maze to be solved/tested with
	static int[][] easyBinMaze = {{1,1,1,1,1}, {1, 2, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 3, 1}, {1, 1, 1, 1, 1}};
	static char[][] easySol = {{'|','|','|','|','|'}, {'|','B','X',' ','|'}, {'|',' ','|','X','|'}, 
							{'|',' ',' ','E', '|'}, {'|', '|', '|', '|', '|'}};
	static Mazes easyMaze = new Mazes(5, 5, easyBinMaze);
	
	/**
	 * Initializes the two different mazes, both the binary form and the node form
	 * @throws Exception
	 */  
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		toughMaze.initializeMazes(easyMaze, medMaze, toughMaze);
	} 

	/**
	 * Unused
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Converts the binary maze into a node maze, making sure that it
	 * remains the same size
	 */
	@Test
	public void testMazeConverterLength() 
	{
		assertEquals(easyMaze.nodeMaze.length, 5, 0);
		assertEquals(easyMaze.nodeMaze.length, easyMaze.theMaze.length, 0);
		assertEquals(easyMaze.nodeMaze[0].length, 5, 0);
		assertEquals(easyMaze.nodeMaze[0].length, easyMaze.theMaze[0].length, 0);
	}
	
	/**
	 * Tests the terrain status and open/closed status of the maze
	 */
	@Test
	public void testMazeConvStatus()
	{		
		for(int j = 0; j < 5; j++)
		{
			for(int i = 0; i < 5; i++)
			{
				if(easyMaze.nodeMaze[j][i].isOpen || easyMaze.nodeMaze[j][i].isClosed)
				{	
					fail("Booleans mis-set");
				}
			}
		}
	}
	
	/**
	 * Tests the boolean status of some of the locations relative to what they should be
	 */
	@Test
	public void testMazeConvBool()
	{
		//Below checcks the various booleans of the maze
		if(easyMaze.nodeMaze[1][0].terrainStatus==0 || easyMaze.nodeMaze[1][0].isOpen ||
			easyMaze.nodeMaze[1][0].isClosed)
		{
			fail("Booleans mis-set on the second row");
		}
		if(easyMaze.nodeMaze[1][2].terrainStatus!=0 || easyMaze.nodeMaze[1][2].isOpen ||
			easyMaze.nodeMaze[1][2].isClosed)
		{
			fail("Booleans mis-set on the second row");
		}
		if(easyMaze.nodeMaze[1][1].terrainStatus!=0 || easyMaze.nodeMaze[1][1].isOpen ||
			easyMaze.nodeMaze[1][1].isClosed)
		{
			fail("Booleans mis-set on the second row");
		}
		else
		{
			assertTrue(true);
		}
	}

	/**
	 * Tests the equals operator to make sure a maze = itself
	 */
	@Test
	public void testEqualsOperatorSame()
	{		
		assertTrue(easyMaze.equals(easyMaze));
	}
	
	/**
	 * Tests to make sure one more maze does not = itself
	 */
	@Test
	public void testEqualsOperatorSame2()
	{
		assertTrue(toughMaze.equals(toughMaze));
	}
	
	/**
	 * Tests the maze to make sure it isn't = to another maze
	 */
	@Test
	public void testEqualsOperatorDiff()
	{
		assertFalse(easyMaze.equals(easyBinMaze));
	}
	
	/**
	 * Tests the maze to make sure it isn't = to another maze with an altered order
	 */
	@Test
	public void testEqualsOperatorDiff2()
	{
		assertFalse(toughMaze.equals(easyMaze));
	}
	
	/**
	 * Makes a new maze, and checks it relative to a different maze
	 */
	@Test
	public void testEqualsOperatorDiffNew()
	{
		Mazes anotherMaze = new Mazes(3,3,easyBinMaze);
		Nodes anotherNodeMaze[][] = anotherMaze.convertMaze(easyBinMaze, easyBinMaze.length, easyBinMaze[0].length);
		anotherMaze.nodeMaze=anotherNodeMaze;
		assertFalse(anotherMaze.equals(easyMaze));
	}
	
	/**
	 * Tests the equals operator again with the same maze and itself
	 */
	@Test
	public void testEqualsOperatorSameNew()
	{
		Mazes anotherMaze = new Mazes(3,3,easyBinMaze);
		Nodes anotherNodeMaze[][] = anotherMaze.convertMaze(easyBinMaze, easyBinMaze.length, easyBinMaze[0].length);
		anotherMaze.nodeMaze=anotherNodeMaze;
		assertTrue(anotherMaze.equals(anotherMaze));
	}
	
	/**
	 * Tests to make sure the constructor does what it should
	 * mostly basic stuff like making sure 0's are 0's...etc
	 */
	@Test
	public void testConstructor()
	{
		Mazes constTest = new Mazes(7,7,toughBinMaze);
		constTest.nodeMaze = constTest.convertMaze(toughBinMaze,  7,  7);
		assertEquals(constTest.width, 7, 0);
		assertEquals(constTest.height, 7, 0);
		if(constTest.theMaze == toughBinMaze)
		{
			assertTrue(true);
		}
		else
		{
			fail("The binary maze didn't stay the same");
		}
	}
	
	/**
	 * Tests the getters and setters of the class
	 */
	@Test
	public void testgsMaze()
	{
		//Some getters and setters testing
		Mazes constTest = new Mazes(7,7,toughBinMaze);
		constTest.nodeMaze = constTest.convertMaze(toughBinMaze,  7,  7);
		
		assertEquals(constTest.getWidth(), 7, 0);
		assertEquals(constTest.getHeight(), 7, 0);
		
		if(constTest.getTheMaze()==constTest.theMaze)
		{
			assertTrue(true);
		}
		else
		{
			fail("The maze fetching went poorly");
		}
		
		if(constTest.getNodeMaze()==constTest.nodeMaze)
		{ 
			assertTrue(true);
		}
		else
		{
			fail("the node maze wasn't fetched");
		}
		
	}
	
	/**
	 * Tests to make sure that the ID system works as it should
	 */
	@Test
	public void testId()
	{
		int counter = 1;
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				assertEquals(toughMaze.nodeMaze[i][j].id, counter++);
			}
		}
	}
	
	

}
