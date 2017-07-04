/**
 * Compute the Cosine similarity between profiles
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package similarity.metric;

import java.util.Set;
import profile.Profile;

public class CosineMetric implements SimilarityMetric {
	
	/**
	 * constructor - creates a new CosineMetric object
	 */
	public CosineMetric()
	{
	}
		
	/**
	 * computes the similarity between profiles
	 * @param profile 1 
	 * @param profile 2
	 */
	
	@Override
	public double getSimilarity(Profile p1, Profile p2) {
		double sum_r1_r2 = 0;
		double sum_r1_square = 0;
		double sum_r2_square = 0;
		
		// Set of all common ids in profile 1 and profile 2
		Set<Integer> common = p1.getCommonIds(p2);
		
		// Set of all ids in profile 1
		Set<Integer> all_p1 = p1.getIds();
		
		// Set of all ids in profile 2
		Set<Integer> all_p2 = p2.getIds();
		
		// Get all ratings on profile 1
		for(Integer id_p1: all_p1){
			double p1_r = p1.getValue(id_p1).doubleValue();
			sum_r1_square += p1_r * p1_r; 
		}
		
		// Get all ratings on profile 2
		for(Integer id_p2: all_p2){
			double p2_r = p2.getValue(id_p2).doubleValue();
			sum_r2_square += p2_r * p2_r;
		}
		
		// Get common ratings on profile 1 and profile 2
		for(Integer id: common)
		{
			double r1 = p1.getValue(id).doubleValue();
			double r2 = p2.getValue(id).doubleValue();
			sum_r1_r2 += r1 * r2;
		}
		
		// Cosine Implementation
		double above = sum_r1_r2;
		double below = Math.sqrt( (sum_r1_square) ) *  Math.sqrt( (sum_r2_square) );
		return (below > 0) ? above / below : 0;
	}
	
}
