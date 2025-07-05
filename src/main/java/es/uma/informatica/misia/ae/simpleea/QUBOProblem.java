package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class QUBOProblem implements Problem{
	private final double[][] Q; // QUBO matrix
	private int n; // Size of the problem
	private double optimalValue; // Optimal value of the problem

	public QUBOProblem(double[][] Q) {
		this.Q = Q;
		this.n = Q.length;
	}

	public double evaluate(Individual individual) {
		BinaryString binaryString = (BinaryString) individual;
		byte[] x = binaryString.getChromosome();
		double result = 0.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result += Q[i][j] * x[i] * x[j];
			}
		}
		return -result;
	}
	
	public BinaryString generateRandomIndividual(Random rnd) {
		return new BinaryString(n,rnd);
	}

	public double getOptimalValue() {
		return optimalValue;
	}
	public void setOptimalValue(double optimalValue) {
		this.optimalValue = optimalValue;
	}
}
