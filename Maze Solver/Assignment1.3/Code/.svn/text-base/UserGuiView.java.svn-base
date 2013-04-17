package Code;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Basically the view class, makes the jframe. This is based off of the
 * code given in the specs
 * @author Jeff
 * 
 */
//Make save an option there, but not available until it's solved
//When solved, print the solved maze INSTEAD of the unsolved one
//GIVE THE OPTION TO export the maze/save it, DON'T AUTOMATICALLY

public class UserGuiView{
	JTextField xCoords, yCoords;
	JComboBox heurList, algList;
	public JButton button;
	JButton solvable;
	public JPanel panel = new JPanel();
	public JPanel secPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JLabel picLabel = new JLabel(); 
	public JMenuItem save = new JMenuItem();
	BufferedImage outputImage;
	public boolean isSolved = false;
	public File tboFile;
	JFrame myFrame;
	public JPanel myPanel;

	Controller cntrlr;
	/**
	 * Initializes some of the basic parts of the GUI
	 */
    public UserGuiView(Controller theController, int flag){
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception e) 
        {
        	
        }
        this.cntrlr = theController;
        myFrame = new JFrame("Maze Solver");
        myFrame.setSize(800, 700);
        myPanel = initializePanel();
        initializeAlgs(myPanel);
        setUpMenu(myFrame);
        myFrame.setContentPane(myPanel);
        myFrame.setVisible(false);
        if(flag == 1)
        {
        	myFrame.setVisible(true);
        }
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the algorithm-related parts of the panel
     * @param myPanel
     */
    public void initializeAlgs(JPanel myPanel)
    {
    	JPanel btnPanel = new JPanel();
    	btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] algStrings = { "A* Algorithm", "Dijkstra's Algorithm" };
        algList = new JComboBox(algStrings);
        algList.setSelectedIndex(1);
      	algList.addActionListener(this.cntrlr);
      	btnPanel.add(algList);
      	initializeHeur(myPanel, btnPanel);
    }
    
    /**
     * Initializes the heuristic inputs for the panel
     * @param myPanel
     * @param btnPanel
     */
    private void initializeHeur(JPanel myPanel, JPanel btnPanel)
    {
        String[] heurStrings = {"Manhattan", "Diagonal", "Euclidean", "Dijkstra's"};
        heurList = new JComboBox(heurStrings);
        heurList.setSelectedIndex(3);
      	heurList.addActionListener(this.cntrlr);
      	btnPanel.add(heurList);
      	initializeCoords(myPanel, btnPanel);
    }
    
    /**
     * Initializes the coordinate parts for the panel
     * @param myPanel
     * @param btnPanel
     */
    private void initializeCoords(JPanel myPanel, JPanel btnPanel)
    {
    	xCoords = new JTextField("Starting Coords");
        yCoords = new JTextField("Ending Coords");

        btnPanel.add(xCoords);
        btnPanel.add(yCoords);
        initializeButtons(myPanel, btnPanel);
    }
    
    /**
     * Initializes the buttons for the panels
     * @param myPanel
     * @param btnPanel
     */
    private void initializeButtons(JPanel myPanel, JPanel btnPanel) 
    {
        button = new JButton("Solve it!");
        button.setEnabled(false);
        solvable = new JButton("Is it Solvable?");
        solvable.addActionListener(this.cntrlr);
        button.addActionListener(this.cntrlr);
        btnPanel.add(solvable);
        btnPanel.add(button);
        myPanel.add(btnPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Sets up the panel itself
     * @return
     */
    private JPanel initializePanel() 
    {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(750,600));
        myPanel.setLayout(new BorderLayout());
        return myPanel;
    }
 
    /**
     * Sets up the menu itself
     * @param window
     */
    private void setUpMenu(JFrame window) 
    {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_M);
        JMenuItem open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        open.addActionListener(this.cntrlr);
        save.setEnabled(false);
        save.addActionListener(this.cntrlr);
        file.add(open);
        file.add(save);
        menubar.add(file);
        window.setJMenuBar(menubar);
    } 
    
    /**
     * Enables the save button
     */
    public void enableSave()
    {
    	save.setEnabled(true);
    }
    
    /**
     * Says the maze isn't solvable
     */
    public void falseSolved()
    {
    	this.isSolved = false;
    }
    
    /**
     * Sets the maze as solvable
     */
    public void trueSolved()
    {
    	this.isSolved = true;
    }
    
    /**
     * Enables solve button
     */
    public void enableButton()
    {
    	button.setEnabled(true);
    }
    
    /**
     * Shows the message given input
     * @param message
     */
    public void showMessage(String message)
    {
		JOptionPane.showMessageDialog(myFrame, message);
    }
    
    /**
     * Adds one panel into another
     * @param thePanel
     * @param tbaPannel
     */
    public void addToPanel(JPanel thePanel, JPanel tbaPannel)
    {
    	thePanel.add(tbaPannel);
    }
    
    /**
     * Actually saves the file itself
     * @throws IOException
     */
    public void saveTheFile() throws IOException
    {
		JFileChooser jFileChooser = new JFileChooser();
		String output = "mySol_";
		output = output + this.tboFile.getName();
		jFileChooser.setSelectedFile(new File(output));
    	jFileChooser.setCurrentDirectory(new java.io.File("."));
		if(this.outputImage != null)
		{
			ImageIO.write(this.outputImage, "bmp", new File(output));
			jFileChooser.showSaveDialog(this.picLabel);
		}
    }
   
    /**
     * Sets the picture into the panel
     * @throws IOException
     */
    public void setPicture() throws IOException
    {
    	BufferedImage myPicture;
		myPicture = ImageIO.read(this.tboFile);
    	this.picLabel.setIcon(new ImageIcon(myPicture)); 
    	this.secPanel.add(this.panel);
        this.panel.add(this.picLabel);
        this.myPanel.add(this.secPanel);  
    }
  
    /**
     * The function that's called to show the image in the gui
     * @param sol
     */
    public void showPic(BufferedImage sol)
    {
    	this.outputImage = sol;
       	this.picLabel.setIcon(new ImageIcon(sol)); 
    	this.addToPanel(this.secPanel, this.panel);
        this.panel.add(this.picLabel);
        this.addToPanel(this.myPanel, this.secPanel);
        this.myFrame.pack();
    }
}

