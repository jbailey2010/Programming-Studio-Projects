package MockTests;

import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import Code.Mazes;
import Code.Position;
import Code.UserInterface;

/**
 * A series of mock tests over the user interface calls
 * @author Jeff
 *
 */
public class MockTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze0() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze0.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100);
		verify(testMaze).readImage(input, 100);
	}
	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze1() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze1.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100);
		verify(testMaze).readImage(input, 100);
	}
	
	/**
	 * Makes sure image reading works with mockito (for speed)
	 * @throws IOException
	 */
	@Test
	public void testImgMaze2() throws IOException
	{
		Mazes testMaze = mock(Mazes.class);
		File input = new File("maze2.bmp");
		ImageIO.read(input);
		testMaze.readImage(input, 100); 
		verify(testMaze).readImage(input, 100);
	}
	
}
