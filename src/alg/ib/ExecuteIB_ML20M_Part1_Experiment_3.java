/*
 * Effect of similarity metric on predictions:
 * ** Using the deviation from item-mean predictor, compare the overall RMSE and coverage achieved by the
 *    following similarity metrics: 
 *        Pearson correlation, 
 *        Pearson correlation with significance weighting (N = 100), 
 *        Cosine.
 * ** Use nearest neighbourhood formation with a neighbourhood size of 100 in this experiment.
 * 
 * Rajat Agrawal
 * 01/03/2017
 */

package alg.ib;

import java.io.File;

import similarity.metric.SimilarityMetric;
import similarity.metric.CosineMetric;
import similarity.metric.PearsonMetric;
import similarity.metric.PearsonWeightageMetric;
import alg.ib.neighbourhood.Neighbourhood;
import alg.ib.neighbourhood.NearestNeighbourhood;
import alg.ib.predictor.DeviationFromItemMeanPredictor;
import alg.ib.predictor.Predictor;
import util.evaluator.Evaluator;
import util.reader.DatasetReader;

public class ExecuteIB_ML20M_Part1_Experiment_3 {
	public static void main(String[] args)
	{
		//Execution 1: Pearson
		System.out.println("Execution #1:\n"
				+ "Similarity Metric: Pearson\n"
				+ "Neighbourhood Formation: Nearest Neighbour(100)\n"
				+ "Predictor: Deviation From Item Mean\n"
				+ "=============================================");
		run_part_1_experiment_3_pearson();
		System.out.println("=============================================");
		//Execution 2: Pearson Weightage
		System.out.println("Execution #2:\n"
				+ "Similarity Metric: Pearson Weightage(N = 100)\n"
				+ "Neighbourhood Formation: Nearest Neighbour(100)\n"
				+ "Predictor: Deviation From Item Mean\n"
				+ "=============================================");
		run_part_1_experiment_3_pearson_weightage();
		System.out.println("=============================================");
		//Execution 3: Cosine
		System.out.println("Execution #3:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Nearest Neighbour(100)\n"
				+ "Predictor: Deviation From Item Mean\n"
				+ "=============================================");
		run_part_1_experiment_3_cosine();
		System.out.println("=============================================");
	}

	private static void run_part_1_experiment_3_cosine() {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(100);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp3_Cosine.txt";
		
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

	private static void run_part_1_experiment_3_pearson_weightage() {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(100);
		SimilarityMetric metric = new PearsonWeightageMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp3_Pearson_Weightage.txt";
		
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

	private static void run_part_1_experiment_3_pearson() {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(100);
		SimilarityMetric metric = new PearsonMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp3_Pearson.txt";
		
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
