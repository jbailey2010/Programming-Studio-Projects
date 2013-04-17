package Code;
/**
 * A common interface for all of the various estimated cost functions
 * Used mostly for making it as pluggable and modular as possible.
 * @author Jeff
 *
 */
public interface EstimatedCost {

	public double estimatedCost(Nodes currLoc, Nodes end);
}
