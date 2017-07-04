/*
 * Effect of neighbourhood size on predictions:
 * ** Using the nearest neighbourhood approach, plot overall RMSE and
 *	  coverage versus neighbourhood size for each of the four predictors (non- personalised, simple average, weighted average, and deviation from item- mean)
 * ** Use neighbourhood sizes of 5, 10, 15, ..., 100.
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
import alg.ib.neighbourhood.NearestNeighbourhood;
import alg.ib.predictor.DeviationFromItemMeanPredictor;
import alg.ib.predictor.NonPersonalisedAveragePredictor;
import alg.ib.predictor.Predictor;
import alg.ib.predictor.SimpleAveragePredictor;
import alg.ib.predictor.WeightedAveragePredictor;
import util.evaluator.Evaluator;
import util.reader.DatasetReader;

public class ExecuteIB_ML20M_Part1_Experiment_1 {
	public static void main(String[] args)
	{
		//Execution 1: Non Personalised
		System.out.println("Execution #1:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Nearest Neighbour\n"
				+ "Predictor: Non Personalised\n"
				+ "=============================================");
		for(int i = 5; i <= 100; i = i+5){
			System.out.printf("At Neighbourhood Size: %d\n", i);
			run_part_1_experiment_1_non_personalised(i);
		}
		System.out.println("=============================================");
		//Execution 2: Simple Average
		System.out.println("Execution #2:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Nearest Neighbour\n"
				+ "Predictor: Simple Average\n"
				+ "=============================================");
		for(int i = 5; i <= 100; i = i+5){
			System.out.printf("At Neighbourhood Size: %d\n", i);
			run_part_1_experiment_1_simple_average(i);
		}
		System.out.println("=============================================");
		//Execution 3: Weighted Average
		System.out.println("Execution #3:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Nearest Neighbour\n"
				+ "Predictor: Weighted Average\n"
				+ "=============================================");
		for(int i = 5; i <= 100; i = i+5){
			System.out.printf("At Neighbourhood Size: %d\n", i);
			run_part_1_experiment_1_weighted_average(i);
		}
		System.out.println("=============================================");
		//Execution 4: Deviation from Item Mean
		System.out.println("Execution #4:\n"
				+ "Similarity Metric: Cosine\n"
				+ "Neighbourhood Formation: Nearest Neighbour\n"
				+ "Predictor: Deviation From Item Mean\n"
				+ "=============================================");
		for(int i = 5; i <= 100; i = i+5){
			System.out.printf("At Neighbourhood Size: %d\n", i);
			run_part_1_experiment_1_deviation_from_item_mean(i);
		}
		System.out.println("=============================================");
	}
	
	private static void run_part_1_experiment_1_weighted_average(int i) {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new WeightedAveragePredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(i);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp1_WtAvg_N_" + i + ".txt";
				
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

	private static void run_part_1_experiment_1_deviation_from_item_mean(int i) {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new DeviationFromItemMeanPredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(i);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp1_DeviationItemMean_N_" + i + ".txt";
				
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

	private static void run_part_1_experiment_1_non_personalised(int i) {
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new NonPersonalisedAveragePredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(i);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp1_NonPersonalised_N_" + i + ".txt";
				
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

	private static void run_part_1_experiment_1_simple_average(int i){
		// configure the item-based CF algorithm - set the predictor, neighbourhood and similarity metric ...
		Predictor predictor = new SimpleAveragePredictor();
		Neighbourhood neighbourhood = new NearestNeighbourhood(i);
		SimilarityMetric metric = new CosineMetric();
		
		// set the paths and filenames of the item file, genome scores file, train file and test file ...
		String itemFile = "ml-20m" + File.separator + "movies-sample.txt";
		String itemGenomeScoresFile = "ml-20m" + File.separator + "genome-scores-sample.txt";
		String trainFile = "ml-20m" + File.separator + "train.txt";
		String testFile = "ml-20m" + File.separator + "test.txt";
		// set the path and filename of the output file ...
		String outputFile = "results" + File.separator + "predictions_Part1_Exp1_SimpleAvg_N_" + i + ".txt";
				
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
