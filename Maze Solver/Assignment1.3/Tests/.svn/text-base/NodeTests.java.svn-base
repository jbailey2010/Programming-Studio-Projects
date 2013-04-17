/**
 * 
 */
package Tests;

import static org.junit.Assert.*;


import java.util.LinkedList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.Mazes;
import Code.Nodes;
/**
 * @author Jeff 
 *
 */
public class NodeTests {
	
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
	 * Initializes the mazes to be ready to run
	 * @throws java.lang.Exception 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		toughMaze.initializeMazes(easyMaze, medMaze, toughMaze);
	} 

	/**
	 * Unused
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Very simple, just tests the cost comparator with a < result
	 */
	@Test
	public void testCostLess() 
	{
		Nodes lessNode = new Nodes(1,1,0,0);
		Nodes moreNode = new Nodes(1,2,1,0);
		lessNode.totalCost = 5;
		moreNode.totalCost = 50;
		assertEquals(lessNode.compareTo(moreNode), -1, 0);
	}
	
	/**
	 * Tests it again with an equals situation
	 */
	@Test
	public void testCostEqual()
	{
		Nodes eqlNode = new Nodes(1,1,0,0);
		Nodes eqlNode2 = new Nodes(1,2,1,0);
		eqlNode.totalCost = 50;
		eqlNode2.totalCost = 50;
		assertEquals(eqlNode.compareTo(eqlNode2), -1, 0);
	}
	
	/**
	 * Tests it one last time with a > outcome
	 */
	@Test
	public void testCostMore()
	{
		Nodes moreNode2 = new Nodes(1,1,0,0);
		Nodes lessNode2 = new Nodes(1,2,1,0);
		moreNode2.totalCost = 50;
		lessNode2.totalCost = 5;
		assertEquals(moreNode2.compareTo(lessNode2), 1, 0);
	}
	
	/**
	 * Tests to see if the isNeighbor function works with a neighboring wall,
	 */
	@Test
	public void testNeighborWall()
	{
		Nodes currNode = new Nodes(1,1,0,0);
		Nodes wallNode = new Nodes(1,2,1,1);
		assertEquals(currNode.isNeighbor(wallNode), 2, 0);
	}
	
	/**
	 * Tests a node relative to a neighbor to make sure they are marked as neighbors
	 */
	@Test
	public void testNeighborNeighbor()
	{
		Nodes currNode = new Nodes(1,1,0,0);
		Nodes neighborNode = new Nodes(1,2,1,0);
		assertEquals(currNode.isNeighbor(neighborNode), 1, 0);
	}
	
	/**
	 * Tests a node relative to a non-neighbor to make sure it isn't marked a neighbor
	 */
	@Test
	public void testNeighborNot()
	{
		Nodes currNode = new Nodes(1,1,0,0);
		Nodes notNeighborNode = new Nodes(1,3,1,0);
		assertEquals(currNode.isNeighbor(notNeighborNode), 0, 0);
	}
	
	/**
	 * Tests the node to itself to make sure it isn't marked as a neighbor
	 */
	@Test
	public void testNeighborSame()
	{
		Nodes currNode = new Nodes(1,1,0,0);
		Nodes eqlNode = new Nodes(1,1,1,0);
		assertEquals(currNode.isNeighbor(eqlNode), -1, 0);
	}
	
	/**
	 * Tests to see if the overridden equals operator works
	 */
	@Test
	public void testEqualsSame()
	{
		Nodes currNode = new Nodes(1,1,0,0);
		Nodes secNode = new Nodes(1,1,0,0);
		if(currNode.equals(secNode))
		{
			assertTrue(true);
		}
		else
		{
			fail("Equals operator test 1 does not work");
		}
	}
	
	/**
	 * Tests the equals operator with two different nodes
	 */
	@Test
	public void testEqualsDiff()
	{
		Nodes diffNode = new Nodes(1,1,0,0);
		Nodes otherNode = new Nodes(2,1,0,0);
		if(otherNode.equals(diffNode))
		{
			fail("Should be different but aren't");;
		}
		else
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Tests the equals operator with a wall and a node
	 */
	@Test
	public void testEqualsWall()
	{
		Nodes wallNode = new Nodes(1,1,0,1);
		Nodes otherWallNode = new Nodes(1,1,0,1);
		if(wallNode.equals(otherWallNode))
		{
			assertTrue(true);
		}
		else
		{
			fail("Being walls doesn't fix it.");
		}
	}
	
	/**
	 * Tests the constructor to make sure everything is as baseline
	 * as it should be
	 */
	@Test
	public void testNodeConst()
	{
		Nodes newNode = new Nodes(1, 2, 3, 0);
		assertEquals(newNode.estCost, 0, 0);
		assertEquals(newNode.accumCost, 0, 0);
		assertEquals(newNode.totalCost, 0, 0);
		if(newNode.id == 0)
		{
			fail("ID Failed");
		}
		if(newNode.isOpen == true)
		{
			fail("isOpen failed");
		}
		if(newNode.isClosed == true)
		{
			fail("isClosed failed");
		}
		if(newNode.parent != null)
		{
			fail("parent failed");
		}
	}
	
	/**
	 * The getters and setters test, consolidated into one for my own sanity
	 */
	@Test
	public void gsTests()
	{
		Nodes newNode = new Nodes(1, 2, 3, 0);

		assertEquals(newNode.getEstCost(), 0, 0);
		
		assertEquals(newNode.getAccumCost(), 0, 0);
		
		assertEquals(newNode.getTotalCost(), 0, 0);

		assertFalse(newNode.isOpen());

		assertFalse(newNode.isClosed());
		
		assertEquals(newNode.location.xCoord, 1, 0);
		assertEquals(newNode.location.yCoord, 2, 0);
		
		assertEquals(newNode.id, 4, 0);
		assertEquals(newNode.getId(), 4, 0);
		
		if(newNode.getParent() == null)
		{
			assertTrue(true);
		}
		else
		{
			fail("Node parent troubles");
		}
		
		assertEquals(newNode.terrainStatus(), 0, 0);		
	}
	

	
	/**
	 * Tests the function that returns the list of neighbors of a calling node 
	 */
	@Test
	public void testGetNeighborsSize()
	{
		//Tests the size of the list returned of neighbors of the start
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		LinkedList<Nodes> startNeighbors = new LinkedList<Nodes>();
		startNeighbors = start.findNeighbors(toughMaze,1);
		assertEquals(startNeighbors.size(), 2, 0);
	}
	
	/**
	 * Tests the x coordinates of the nodes returned by getNeighbor to make sure they are what they should be
	 */
	@Test
	public void testNeihborsXCoords()
	{
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		LinkedList<Nodes> startNeighbors = new LinkedList<Nodes>();
		startNeighbors = start.findNeighbors(toughMaze,1);
		
		//Tests to make sure the coordinates are correct
		Nodes startNode1 = startNeighbors.removeFirst();
		Nodes startNode2 = startNeighbors.removeFirst();
		assertEquals(startNode1.location.xCoord, 1, 0);
		assertEquals(startNode2.location.xCoord, 2, 0);
	}
	
	/**
	 * Tests the y coordinates of the nodes returned by getNeighbor to make sure they are what they should be
	 */
	@Test
	public void testNeighborsYCoords()
	{
		Nodes start = toughMaze.nodeMaze[toughMaze.beginning.xCoord][toughMaze.beginning.yCoord];
		LinkedList<Nodes> startNeighbors = new LinkedList<Nodes>();
		startNeighbors = start.findNeighbors(toughMaze,1);
		
		//Tests to make sure the coordinates are correct
		Nodes startNode1 = startNeighbors.removeFirst();
		Nodes startNode2 = startNeighbors.removeFirst();
		assertEquals(startNode1.location.yCoord, 2, 0);
		assertEquals(startNode2.location.yCoord, 1, 0);
	}
	
	/**
	 * Makes sure that, when a node is stuck in a corner, it doesn't think it can be used.
	 */
	@Test
	public void testNoNeighbors()
	{
		Nodes stuck = toughMaze.nodeMaze[7][1];
		LinkedList<Nodes> oneNeighbor = new LinkedList<Nodes>();
		oneNeighbor = stuck.findNeighbors(toughMaze,1);
		assertEquals(oneNeighbor.size(), 1, 0);
	}
	
	/**
	 * Tests the start and end by node to see if they are established as start and end, and makes sure the ID's are unique
	 */
	@Test
	public void testID()
	{
		Nodes start = toughMaze.nodeMaze[1][1];
		Nodes end = toughMaze.nodeMaze[7][7];
		assertFalse(start.id == end.id);
	}
	
	/**
	 * Tests the access and add/removing of open and closed sets
	 */
	@Test
	public void testOpenClosed()
	{
		Nodes openClosedTest = new Nodes(1,1,0,0);
		assertFalse(openClosedTest.isOpen);
		LinkedList<Nodes> openList = new LinkedList<Nodes>();
		openList.add(openClosedTest);
		openClosedTest.isOpen = true;
		assertTrue(openList.contains(openClosedTest));
	}
	
	/**
	 * Tests to make sure that being on the end of the maze is distinguished from all other nodes,
	 * testing the boolean and both of the coordinates
	 */
	@Test
	public void testEnd()
	{
		Nodes endTest = toughMaze.nodeMaze[7][7];
		assertEquals(endTest.location.xCoord, toughMaze.end.xCoord);
		assertEquals(endTest.location.yCoord, toughMaze.end.yCoord);
	}
	
}
