package Code;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.imageio.ImageIO;
/**
 * A collection of the functions used in the user interface in the main
 * @author Jeff
 *
 */
public class UserInterface {
	
	/**
	 * Takes prompts from the command line to get the preferred algorithm choice for solving
	 * @return -- an int representing the choice of algorithm
	 */
	/*public static int algCommand()
	{
		Scanner lineInput = new Scanner(System.in);
		System.out.println("Please the number next to which algorithm you'd like to run:");
		System.out.println("1. A* Algorithm");
		System.out.println("2. Dijkstra's Algorithm");
		int algChoice = 0;
		while(true)
		{
			algChoice = lineInput.nextInt(); 
			if(algChoice != 1 && algChoice != 2)
			{
				System.out.println("Please enter a valid number, 1 or 2, for your preferred algorithm");
				continue;
			}
			break;
		}
		return algChoice;
	}	*/
	/**
	 * Similar to the algorithm function, it runs to choose the user's preferred heuristic
	 * @param algChoice -- only used to avoid unnecessary work if it's Dijkstra's
	 * @return -- an int representing the preferred heuristic
	 */
	/*public static int heurCommand(int algChoice)
	{
		int heurChoice = 0;
		if(algChoice != 2)
		{
			Scanner lineInput = new Scanner(System.in);
			System.out.println("Please the number next to which heuristic function you'd like to run:");
			System.out.println("1. Manhattan");
			System.out.println("2. Diagonal");
			System.out.println("3. Euclidean");
			while(true)
			{
				heurChoice = lineInput.nextInt();
				if(heurChoice != 1 && heurChoice != 2 && heurChoice != 3)
				{
					System.out.println("Please enter a valid number, 1 through 3, for your preferred heuristic");
					continue;
				}
				break;
			}
		}
		return heurChoice;
	}*/
		
	/**
	 * Simply gets the name of the file to be solved from the user
	 * @return -- a string to be used to open the file
	 *//*
	public static String fileCommand()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the name of the file you wish to solve (ex: maze0.bmp)");
		String name;
		while(true)
		{
			name = scan.nextLine();
			File test = new File(name);
			if(!test.exists())
			{
				System.out.println("Please enter the name of a file that exists");
				continue;
			}
			break;
		}
		return name;
	}*/
		
	/**
	 * Takes the user's input to get the start and ending points
	 * @param tbpMaze -- the maze in which the values are to be stored
	 *//*
	public static void setStartEnd(Mazes tbpMaze)
	{
		Scanner lineInput = new Scanner(System.in);
		System.out.println("Please the x and y-coordinates of the starting point you'd like to use (separated)");
		int xCoord = lineInput.nextInt();
		int yCoord = lineInput.nextInt();
		tbpMaze.beginning = new Position(xCoord, yCoord);
		 
		System.out.println("Please the x and y-coordinates of the ending point you'd like to use (separated)");
		xCoord = lineInput.nextInt();
		yCoord = lineInput.nextInt();
		tbpMaze.end = new Position(xCoord, yCoord);		
	}*/
	

	/**
	 * Makes sure the user is ready to solve the maze
	 *//*
	public static void confirmSolve()
	{
		System.out.println("Press enter to solve the maze");
		Scanner confirmEnter = new Scanner(System.in);
		while(!confirmEnter.nextLine().equals(""));
	}*/
	
	/**
	 * Actually solves the maze, and prints out the unsolved and solved versions of it
	 * @param tbpMaze -- the maze 'to be printed'
	 * @throws IOException 
	 */
	public static File printMazes(Mazes tbpMaze, int heurChoice, int algChoice, String fileName) throws IOException
	{
		//tbpMaze.drawMaze();
		if(tbpMaze == null)
		{
			return null;
		}
		char[][] mazeSol = new char[tbpMaze.width][tbpMaze.height];
		if(algChoice == 2)
		{
			DijkstraSolving mazeCaller = new DijkstraSolving(); 
			mazeSol = mazeCaller.solveTheMaze(tbpMaze, heurChoice);
		}
		else if(algChoice == 1)
		{
			AStarSolving mazeCaller = new AStarSolving(); 
			mazeSol = mazeCaller.solveTheMaze(tbpMaze, heurChoice);
		}  
		
		Stack<Position> positions = new Stack<Position>();
		setStack(tbpMaze, mazeSol, positions);
		if(mazeSol == null)
		{
			return null; 
		}
		return printSol(positions, tbpMaze, fileName, algChoice);
	}
	/**
	 * Prints the maze to an output bmp
	 * @param positions -- the stack of positions to be printed
	 * @param tbpMaze -- the maze I don't think I ever used here
	 * @param fileName -- the string file name for a few reasons 
	 * @throws IOException -- to make the bugs go away
	 */
	public static File printSol(Stack<Position> positions, Mazes tbpMaze, String fileName, int algChoice) throws IOException
	{
		String output = "sol";
		if(algChoice == 1)
		{
			output = output+"_as_";
		}
		else if(algChoice == 2)
		{
			output = output+"_dj_";
		} 
		output = output + fileName;
		File theFile = new File(fileName); 
		BufferedImage image = ImageIO.read(theFile);
		BufferedImage outputImage = new BufferedImage(tbpMaze.width, tbpMaze.height, BufferedImage.TYPE_3BYTE_BGR);
		outputImage.getGraphics().drawImage(image, 0, 0, null);
		while(positions.size() > 0)
		{
			Position path = positions.pop();
			outputImage.setRGB(path.xCoord, path.yCoord, Color.red.getRGB());
		}
		File outputFile = new File(output);
		ImageIO.write(outputImage, "bmp", outputFile);
		return outputFile;
	}
	
