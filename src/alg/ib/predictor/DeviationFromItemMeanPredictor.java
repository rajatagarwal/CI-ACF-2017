/**
 * A class to compute the target user's predicted rating for the target item (item-based CF) using the Deviation from Item Mean technique.
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib.predictor;

import java.util.Map;

import alg.ib.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class DeviationFromItemMeanPredictor implements Predictor
{
	/**
	 * constructor - creates a new SimpleAveragePredictor object
	 */
	public DeviationFromItemMeanPredictor()
	{
	}
	
	/**
	 * @returns the target user's predicted rating for the target item or null if a prediction cannot be computed
	 * @param userId - the numeric ID of the target user
	 * @param itemId - the numerid ID of the target item
	 * @param userProfileMap - a map containing user profiles
	 * @param itemProfileMap - a map containing item profiles
	 * @param neighbourhood - a Neighbourhood object
	 * @param simMap - a SimilarityMap object containing item-item similarities
	 */
	public Double getPrediction(final Integer userId, final Integer itemId, final Map<Integer,Profile> userProfileMap, final Map<Integer,Profile> itemProfileMap, final Neighbourhood neighbourhood, final SimilarityMap simMap)	
	{
		double ratings = 0;
		double similarities = 0;
		double mean_rating_of_item_j = 0;
		
		//get mean rating of item J -> target item.
		mean_rating_of_item_j = itemProfileMap.get(itemId).getMeanValue();
		
		// iterate over the target user's items
		for(Integer id: userProfileMap.get(userId).getIds()) 
		{
			// the current item is in the neighbourhood
			if(neighbourhood.isNeighbour(itemId, id)) 
			{
				//sum of all rating for user A on item I
				Double rating = userProfileMap.get(userId).getValue(id);
				//similarity between item I and target item J
				Double similarity = simMap.getSimilarity(id, itemId);
				//mean rating of item I
				Double mean_rating = itemProfileMap.get(id).getMeanValue();
				//sum of product of "all rating for user A on item I - mean rating of item I" and "similarity between item I and target item J
				ratings += (similarity * (rating - mean_rating));
				//sum of similarity between item I and target item J
				similarities += similarity;
			}
		}
		// return deviation from item mean score.
		return new Double(mean_rating_of_item_j + ((similarities > 0) ? (ratings / similarities) : 0 ));
	}
}

