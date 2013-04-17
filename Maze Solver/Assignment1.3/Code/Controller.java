package Code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Serves as the controller for the MVC structure
 * @author Jeff
 *
 */
public class Controller implements ActionListener 
{
	UserGuiView theGui;
	public Controller()
	{
		
	}
	
	  /**
     * Reads the various inputs and reacts as such, while still verifying inputs
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    { 
        String whichButton = event.getActionCommand();
        if (whichButton.equals("Open"))
        { 
       		displayMaze();
        }
        if(whichButton.equals("Save"))
        {
        	try {
				saveFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if(whichButton.equals("Solve it!"))
        {
           	BufferedImage returnPic = solveTheMaze();
           	this.theGui.showPic(returnPic);
           	this.theGui.enableSave();
        }
        if(whichButton.equals("Is it Solvable?"))
        {
        	if(!this.theGui.xCoords.getText().equals("Starting Coords") && !this.theGui.yCoords.getText().equals("Ending Coords"))
        	{
        		isSolvable();
        	}
        	else
        	{
        		String notDone = "Please enter all of the data";
        		this.theGui.showMessage(notDone);
        	}
        }
    }
    

	/**
	 * Displays the maze in the file opened, verifying that it is actually an image
	 */
    public void displayMaze()
    {
    	this.theGui.tboFile = selectFile();
    	String type = this.theGui.tboFile.getName();
        if(!type.contains(".bmp") && !type.contains(".png") && !type.contains(".jpg"))
        {
        	this.theGui.showMessage("Please Choose an Image");
    		return;
        }
		try {
			this.theGui.setPicture();
		} catch (IOException e) {
			e.printStackTrace();
		}
      this.theGui.myFrame.pack();
    }
    
	/**
	 * The function that's called to save the file, or not
	 * @throws IOException 
	 */
	public void saveFile() throws IOException
	{
		this.theGui.saveTheFile();
	}
    
    /**
     * The function that actually solves the maze
     * @return
     */
    public BufferedImage solveTheMaze()
    {
    	Mazes tbsMaze = new Mazes();
    	String xCoord = this.theGui.xCoords.getText();
    	int ind = xCoord.indexOf(",");
    	String xStart = xCoord.substring(0, ind);
    	String xEnd = xCoord.substring(ind+1);
    	String yCoord = this.theGui.yCoords.getText();
    	ind = yCoord.indexOf(",");
    	String yStart = yCoord.substring(0, ind);
    	String yEnd = yCoord.substring(ind+1);
    	tbsMaze.end = new Position(Integer.parseInt(yStart), Integer.parseInt(yEnd));
    	tbsMaze.beginning = new Position(Integer.parseInt(xStart), Integer.parseInt(xEnd));
    	try {
			tbsMaze.readImage(this.theGui.tboFile, 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    	int algChoice = 0;
    	int heurChoice = 0;
    	if(this.theGui.algList.getSelectedItem().equals("Dijkstra's Algorithm"))
    	{
    		algChoice = 2;
    	}
    	else
    	{
    		algChoice = 1;
    		if(this.theGui.heurList.getSelectedItem().equals("Manhattan"))
    		{
    			heurChoice = 1;
    		}
    		else if(this.theGui.heurList.getSelectedItem().equals("Diagonal"))
    		{
    			heurChoice = 2;
    		}
    		else
    		{
    			heurChoice = 3;
    		}
    	}
		try {
			return UserInterface.printMazesNoFile(tbsMaze, heurChoice, algChoice, this.theGui.tboFile.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * Checks to make sure the maze is solvable
     */
    public void isSolvable()
    {
     	if(solveTheMaze() == null)
    	{
     		this.theGui.falseSolved();
     		this.theGui.showMessage("It can't be solved...");
    	}
    	else
    	{
    		this.theGui.trueSolved();
    		this.theGui.enableButton();
    		this.theGui.showMessage("It IS Solvable");
    	}
    }
    
    /**
     * Selects the file to be run
     * @return
     */
	public File selectFile()
	{ 
		JFileChooser fc = new JFileChooser();	
    	fc.setCurrentDirectory(new java.io.File(".")); 
    	if (fc.showDialog(this.theGui.myPanel,  "Open file") == JFileChooser.APPROVE_OPTION) 
    	{
    	    return fc.getSelectedFile();
    	}
    	return null;
	}
	
	/**
	 * A quick main to get it started
	 * @param args
	 */
	public static void main(String[] args)
	{
		Controller theController = new Controller();
		theController.theGui = new UserGuiView(theController, 1);
	}
}
