package Tests;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;


/**
 * Runs all tests I wrote to test the library, all the tests imported here. Life is easy.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AStarTest.class, ViewTests.class, MockTests.MockTests.class, DjikstraTest.class, 
	MazeTests.class, NodeTests.class, DistanceTests.class, TestUserInterface.class})
public class TestGroup 
{

}