package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class SinglePointCrossover implements Crossover {

	private double crossoverProb;
	private Random rnd;
	public static final String CROSSOVER_PROBABILITY_PARAM = "crossoverProbability";

	public SinglePointCrossover (Random rnd, double crossoverProb) {
		this.rnd = rnd;
		this.crossoverProb = crossoverProb;
	}

	@Override
	public BinaryString apply(Individual individual1, Individual individual2) {
		BinaryString binaryParent1 = (BinaryString) individual1;
		BinaryString binaryParent2 = (BinaryString) individual2;
		
		BinaryString child = new BinaryString (binaryParent1);
		int cutPoint = rnd.nextInt(binaryParent1.getChromosome().length+1);
		for (int i=cutPoint; i < binaryParent1.getChromosome().length; i++) {
			if (rnd.nextDouble() < crossoverProb) {
				child.getChromosome()[i] = binaryParent2.getChromosome()[i];
			}
		}
		return child;
	}

	public double getCrossoverProb() {
		return crossoverProb;
	}

	public void setCrossoverProb(double crossoverProb) {
		this.crossoverProb = crossoverProb;
	}

}
