/**
 * 
 */
package Code;

import java.util.LinkedList;

/**
 *Gives the variables that will hold the cost values,
 *whether it's in the open list or closed list,
 *a unique ID and a parent pointer,
 *and a location (x and y coordinates)  
 * @author Jeff
 */ 
public class Nodes implements Comparable<Nodes> {
	public double estCost, accumCost, totalCost;
	public boolean isOpen;
	public boolean isClosed;
	public int terrainStatus; //1 is wall...
	public final int id;
	public Position location;
	public Nodes parent;
	
	/**
	 * Constructor for the node class, given the inputs below
	 * the estCost and accumCost are set to 0, isOpen and
	 * isClosed to false, and parent node to null
	 * @param xPosition -- the x coordinate of the location of the node
	 * @param yPosition -- the y coordinate of the location of the node
	 * @param oldId -- the id of the parent node (previous one)
	 */
	public Nodes(int xPos, int yPos, int oldId, int terrainStatus)
	{
		this.setEstCost(0);
		this.setAccumCost(0);
		this.totalCost=0;
		this.id = oldId+1;
		this.setOpen(false);
		this.setClosed(false);
		this.setWall(terrainStatus); 
		this.setParent(null);
		this.location = new Position(xPos, yPos);
	}
	
	/**
	 * A helper function to compare the total costs of two different nodes
	 * This is a refactored version of the function from the Square.java file given to us
	 * @param otherNode -- takes the node to be compared with the calling node
	 * @returns 1 if the calling node has greater cost, -1 if less, and 0 if it's equal
	 */
	public int compareTo(Nodes otherNode) 
	{ 
		if(this.getTotalCost() > otherNode.getTotalCost())
		{
			return 1;
		}
		else if(this.getTotalCost() <= otherNode.getTotalCost())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * Returns a list of neighbors of a calling node, walls excluded.
	 * It uses the isNeighbor function to know what is a neighbor and what isn't.
	 * @param parentMaze -- the maze to work through and find neighbors inside of (here for member variables mostly)
	 * @returns the linked list of neighbors
	 */
	public LinkedList<Nodes> findNeighbors(Mazes parentMaze, int index)
	{
		LinkedList<Nodes> neighbors = new LinkedList<Nodes>();
		for(int i = -1; i < 2; i++)
		{ 
			for(int j = -1; j < 2; j++)
			{
				if(this.location.xCoord+i >= 0 && this.location.xCoord+i < parentMaze.width)
				{
					if(this.location.yCoord + j >= 0 && this.location.yCoord + j < parentMaze.height)
					{
						if(index == 1)
						{
							if(this.isNeighbor(parentMaze.nodeMaze[this.location.xCoord + i][this.location.yCoord+j])==1)
							{
								neighbors.add(parentMaze.nodeMaze[this.location.xCoord + i][this.location.yCoord+j]);
							}
						}
					}
				}
			}
		}
		return neighbors;
	}
	
	/**
	 * Checks to see if a given node is a neighbor to the calling node,
	 * to be used as a helper in findNeighbor
	 * @param otherNode -- the node that may or may not be a neighbor
	 * @return 0 for not a neighbor, 1 for is a neighbor, 2 for is a wall, -1 for is the same
	 */
	public int isNeighbor(Nodes otherNode)
	{
		if(this.location.xCoord != otherNode.location.xCoord || this.location.yCoord != otherNode.location.yCoord)
		{
			if(Math.abs(this.location.xCoord - otherNode.location.xCoord) >= 2) 
			{
				return 0;
			}
			else if(Math.abs(this.location.yCoord - otherNode.location.yCoord)>= 2)
			{
				return 0;
			}
			else if(otherNode.terrainStatus != 0  || otherNode.isClosed == true)// || otherNode.isOpen == true)
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * returns the estimated cost remaining
	 * @returns the estimated cost of the calling node
	 */
	public double getEstCost() 
	{
		return estCost;
	}

	/**
	 * Establishes the estimated cost
	 * @param estCost, the estimated cost to be set to the node
	 */
	public void setEstCost(double estCost) 
	{
		this.estCost = estCost;
	}

	/**
	 * Gets the accumulated cost of the calling node
	 * @returns the accumulated cost of the calling node
	 */
	public double getAccumCost() 
	{
		return accumCost;
	}

	/**
	 * Establishes the accumulated cost in relation to the calling node
	 * @param accumCost, the variable to be linked to the node
	 */
	public void setAccumCost(double accumCost) 
	{
		this.accumCost = accumCost;
	}
	
	/**
	 * @return the sum of the estimated cost and the accumulated cost
	 */
	public double getTotalCost()
	{
		if(this.totalCost == 0)
		{
			return this.getAccumCost() + this.getEstCost();
		}
		else
		{
			return this.totalCost;
		}
	}
	
	/**
	 * @returns whether or not the node is in the open list
	 */
	public boolean isOpen() 
	{
		return isOpen;
	}

	/**
	 * @param isOpen -- the boolean value to establish whether or not the calling node is in the open list
	 */
	public void setOpen(boolean isOpen) 
	{
		this.isOpen = isOpen;
	}

	/**
	 * @return whether or not the calling node is in the closed list
	 */
	public boolean isClosed() 
	{
		return isClosed;
	}

	/**
	 * @param isClosed -- the boolean to establish whether or not the calling node is in the closed list
	 */
	public void setClosed(boolean isClosed)
	{
		this.isClosed = isClosed;
	}

	/**
	 * @return the id of the calling node
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * @returns the parent node of the calling node
	 */
	public Nodes getParent() 
	{
		return parent;
	}

	/**
	 * @param parent is the parent node to link to the calling node
	 */
	public void setParent(Nodes parent) 
	{
		this.parent = parent;
	}

	/**
	 * @return whether or not the calling node is a 'wall'
	 */
	public int terrainStatus() 
	{
		return terrainStatus;
	}

	/**
	 * @param terrainStatus -- the boolean to be linked to the node to establish if it is a wall
	 */
	public void setWall(int terrainStatus) 
	{
		this.terrainStatus = terrainStatus;
	}

	/**
	 * A new equals operator, adapted from the square.java equals operator,
	 * to be used primarily with testing
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Nodes)
		{
			return 	(this.location.xCoord == ((Nodes)other).location.xCoord) && 
					(this.location.yCoord == ((Nodes)other).location.yCoord) &&
					(this.estCost == ((Nodes)other).estCost) &&
					(this.accumCost == ((Nodes)other).accumCost) &&
					(this.id == ((Nodes)other).id) && 
					(this.parent == ((Nodes)other).parent) &&
					this.isOpen == ((Nodes)other).isOpen && 
					this.isClosed == ((Nodes)other).isClosed &&
					this.terrainStatus == ((Nodes)other).terrainStatus;
		}
		return false; 
	}
	
	
}
 