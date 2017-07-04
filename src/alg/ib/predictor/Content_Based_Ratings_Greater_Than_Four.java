/**
 * A class which implements Content Based Filtering to predict rating with set of all movies rated >= 4 by user A
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib.predictor;

import java.util.Map;

import alg.ib.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class Content_Based_Ratings_Greater_Than_Four implements Predictor
{
	/**
	 * constructor - creates a new SimpleAveragePredictor object
	 */
	public Content_Based_Ratings_Greater_Than_Four()
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
		double above = 0;
		int counter = 0;
		
		for(Integer id: userProfileMap.get(userId).getIds()) // iterate over the target user's items
		{
			// If ratings greater than 4.
			if(userProfileMap.get(userId).getValue(id) >= 4){
				above += simMap.getSimilarity(itemId, id);
				counter++;
			}
		}
		
		return (counter > 0) ? new Double(above / counter) : null;
	}
}

