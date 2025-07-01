package es.uma.informatica.misia.ae.simpleea;

public class QUBOProblem {
    private final double[][] Q; // QUBO matrix
    private final int n; // Size of the problem

    public QUBOProblem(double[][] Q) {
        this.Q = Q;
        this.n = Q.length;
    }
    public double fitness(int[] solution) {
        if (solution.length != n) {
            throw new IllegalArgumentException("The size of the solution must be equal to the length of the QUBO problem.");
        }
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += Q[i][j] * solution[i] * solution[j];
            }
        }
        return result;
    }
}
