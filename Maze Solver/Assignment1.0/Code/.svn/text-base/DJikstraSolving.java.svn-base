package Code;

public class DJikstraSolving extends DistanceMethods{
	/**
	 * To help in the refactoring of the A* algorithm, it helps decide if the
	 * movement is to be diagonal or straight in one direction, then calculates MEstCost,
	 * and then finally total cost
	 * @param currNode -- the current node
	 * @param otherNode -- the potential other node
	 * @param tbsMaze -- the maze for which the calculations are being made
	 * @return the value to be added to the accumulated cost
	 */
	public void distanceUpdater(Nodes currNode, Nodes otherNode, Mazes tbsMaze)
	{
		currNode.accumCost = currNode.parent.accumCost;
		if(currNode.location.xCoord != otherNode.location.xCoord && currNode.location.yCoord != otherNode.location.yCoord)
		{
			currNode.accumCost += 141;
		}
		else
		{
			currNode.accumCost += 100;
		}
		currNode.totalCost = currNode.getTotalCost();
	}

}
