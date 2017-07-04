/**
 * A class that implements the Hybrid approach
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

public class HybridApproach extends Neighbourhood
{
	private final int k; // the number of neighbors in the neighborhood
	private final double t; // Threshold value considered to create the neighborhood
	
	/**
	 * constructor - creates a new NearestNeighbourhood object
	 * @param k - the number of neighbours in the neighbourhood
	 */
	public HybridApproach(final int k, final double t)
	{
		super();
		this.k = k;
		this.t = t;
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
					if(sim > t)
						ss.add(new ScoredThingDsc(sim, id));
				}
			}

			// Similarity Threshold and get the k most similar items (neighbours)
			int counter = 0;
			for(Iterator<ScoredThingDsc> iter = ss.iterator(); iter.hasNext() && counter < k; )
			{
				ScoredThingDsc st = iter.next();
				Integer id = (Integer)st.thing;
				this.add(itemId, id);
				counter++;
			}
		}
	}
}