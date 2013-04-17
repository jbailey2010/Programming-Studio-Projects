/**
 * 
 */
package Code;

import java.util.LinkedList;
import java.util.PriorityQueue;


/**
 * Stores the height and width of the maze for node position's sake
 * A 2-D int array that will be ultimately converted to a node array
 * The 2-D node array that comes from the int array
 * @author Jeff
 */
public class Mazes {
	public int height, width;
	public int theMaze[][];
	public Nodes nodeMaze[][];
	public Position end;
	public Position beginning;
	public PriorityQueue<Nodes> openList;
	/**
	 * The constructor for the maze object
	 * @param width sets the width of the maze
	 * @param height sets the height of the maze
	 * @param theMaze gives the initial binary maze in 2-D form
	 */
	public Mazes(int width, int height, int theMaze[][])
	{ 
		this.height = height; 
		this.width = width;
		this.setTheMaze(theMaze);
		Nodes theNodeMaze[][] = convertMaze(theMaze, width, height);
		this.setNodeMaze(theNodeMaze);
		this.openList = new PriorityQueue<Nodes>();
	}
	
	/**
	 * @returns the height of the maze
	 */
	public int getHeight() 
	{
		return height;
	}


	/**
	 * @returns the width of the maze
	 */
	public int getWidth() 
	{
		return width;
	}

	/**
	 * @returns the int maze itself
	 */
	public int[][] getTheMaze() 
	{
		return theMaze;
	}

	/**
	 * @param theMaze, takes the int maze and links it to the calling maze
	 */
	public void setTheMaze(int theMaze[][]) 
	{
		this.theMaze = theMaze;
	}

	/**
	 * @returns the nodeMaze of the calling maze
	 */
	public Nodes[][] getNodeMaze() 
	{
		return nodeMaze;
	}

	/**
	 * @param nodeMaze sets the node maze to the calling maze
	 */
	public void setNodeMaze(Nodes nodeMaze[][]) 
	{
		this.nodeMaze = nodeMaze;
	}
	
	/**
	 * A new equals operator, adapted from the square.java equals operator
	 * to be used primarily for testing of various maze creations
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Mazes) 
		{
			return 	(this.height == ((Mazes)other).height) && 
					(this.width == ((Mazes)other).width) &&
					(this.theMaze == ((Mazes)other).theMaze) &&
					(this.nodeMaze == ((Mazes)other).nodeMaze);
		} 
		return false;
	}
	
	/**
	 * Converts a int maze into a maze of nodes, with
	 * the various int values having specific meanings for the type of node
	 * @param theMaze -- the initial binary maze
	 * @param width -- the width of said maze
	 * @param height -- the height of said maze
	 * @return the new 2-D node array
	 */
	public Nodes[][] convertMaze(int[][] theMaze, int width, int height)
	{
		//Create a new node array
		Nodes nodeConvMaze[][] = new Nodes[width][height];
		int oldId = 0; 
		if(width == 0)
		{
			width = theMaze.length;
		}
		if(height == 0)
		{ 
			height = theMaze[0].length;
		}
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				//A status of 0 means an empty space, a non-wall, beginning, or end.
				if(theMaze[i][j]==0)
				{
					nodeConvMaze[i][j] = new Nodes(i, j, oldId, 0);
				}
				//2 means the beginning of the maze
				else if(theMaze[i][j]==2)
				{
					nodeConvMaze[i][j] = new Nodes(i, j, oldId, 0); 
					this.beginning = new Position(i, j);
				}
				//3 means the end of the maze
				else if(theMaze[i][j]==3) 
				{
					nodeConvMaze[i][j] = new Nodes(i, j, oldId, 0);
					this.end = new Position(i, j);
				}
				//1 means a wall
				else if(theMaze[i][j]==1)
				{
					nodeConvMaze[i][j] = new Nodes(i, j, oldId, 1);
				}
				oldId++;
			}
		}
		return nodeConvMaze;
	}
	
	/**
	 * Mostly for the sake of testing and having something to look at,
	 * this draws the maze in the console
	 * | is a wall, B beginning, E end, a space an open area
	 */
	public void drawMaze()
	{
		int width = this.width;
		int height = this.height;
		for(int j = 0; j < width; j++)
		{
			for(int i = 0; i < height; i++)
			{
				if(this.theMaze[i][j]==2)
				{
					System.out.print("B");
				}
				else if(this.theMaze[i][j]==3)
				{
					System.out.print("E");
				}
				else if(this.theMaze[i][j]!=1)
				{
					System.out.print(" "); 
				}
				else
				{
					System.out.print("|");
				}
				if(i+1 == height)
				{
					System.out.println();
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * To show that the function actually worked at the end, I re-wrote the drawMaze
	 * function to include X's for the path that was taken to solve the maze
	 * @param end -- the end of the maze to trace back to the beginning
	 */
	public char[][] drawMazeSol(Nodes end)
	{
		char [][] printMazeSol = new char[this.width][this.height];
		for(int j = 0; j < this.width; j++)
		{
			for(int i = 0; i < this.height; i++)
			{ 
				if(this.theMaze[i][j]==2)
				{
					printMazeSol[i][j] = 'B';
				}
				else if(this.theMaze[i][j]==3)
				{
					printMazeSol[i][j] = 'E';
				}
				else if(this.theMaze[i][j]!=1)
				{
					printMazeSol[i][j] = ' ';
				}
				else
				{
					printMazeSol[i][j] = '|';
				}
			}
		}
		//Adding in the x's to the array
		end = end.parent;
		while(end.location.xCoord != this.beginning.xCoord || end.location.yCoord != this.beginning.yCoord)
		{ 
			int xLoc = end.location.xCoord;
			int yLoc = end.location.yCoord; 
			printMazeSol[xLoc][yLoc] = 'X';
			end = end.parent;
		}
		return printMazeSol;
	} 
	
	/**
	 * Just trying to avoid copy-pasting a terrible amount of code over all of the test files
	 * @param easyMaze - the easy maze
	 * @param medMaze - the medium maze
	 * @param toughMaze - the tough maze
	 */
	public void initializeMazes(Mazes easyMaze, Mazes medMaze, Mazes toughMaze)
	{
		Nodes toughNodeMaze[][] = toughMaze.convertMaze(toughMaze.theMaze, toughMaze.theMaze.length, toughMaze.theMaze[0].length);
		toughMaze.nodeMaze = toughNodeMaze;
		
		Nodes medNodeMaze[][] = medMaze.convertMaze(medMaze.theMaze, medMaze.theMaze.length, medMaze.theMaze[0].length);
		medMaze.nodeMaze = medNodeMaze;
		
		Nodes easyNodeMaze[][] = easyMaze.convertMaze(easyMaze.theMaze, easyMaze.theMaze.length, easyMaze.theMaze[0].length);
		easyMaze.nodeMaze = easyNodeMaze;
	}
}

