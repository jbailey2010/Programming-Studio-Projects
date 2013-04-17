package Code;

import java.util.LinkedList;

/**
 * The whole distanceMethods function, that updates distances differently depending on the 
 * algorithm choice
 * @author Jeff
 *
 */
public abstract class DistanceMethods implements SolvingAlgorithm{
 
	/**
	 * What the different algorithms call
	 * @param startNbr -- the current node
	 * @param firstNode -- the potential neighbor
	 * @param tbsMaze -- the 'to be solved' maze
	 */
	abstract void distanceUpdater(Nodes startNbr, Nodes firstNode, Mazes tbsMaze, int heurChoice);
	/**
	 * The helper function that does most of the work in the function
	 * @param tbsMaze, the pluggable maze that is to be solved
	 */
	public char[][] solveTheMaze(Mazes tbsMaze, int heurChoice)
	{ 
		Nodes firstNode = tbsMaze.nodeMaze[tbsMaze.beginning.xCoord][tbsMaze.beginning.yCoord];
		while(firstNode.location.xCoord != tbsMaze.end.xCoord || firstNode.location.yCoord != tbsMaze.end.yCoord)
		{
			firstNode.isClosed = true;
			LinkedList<Nodes> neighborsList = firstNode.findNeighbors(tbsMaze, 1);
			while(neighborsList.size() > 0) 
			{ 
				Nodes startNbr = neighborsList.removeFirst(); 
				if(startNbr.isOpen == false)
				{
					isNotOpen(firstNode, startNbr, tbsMaze, heurChoice);
				}
				else
				{
					isOpen(firstNode, startNbr, tbsMaze, heurChoice);
				}
			}
			if(tbsMaze.openList.size() > 0)
			{
				firstNode = tbsMaze.openList.remove();
			}
			else
			{
				System.out.println("No solution found...");
				break;
			}
		}
		return tbsMaze.drawMazeSol(firstNode);
	}
	
	/**
	 * the method to be called if the node pulled is not on the open list
	 * @param firstNode -- the node to be checked relative to
	 * @param startNbr -- the potential neighbor
	 * @param tbsMaze -- the parent maze itself
	 * @param heurChoice -- the algorithm choice, to be used with distanceUpdater
	 */
	public void isNotOpen(Nodes firstNode, Nodes startNbr, Mazes tbsMaze, int heurChoice)
	{
		startNbr.parent = firstNode;
		distanceUpdater(startNbr, firstNode, tbsMaze, heurChoice); 
		startNbr.isOpen = true; 
		tbsMaze.openList.add(startNbr);
	}
	
	/**
	 * The function to be called if the neighbor pulled off is, in fact, on the open list
	 * @param firstNode -- the node to be checked relative to
	 * @param startNbr -- the node to be checked
	 * @param tbsMaze -- the maze itself
	 * @param heurChoice -- the heuristic choice 
	 */
	public void isOpen(Nodes firstNode, Nodes startNbr, Mazes tbsMaze, int heurChoice)
	{
		Nodes tempHolder = new Nodes(startNbr.location.xCoord, startNbr.location.yCoord, startNbr.id, startNbr.terrainStatus);
		tempHolder.parent = firstNode;
		distanceUpdater(tempHolder,firstNode,tbsMaze,heurChoice);
		tempHolder.totalCost = tempHolder.accumCost + tempHolder.estCost;
		if(tempHolder.accumCost <= startNbr.accumCost)
		{
			boolean checker = tbsMaze.openList.remove(startNbr);
			tempHolder.parent = firstNode; 
			if(checker)
			{
				tbsMaze.openList.add(tempHolder);
			}
		}
	}
}
	