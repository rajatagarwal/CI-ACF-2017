/*
 * Implement a content-based recommender for set of all movies rated by user A
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib;

import java.io.File;

import similarity.metric.SimilarityMetric;
import similarity.metric.CosineMetric;
import alg.ib.neighbourhood.Neighbourhood;
import alg.ib.neighbourhood.NearestNeighbourhood;
import alg.ib.predictor.Content_Based_All_Ratings;
import alg.ib.predictor.Predictor;
import util.evaluator.Evaluator;
import util.reader.DatasetReader;

public class ExecuteIB_ML20M_Part2_Experiment_1 {
	public static void main(String[] args)
	{
		System.out.println("Content Based Approach:\n"
				+ "Prediction based on all movies rated by user A\n"
				+ "===============================================");
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new Content_Based_All_Ratings();
		Neighbourhood neighbourhood = new NearestNeighbourhood(100);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part2_Exp1.txt";
		
		////////////////////////////////////////////////
		// Evaluates the CF algorithm (do not change!!):
		// - the RMSE (if actual ratings are available) and coverage are output to screen
		// - output file is created
		DatasetReader reader = new DatasetReader(itemFile, itemGenomeScoresFile, trainFile, testFile);
		// Changed ItemBasedCF to ContentBased
		ContentBased ibcf = new ContentBased(predictor, neighbourhood, metric, reader);
		Evaluator eval = new Evaluator(ibcf, reader.getTestData());
		
		// Write to output file
		eval.writeResults(outputFile);
		
		// Display RMSE and coverage
		Double RMSE = eval.getRMSE();
		if(RMSE != null) System.out.printf("RMSE: %.6f\n", RMSE);
		
		double coverage = eval.getCoverage();
		System.out.printf("coverage: %.2f%%\n", coverage);
		
		System.out.println("===============================================");
	}
}
