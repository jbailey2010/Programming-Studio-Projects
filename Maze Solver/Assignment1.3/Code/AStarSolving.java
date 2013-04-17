package Code;
/**
 * The extension of distanceMethods that takes the heuristics into account, unlike Dijkstra's
 * @author Jeff
 *
 */
public class AStarSolving extends DistanceMethods{
	
	/**
	 * To help in the refactoring of the A* algorithm, it helps decide if the
	 * movement is to be diagonal or straight in one direction, then calculates MEstCost,
	 * and then finally total cost
	 * @param currNode -- the current node
	 * @param otherNode -- the potential other node
	 * @param tbsMaze -- the maze for which the calculations are being made
	 * @return the value to be added to the accumulated cost
	 */
	public void distanceUpdater(Nodes currNode, Nodes otherNode, Mazes tbsMaze, int heurChoice)
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
		if(heurChoice == 1) 
		{ 
			ManhattanEstCost distanceUpdater = new ManhattanEstCost();
			currNode.estCost = distanceUpdater.estimatedCost(currNode,tbsMaze.nodeMaze[tbsMaze.end.xCoord][tbsMaze.end.yCoord]);
		}
		else if(heurChoice == 2) 
		{ 
			DiagonalEstCost distanceUpdater = new DiagonalEstCost();
			currNode.estCost = distanceUpdater.estimatedCost(currNode,tbsMaze.nodeMaze[tbsMaze.end.xCoord][tbsMaze.end.yCoord]);
		}
		else 
		{
			EuclideanEstimatedCost distanceUpdater = new EuclideanEstimatedCost();
			currNode.estCost = distanceUpdater.estimatedCost(currNode,tbsMaze.nodeMaze[tbsMaze.end.xCoord][tbsMaze.end.yCoord]);
		}

	}
}
