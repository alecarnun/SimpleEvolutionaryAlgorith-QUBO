package es.uma.informatica.misia.ae.simpleea;

public class QUBOProblem {
    private final double[][] Q; // QUBO matrix
    private final int n; // Size of the problem

    public QUBOProblem(double[][] Q) {
        this.Q = Q;
        this.n = Q.length;
    }
}
