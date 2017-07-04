/**
 * Compute the Pearson Weightage similarity between profiles
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package similarity.metric;

import java.util.Set;

import profile.Profile;

public class PearsonWeightageMetric implements SimilarityMetric
{
	/**
	 * constructor - creates a new PearsonWeightageMetric object
	 */
	public PearsonWeightageMetric()
	{
	}
		
	/**
	 * computes the similarity between profiles
	 * @param profile 1 
	 * @param profile 2
	 */
	public double getSimilarity(final Profile p1, final Profile p2)
	{
        double sum_r1 = 0;
        double sum_r1_sq = 0;
        double sum_r2 = 0;
        double sum_r2_sq = 0;
        double sum_r1_r2 = 0;
        
        // Set of all common ids in profile 1 and profile 2
        Set<Integer> common = p1.getCommonIds(p2);
        
        /**
         * @param significance_weight_constant - Setting significance weightage constant = 100 
         */
        double significance_weight_constant = 100;
        
        // Get common ratings on profile 1 and profile 2
        for(Integer id: common)
		{
			double r1 = p1.getValue(id).doubleValue();
			double r2 = p2.getValue(id).doubleValue();

			sum_r1 += r1;
            sum_r1_sq += r1 * r1;
            sum_r2 += r2;
            sum_r2_sq += r2 * r2;
            sum_r1_r2 += r1 * r2;
		}
		
        // Weightage Pearson Implementation
		double above = (common.size() > 0) ? sum_r1_r2 - (sum_r1 * sum_r2) / common.size() : 0;
		double below = (common.size() > 0) ? Math.sqrt( (sum_r1_sq - (sum_r1 * sum_r1) / common.size()) * (sum_r2_sq - (sum_r2 * sum_r2) / common.size()) ) : 0;
	
		// If common ratings size < weight constant -- multiply Pearon's score with (common ratings size / weight constant)
		// Otherwise keep the pearson's score as it is.
		return (below > 0) ? ( (common.size() < significance_weight_constant) ? ( (above / below) * (common.size() / significance_weight_constant) ) : (above / below) ) : 0;
	}
}
