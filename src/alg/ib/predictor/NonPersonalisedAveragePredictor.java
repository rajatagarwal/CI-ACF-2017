/**
 * A class to compute the target user's predicted rating for the target item (item-based CF) using non personalized approach.
 * It returns mean rating for items as predictions.
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib.predictor;

import java.util.Map;

import alg.ib.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class NonPersonalisedAveragePredictor implements Predictor
{
	/**
	 * constructor - creates a new NonPersonalisedAveragePredictor object
	 */
	public NonPersonalisedAveragePredictor()
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
		// mean of item's overall rating.
		double mean_value = itemProfileMap.get(itemId).getMeanValue();

		return (mean_value > 0) ? mean_value : null;
	}
}

