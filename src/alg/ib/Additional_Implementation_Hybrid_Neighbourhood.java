/*
 Find the effects of hybrid neighbourhood selection on prediction 
 Modified By: Rajat Agrawal
 01/03/2017
 */ 
 
package alg.ib;

import java.io.File;

import similarity.metric.SimilarityMetric;
import similarity.metric.CosineMetric;
import alg.ib.predictor.DeviationFromItemMeanPredictor;
import alg.ib.neighbourhood.Neighbourhood;
import alg.ib.neighbourhood.HybridApproach;
import alg.ib.predictor.Predictor;
import util.evaluator.Evaluator;
import util.reader.DatasetReader;

public class Additional_Implementation_Hybrid_Neighbourhood {
	public static void main(String[] args)
	{ 
		System.out.println("Execution:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Hybrid Approach\n"
				+ "     - Neighbourhood Size = 80\n"
				+ "     - Neighbourhood Threshold = 0.10\n"
				+ "Predictor: Deviation From Item Mean\n"
				+ "=============================================");
		run_hybrid_experiemnt();
		System.out.println("=============================================");
	}
	
	private static void run_hybrid_experiemnt() {
		// Best RMSE score was observed at threshold 0.10 and 80 top-n for this dataset.
		double threshold_value = 0.10; 
		int top_n = 80;
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new HybridApproach(top_n, threshold_value);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_hybrid_approach.txt";
		
		////////////////////////////////////////////////
		// Evaluates the CF algorithm (do not change!!):
		// - the RMSE (if actual ratings are available) and coverage are output to screen
		// - output file is created
		DatasetReader reader = new DatasetReader(itemFile, itemGenomeScoresFile, trainFile, testFile);
		ItemBasedCF ibcf = new ItemBasedCF(predictor, neighbourhood, metric, reader);
		Evaluator eval = new Evaluator(ibcf, reader.getTestData());
		
		// Write to output file
		eval.writeResults(outputFile);	
		
		// Display RMSE and coverage
		Double RMSE = eval.getRMSE();
		if(RMSE != null) System.out.printf("RMSE: %.6f\n", RMSE);
		
		double coverage = eval.getCoverage();
		System.out.printf("coverage: %.2f%%\n", coverage);
		}
}