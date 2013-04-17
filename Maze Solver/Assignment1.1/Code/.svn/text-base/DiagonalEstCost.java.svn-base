package Code;

/**
 * The diagonal version of estimated cost 
 * @author Jeff
 *
 */
public class DiagonalEstCost implements EstimatedCost{

	/**
	 * Here I implemented the interface with the diagonal equation
	 */
	@Override
	public double estimatedCost(Nodes currLoc, Nodes end) 
	{
		double diagonalN = Math.min(Math.abs(currLoc.location.xCoord-end.location.xCoord), 
				Math.abs(currLoc.location.yCoord-end.location.yCoord));
				
		double straightN = (Math.abs(currLoc.location.xCoord-end.location.xCoord) + 
				Math.abs(currLoc.location.yCoord-end.location.yCoord));
		
		double straightD = 100;
		double diagD = 141;
		return  diagD * diagonalN + straightD * (straightN - 2*diagonalN);
	}

}
