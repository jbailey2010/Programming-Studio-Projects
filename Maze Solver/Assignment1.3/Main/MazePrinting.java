package Main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.imageio.ImageIO;

import Code.AStarSolving;
import Code.Controller;
import Code.DijkstraSolving;
import Code.DistanceMethods;
import Code.Mazes;
import Code.Nodes;
import Code.Position;
import Code.UserGuiView;
import Code.UserInterface;

/**
 * The main function, to be used to demonstrate functionality
 * @author Jeff
 *
 */
public class MazePrinting {
/*
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
*/	
	/**
	 * Initializes the mazes appropriately, then calls the printing/solving function afterwards
	 * @param args -- nothing
	 * @return -- nothing
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
	//	easyMaze.initializeMazes(easyMaze, medMaze, toughMaze);
		
	//	Nodes brutalNodeMaze[][] = brutalMaze.convertMaze(brutalMaze.theMaze, brutalMaze.theMaze.length, brutalMaze.theMaze[0].length);
	//	brutalMaze.nodeMaze = brutalNodeMaze;
	//	UserGui temp = new UserGui();
		Controller cntrlr = new Controller();
	/**	Mazes tbsMaze = new Mazes();
		int algChoice = UserInterface.algCommand();
		int heurChoice = UserInterface.heurCommand(algChoice);
		String fileName = UserInterface.fileCommand();
		File theFile = new File(fileName);  
		UserInterface.setStartEnd(tbsMaze);
		int grayScale = UserInterface.getGrayScale();
		UserInterface.confirmSolve();
		tbsMaze.readImage(theFile, grayScale); 
		UserInterface.printMazes(tbsMaze, heurChoice, algChoice, fileName);*/
	}
}
