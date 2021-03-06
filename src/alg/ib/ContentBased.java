/**
 * A class to implement the Content based filtering algorithm
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib;

import alg.CFAlgorithm;
import alg.ib.neighbourhood.Neighbourhood;
import alg.ib.predictor.Predictor;
import similarity.SimilarityMap;
import similarity.metric.SimilarityMetric;
import util.reader.DatasetReader;

public class ContentBased implements CFAlgorithm
{
	private Predictor predictor; // the predictor technique  
	private Neighbourhood neighbourhood; // the neighbourhood technique
	private DatasetReader reader; // dataset reader
	private SimilarityMap simMap; // similarity map - stores all item-item similarities
	
	/**
	 * constructor - creates a new UserBasedCF object
	 * @param predictor - the predictor technique
	 * @param neighbourhood - the neighbourhood technique
	 * @param metric - the item-item similarity metric
	 * @param reader - dataset reader
	 */
	public ContentBased(final Predictor predictor, final Neighbourhood neighbourhood, final SimilarityMetric metric, final DatasetReader reader)
	{
		this.predictor = predictor;
		this.neighbourhood = neighbourhood;
		this.reader = reader;
		// it takes genome score file
		this.simMap = new SimilarityMap(reader.getItemGenomeScores(), metric);
		//this.simMap = new SimilarityMap(reader.getItemProfiles(), metric); // compute all item-item similarities
		this.neighbourhood.computeNeighbourhoods(simMap); // compute the neighbourhoods for all items
	}
	
	/**
	 * @returns the target user's predicted rating for the target item or null if a prediction cannot be computed
	 * @param userId - the target user ID
	 * @param itemId - the target item ID
	 */
	public Double getPrediction(final Integer userId, final Integer itemId)
	{	
		// it takes genome score file
		//return predictor.getPrediction(userId, itemId, reader.getUserProfiles(), reader.getItemProfiles(), neighbourhood, simMap);
		return predictor.getPrediction(userId, itemId, reader.getUserProfiles(), reader.getItemGenomeScores(), neighbourhood, simMap);
	}
}