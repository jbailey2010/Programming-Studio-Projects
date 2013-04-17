package Code;

/**
 * The second estimated cost function implemented, doing the
 * euclidean estimated cost equation
 * @author Jeff
 *
 */
public class EuclideanEstimatedCost implements EstimatedCost{

	/**
	 * This version of estimated cost does the euclidean version
	 */
	@Override
	public double estimatedCost(Nodes currLoc, Nodes end) {
		return 100 * Math.sqrt(Math.pow((currLoc.location.xCoord-end.location.xCoord),2) + 
				Math.pow((currLoc.location.yCoord-end.location.yCoord),2));  
	}




}
