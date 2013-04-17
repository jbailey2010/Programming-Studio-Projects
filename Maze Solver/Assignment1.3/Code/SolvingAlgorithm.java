package Code;

/**
 * The interface to be used in solving the maze, it's primary accomplishment
 * being that it will make the different solving algorithms more modular
 * @author Jeff
 *
 */
public interface SolvingAlgorithm {
	public char[][] solveTheMaze(Mazes tbsMaze, int heurChoice);
}
