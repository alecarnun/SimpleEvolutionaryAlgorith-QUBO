package es.uma.informatica.misia.ae.simpleea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main (String args []) throws IOException {
		
		if (args.length < 5) {
			System.err.println("Invalid number of arguments");
			System.err.println("Arguments: <population size> <function evaluations> <bitflip probability> <crossover probability> <problem size> [<random seed>]");
			return;
		}

        double[][] Q;
        try {
            Q = readQUBOMatrix("src/main/resources/qubo_instance_1.csv", 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Problem problem = new QUBOProblem(Q);
		
		Map<String, Double> parameters = readEAParameters(args);
		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(parameters, problem);
		
		Individual bestSolution = evolutionaryAlgorithm.run();
		System.out.println(bestSolution);
	}

	private static Map<String, Double> readEAParameters(String[] args) {
		Map<String, Double> parameters = new HashMap<>();
		parameters.put(EvolutionaryAlgorithm.POPULATION_SIZE_PARAM, Double.parseDouble(args[0]));
		parameters.put(EvolutionaryAlgorithm.MAX_FUNCTION_EVALUATIONS_PARAM, Double.parseDouble(args[1]));
		parameters.put(BitFlipMutation.BIT_FLIP_PROBABILITY_PARAM, Double.parseDouble(args[2]));
		parameters.put(SinglePointCrossover.CROSSOVER_PROBABILITY_PARAM, Double.parseDouble(args[3]));

		long randomSeed = System.currentTimeMillis();
		if (args.length > 5) {
			randomSeed = Long.parseLong(args[5]);
		}
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double)randomSeed);
		return parameters;
	}

	public static double[][] readQUBOMatrix(String filePath, int n) throws IOException {
		double[][] Q = new double[n][n];
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int row = 0;
			while ((line = br.readLine()) != null && row < n) {
				String[] values = line.split(",");
				for (int col = 0; col < n; col++) {
					Q[row][col] = Double.parseDouble(values[col]);
				}
				row++;
			}
		}
		return Q;
	}

}