	/**
	 * Actually solves the maze, and prints out the unsolved and solved versions of it
	 * @param tbpMaze -- the maze 'to be printed'
	 * @throws IOException 
	 */
	public static BufferedImage printMazesNoFile(Mazes tbpMaze, int heurChoice, int algChoice, String fileName) throws IOException
	{
		char[][] mazeSol = new char[tbpMaze.width][tbpMaze.height];
		if(algChoice == 2)
		{
			DijkstraSolving mazeCaller = new DijkstraSolving(); 
			mazeSol = mazeCaller.solveTheMaze(tbpMaze, heurChoice);
		}
		else if(algChoice == 1)
		{
			AStarSolving mazeCaller = new AStarSolving(); 
			mazeSol = mazeCaller.solveTheMaze(tbpMaze, heurChoice);
		}  
		
		Stack<Position> positions = new Stack<Position>();
		setStack(tbpMaze, mazeSol, positions);
		if(mazeSol == null)
		{
			return null; 
		}
		return printSolNoFile(positions, tbpMaze, fileName, algChoice);
	}
	/**
	 * Prints the maze to an output bmp
	 * @param positions -- the stack of positions to be printed
	 * @param tbpMaze -- the maze I don't think I ever used here
	 * @param fileName -- the string file name for a few reasons 
	 * @throws IOException -- to make the bugs go away
	 */
	public static BufferedImage printSolNoFile(Stack<Position> positions, Mazes tbpMaze, String fileName, int algChoice) throws IOException
	{
		String output = "sol";
		if(algChoice == 1)
		{
			output = output+"_as_";
		}
		else if(algChoice == 2)
		{
			output = output+"_dj_";
		} 
		output = output + fileName;
		File theFile = new File(fileName); 
		BufferedImage image = ImageIO.read(theFile);
		BufferedImage outputImage = new BufferedImage(tbpMaze.width, tbpMaze.height, BufferedImage.TYPE_3BYTE_BGR);
		outputImage.getGraphics().drawImage(image, 0, 0, null);
		while(positions.size() > 0)
		{
			Position path = positions.pop();
			outputImage.setRGB(path.xCoord, path.yCoord, Color.red.getRGB());
		}
		return outputImage;
		//File outputFile = new File(output);
		//ImageIO.write(outputImage, "bmp", outputFile);
		//return outputFile;
	}
	
	/** 
	 * Sets the stack to hold the path of the solution to the maze
	 * @param tbpMaze -- the maze traced through
	 * @param mazeSol -- the array of the solution
	 * @param positions -- the stack to be used
	 */
	public static void setStack(Mazes tbpMaze, char[][] mazeSol, Stack<Position> positions)
	{
		Position start = new Position(tbpMaze.beginning.xCoord, tbpMaze.beginning.yCoord);
		Position end = new Position(tbpMaze.end.xCoord, tbpMaze.end.yCoord);
		positions.add(start);
		positions.add(end);
		for(int i = 0; i < tbpMaze.width; i++)
		{
			for(int j = 0; j < tbpMaze.height; j++)
			{
				if(mazeSol[i][j] == 'X')
				{
					Position path = new Position(i, j);
					positions.add(path);
				}
			}
		}
	}
	
	/**
	 * Gets the user's preferred grayscale value to distinguish walls and travel-able paths
	 * @return -- that value
	 *//*
	public static int getGrayScale()
	{
		int grayChoice = 0;
		Scanner lineInput = new Scanner(System.in);
		System.out.println("Please enter the rbga grayscale value you'd like to use to distinguish walls and open paths (0-255)");
		while(true)
		{
			grayChoice = lineInput.nextInt();
			if(grayChoice <= 0 || grayChoice >= 255)
			{
				System.out.println("Please enter a valid number, 0-255, for your preferred heuristic");
				continue;
			}
			break;
		}
		return grayChoice;
	}*/
}
