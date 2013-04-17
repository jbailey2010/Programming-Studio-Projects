package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.DiagonalEstCost;
import Code.EuclideanEstimatedCost;
import Code.ManhattanEstCost;
import Code.Mazes;
import Code.Nodes;

public class DistanceTests { 
	
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
	 * Sets up the mazes to work as they should when I test them
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		toughMaze.initializeMazes(easyMaze, medMaze, toughMaze);

	}
	
	/**
	 * Unused
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * Tests to make sure the estimated cost function works by checking the 
	 * output relative to hand calculations with the Diagonal method
	 */
	@Test
	public void testDEstCostClose()
	{
		//Test that the distance from one node to itself is 0
		DiagonalEstCost testChecker = new DiagonalEstCost();
		Nodes end = toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord];
		double estCost = testChecker.estimatedCost(end, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 0, 0);
	}

	/**
	 * Tests the cost from one node away
	 */
	@Test
	public void testDEstCostOne()
	{
		DiagonalEstCost testChecker = new DiagonalEstCost();
		Nodes close = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord];
		double estCost = testChecker.estimatedCost(close,  toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 100, 0);
	}
	
	/**
	 * Tests the Diagonal cost of a somewhat close node
	 */
	@Test
	public void testDEstCostMid()
	{
		//Test a close distance
		DiagonalEstCost testChecker = new DiagonalEstCost();
		Nodes closeToEnd = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord-2];
		double estCost = testChecker.estimatedCost(closeToEnd, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 241, 0);
	}
		
	/**
	 * Tests the Diagonal cost of a far away node
	 */
	@Test
	public void testDEstCostFar()
	{
		//Test a far distance
		DiagonalEstCost testChecker = new DiagonalEstCost();
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		double estCost = testChecker.estimatedCost(start,toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 846, 0);
	}
	
	/**
	 * Very similar to the testHEstCost, this calculates the manhattan distance cost for a node and itself
	 */
	@Test
	public void testMEstCostSame()
	{
		//Test that the distance from one node to itself is 0
		ManhattanEstCost testChecker = new ManhattanEstCost();
		Nodes end = toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord];
		double estCost = testChecker.estimatedCost(end, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 0, 0);
	}
	
	/**
	 * Tests the cost from one node away
	 */
	@Test
	public void testMEstCostClose()
	{
		Nodes close = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord];
		ManhattanEstCost testChecker = new ManhattanEstCost();
		double estCost = testChecker.estimatedCost(close, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 100, 0);
	}
		
	/**
	 * Tests the Manhattan distance for a node and another somewhat close to it
	 */
	@Test
	public void testMEstCostMid()
	{
		//Test a close distance
		Nodes closeToEnd = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord-2];
		ManhattanEstCost testChecker = new ManhattanEstCost();
		double estCost = testChecker.estimatedCost(closeToEnd, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 300, 0);
	}
	
	/**
	 * Tests the Manhattan distance for a node and another that is far away
	 */
	@Test
	public void testMEstCostFar()
	{
		//Test a far distance
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		ManhattanEstCost testChecker = new ManhattanEstCost();
		double estCost = testChecker.estimatedCost(start, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 1200, 0);
	}

	/**
	 * This calculates the Euclidean distance cost for a node and itself
	 */
	@Test
	public void testEEstCostSame()
	{
		//Test that the distance from one node to itself is 0
		EuclideanEstimatedCost testChecker = new EuclideanEstimatedCost();
		Nodes end = toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord];
		double estCost = testChecker.estimatedCost(end, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 0, 0);
	}
	
	/**
	 * Tests the cost from one node away
	 */
	@Test
	public void testEEstCostClose()
	{
		Nodes close = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord];
		EuclideanEstimatedCost testChecker = new EuclideanEstimatedCost();
		double estCost = testChecker.estimatedCost(close, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 100, 0);
	}
		
	/**
	 * Tests the Euclidean distance for a node and another somewhat close to it
	 */
	@Test
	public void testEEstCostMid()
	{
		//Test a close distance
		Nodes closeToEnd = toughMaze.nodeMaze[toughMaze.end.xCoord-1][toughMaze.end.yCoord-2];
		EuclideanEstimatedCost testChecker = new EuclideanEstimatedCost();
		double estCost = testChecker.estimatedCost(closeToEnd, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 223.606, .001);
	}
	
	/**
	 * Tests the Euclidean distance for a node and another that is far away
	 */
	@Test
	public void testEEstCostFar()
	{
		//Test a far distance
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		EuclideanEstimatedCost testChecker = new EuclideanEstimatedCost();
		double estCost = testChecker.estimatedCost(start, toughMaze.nodeMaze[toughMaze.end.xCoord][toughMaze.end.yCoord]);
		assertEquals(estCost, 848.528, .001);
	}
}
