package Code;

/**
 * The Manhattan Estimated Cost class, for use in the A* algorithm primarily
 * @author Jeff
 *
 */
public class ManhattanEstCost implements EstimatedCost{

	/**
	 * The function that defines the estimated cost from a node to the end
	 * of the maze
	 */
	@Override
	public double estimatedCost(Nodes currLoc, Nodes end) {
		double xDistance = Math.abs(currLoc.location.xCoord - end.location.xCoord);
		double yDistance = Math.abs(currLoc.location.yCoord - end.location.yCoord);
		double mDistance = 100*(xDistance + yDistance);
		
		return mDistance;
	}

	
}
