/**
 * A class that implements the "neighbourhood threshold" technique (item-based CF)
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib.neighbourhood;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import similarity.SimilarityMap;
import profile.Profile;
import util.ScoredThingDsc;

public class NearestNeighbourhood_Similarity_Thresholding extends Neighbourhood
{
	private final double T; // similarity threshold 
	
	/**
	 * constructor - creates a new NearestNeighbourhood_Similarity_Thresholding object
	 * Similarity thresholding – for each item i, the neighbourhood consists
	 * of all items with similarity (to item i) in excess of a threshold, T
	 */
	public NearestNeighbourhood_Similarity_Thresholding(final double T)
	{
		super();
		this.T = T;
	}

	/**
	 * stores the neighbourhoods for all items in member Neighbour.neighbourhoodMap
	 * @param simMap - a map containing item-item similarities
	 */
	public void computeNeighbourhoods(final SimilarityMap simMap)
	{
		// iterate over each item
		for(Integer itemId: simMap.getIds()) 
		{
			// for the current item, store all similarities in order of descending similarity in a sorted set
			SortedSet<ScoredThingDsc> ss = new TreeSet<ScoredThingDsc>(); 
			
			// get the item similarity profile
			Profile profile = simMap.getSimilarities(itemId); 
			if(profile != null)
			{
				// iterate over each item in the profile
				for(Integer id: profile.getIds()) 
				{
					double sim = profile.getValue(id);
					// If similarity threshold is greater than T, store it in the SortedSet
					if(sim > T)	
						ss.add(new ScoredThingDsc(sim, id));
				}
			}
			
			// Get all the values with similarity greater than Threshold T.
			for(Iterator<ScoredThingDsc> iter = ss.iterator(); iter.hasNext(); )
			{
				ScoredThingDsc st = iter.next();
				Integer id = (Integer)st.thing;
				this.add(itemId, id);
			}
		}
	}
}
