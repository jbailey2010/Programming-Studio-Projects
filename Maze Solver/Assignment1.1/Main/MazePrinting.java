package Main;


import Code.AStarSolving;
import Code.DJikstraSolving;
import Code.DistanceMethods;
import Code.Mazes;
import Code.Nodes;

public class MazePrinting {
	
	//I got bored and made another...
	static int[][] brutalBin = {{1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,0,0,0,0,1,0,1,1,1,1},{1,0,0,0,1,0,0,1,0,0,1,1,1},
		{1,2,1,0,1,1,0,0,0,1,1,1,1},{1,0,1,0,0,1,0,1,0,0,1,0,1},{1,0,1,1,0,1,0,1,1,0,0,0,1},{1,0,1,0,0,0,0,0,1,0,0,1,1},
		{1,0,1,0,0,1,0,0,1,0,0,3,1},{1,0,1,1,1,1,1,1,1,1,1,0,1},{1,0,1,0,0,0,1,0,0,0,1,0,1},{1,0,1,0,1,0,1,0,1,0,1,0,1},
		{1,0,0,0,1,0,0,0,1,0,0,0,1},{1,1,1,1,1,1,1,1,1,1,1,1,1}};
	static Mazes brutalMaze = new Mazes(13, 13, brutalBin);
	
	//A tough maze to be solved/tested with
	static int[][] toughBinMaze = { {1,1,1,1,1,1,1,1,1}, {1,2,0,0,1,0,1,0,1}, 
		{1,0,1,0,1,0,0,0,1}, {1,0,1,1,1,0,1,0,1}, {1,0,0,0,0,0,1,0,1}, {1,0,0,0,1,1,1,0,1},  
		{1,0,1,0,1,0,0,0,1}, {1,0,1,0,0,0,1,3,1}, {1,1,1,1,1,1,1,1,1} };
	static Mazes toughMaze = new Mazes(9, 9, toughBinMaze);
	
	//A not hard, but not easy maze to be solved/tested with
	static int[][] binMaze = { {1,1,1,1,1,1,1}, {1,2,0,0,0,0,1}, {1,0,1,0,1,0,1}, {1,0,1,0,1,0,1}, 
							{1,0,1,1,1,0,1}, {1,0,0,0,0,3,1}, {1,1,1,1,1,1,1} };
	static Mazes medMaze = new Mazes(7, 7, binMaze);
	
	//An easy maze to be solved/tested with
	static int[][] easyBinMaze = {{1,1,1,1,1}, {1, 2, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 3, 1}, {1, 1, 1, 1, 1}};
	static Mazes easyMaze = new Mazes(5, 5, easyBinMaze);
	
	/**
	 * Initializes the mazes appropriately, then calls the printing/solving function afterwards
	 * @param args -- nothing
	 * @return -- nothing
	 */
	public static void main(String[] args) {
		
		easyMaze.initializeMazes(easyMaze, medMaze, toughMaze);
		
		Nodes brutalNodeMaze[][] = brutalMaze.convertMaze(brutalMaze.theMaze, brutalMaze.theMaze.length, brutalMaze.theMaze[0].length);
		brutalMaze.nodeMaze = brutalNodeMaze;

		printMazes(easyMaze);
	}
	
	/**
	 * Actually solves the maze, and prints out the unsolved and solved versions of it
	 * @param tbpMaze -- the maze 'to be printed'
	 */
	public static void printMazes(Mazes tbpMaze)
	{
		tbpMaze.drawMaze();
		DJikstraSolving mazeCaller = new DJikstraSolving(); 
		char[][] mazeSol = mazeCaller.solveTheMaze(tbpMaze);
		for(int j = 0; j < tbpMaze.width; j++)
		{ 
			for(int i = 0; i < tbpMaze.height; i++) 
			{
				System.out.print(mazeSol[i][j]);
			}
			System.out.println();
		}
	}

}
