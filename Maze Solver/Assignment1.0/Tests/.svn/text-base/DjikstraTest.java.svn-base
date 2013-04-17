package Tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Code.AStarSolving;
import Code.DJikstraSolving;
import Code.Mazes;
import Code.Nodes;


public class DjikstraTest { 

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
	static char[][] medSol = {{'|', '|', '|', '|','|','|','|'},{'|','B',' ',' ',' ',' ','|'},{'|','X','|',' ','|',' ','|'},
						{'|','X','|',' ','|',' ','|'},{'|','X','|','|','|',' ','|'},{'|',' ','X','X','X','E','|'},
						{'|','|','|','|','|','|','|'}};
	static Mazes medMaze = new Mazes(7, 7, binMaze);
	
	//An easy maze to be solved/tested with
	static int[][] easyBinMaze = {{1,1,1,1,1}, {1, 2, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 3, 1}, {1, 1, 1, 1, 1}};
	static char[][] easySol = {{'|','|','|','|','|'}, {'|','B',' ',' ','|'}, {'|','X','|',' ','|'}, 
							{'|',' ','X','E', '|'}, {'|', '|', '|', '|', '|'}};
	static Mazes easyMaze = new Mazes(5, 5, easyBinMaze);

	
	/**
	 * Make the three mazes into node mazes, then add each as node maze objects
	 * to the maze objects themselves
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
	 * Calls a helper function, simply split up to make another maze far easier to plug into
	 * the function.
	 */
	@Test
	public void djikstraToughShell() 
	{	
		djikstraSolver(toughMaze, toughSol);
	}
	
	/**
	 * Calls a helper function, simply split up to make another maze far easier to plug into
	 * the function.
	 */
	@Test
	public void djikstraMedShell() 
	{	
		djikstraSolver(medMaze, medSol);
	}
	
	/**
	 * Calls a helper function, simply split up to make another maze far easier to plug into
	 * the function.
	 */
	@Test
	public void djikstraEasyShell() 
	{	
		djikstraSolver(easyMaze, easySol);
	}
	
	/**
	 * The code that actually calls the solver functions and does the work
	 * @param doneMaze -- the maze that is to be solved
	 * @param mazeSol -- the solution key for that maze
	 */
	public void djikstraSolver(Mazes doneMaze, char[][] mazeSol)
	{
		DJikstraSolving mazeCaller = new DJikstraSolving();
		char[][] solvedMaze = mazeCaller.solveTheMaze(doneMaze);
		for(int i = 0; i < mazeSol.length; i++)
		{
			for(int j = 0; j < mazeSol.length; j++)
			{ 
				if(mazeSol[i][j] != solvedMaze[i][j])
				{
					System.out.println("The solution was not correct");
					fail("They aren't the same");
				}
			}
		}
	}
}
