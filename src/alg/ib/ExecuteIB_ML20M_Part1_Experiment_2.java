/*
 * Effect of neighbourhood threshold on predictions:
 * ** Using the similarity thresholding approach, plot overall RMSE and
 *	  coverage versus threshold for the deviation from item-mean predictor.
 * ** Use thresholds of 0, 0.05, 0.10, 0.15, ..., 0.80.
 * ** Use Cosine similarity in this experiment.
 *
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib;

import java.io.File;

import similarity.metric.SimilarityMetric;
import similarity.metric.CosineMetric;
import alg.ib.neighbourhood.Neighbourhood;
import alg.ib.neighbourhood.NearestNeighbourhood_Similarity_Thresholding;
import alg.ib.predictor.DeviationFromItemMeanPredictor;
import alg.ib.predictor.Predictor;
import util.evaluator.Evaluator;
import util.reader.DatasetReader;

public class ExecuteIB_ML20M_Part1_Experiment_2 {
	public static void main(String[] args)
	{
		//Execution 1: Deviation from Item Mean
		System.out.println("Similarity Metric: Cosine\n"
		+ "Neighbourhood Formation: Neighbourhood Threshold\n"
		+ "Predictor: Deviation From Item Mean\n"
		+ "=============================================");
		for(double threshold_value = 0; threshold_value <= 0.81; threshold_value = threshold_value + 0.05){
			System.out.printf("At Neighbourhood Threshold: %.2f\n", threshold_value);
			run_part_1_experiment_2(threshold_value);
		}
		System.out.println("=============================================");
	}

	private static void run_part_1_experiment_2(double threshold_value) {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood_Similarity_Thresholding(threshold_value);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp2_Threshold_"+ threshold_value%.2f +".txt";
		
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
