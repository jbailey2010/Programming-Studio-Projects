package Code;

import java.util.LinkedList;

public abstract class DistanceMethods implements SolvingAlgorithm{
 
	abstract void distanceUpdater(Nodes startNbr, Nodes firstNode, Mazes tbsMaze);
	/**
	 * The helper function that does most of the work in the function
	 * @param tbsMaze, the pluggable maze that is to be solved
	 */
	public char[][] solveTheMaze(Mazes tbsMaze)
	{ 
		Nodes firstNode = tbsMaze.nodeMaze[tbsMaze.beginning.xCoord][tbsMaze.beginning.yCoord];
		
		while(firstNode.location.xCoord != tbsMaze.end.xCoord || firstNode.location.yCoord != tbsMaze.end.yCoord)
		{
			//Add the node being used to the closed list and find its neighbors
			firstNode.isClosed = true;
			LinkedList<Nodes> neighborsList = firstNode.findNeighbors(tbsMaze);

			while(neighborsList.size() > 0)
			{ 
				Nodes startNbr = neighborsList.removeFirst();
				startNbr.parent = firstNode;
				 
				distanceUpdater(startNbr, firstNode, tbsMaze); 

				startNbr.isOpen = true; 
				tbsMaze.openList.add(startNbr);
			}
			firstNode = tbsMaze.openList.remove();
		}
		return tbsMaze.drawMazeSol(firstNode);
	}
}
	