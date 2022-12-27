public class Crow {

    protected final int nVariables = Problem.nVariables;
    protected final int[][] restricciones = Problem.restricciones;

    // X es la posición del cuervo
    private int[] x = new int[nVariables];

    // La mejor posición = pBest; depende de las variables que tenga
    protected int[] pBest = new int[nVariables];

    // La iteración del cuervo; (cuervo seguido)
    private double[] v = new double[nVariables];

    // AP
    double AP = 0.5;
    // Largo del vuelo
    float flightLength;

    public Crow() {
        for (int j = 0; j < nVariables; j++) {
            x[j] = StdRandom.uniform(2);
            v[j] = StdRandom.uniform();
        }
    }

    protected boolean isBetterThanPBest() {
        return fitness() > fitnessPBest();
    }

    protected boolean isBetterThan(Crow g) {
        return fitness() > g.fitness();
    }

    protected void updatePBest() {
        System.arraycopy(x, 0, pBest, 0, x.length);
    }

    private float fitness() {
        float beneficio = 0;
        for (int i = 0; i < nVariables; i++) {
            beneficio += x[i] * Problem.coste[i];
            /* System.out.println(beneficio) */;
        }
        return beneficio;
    }

    // Fitness de la memoria
    private float fitnessPBest() {
        float benefi = 0;
        for (int i = 0; i < nVariables; i++) {
            benefi += pBest[i] * Problem.coste[i];
        }
        return benefi;
    }

    protected boolean isFeasible() {
        boolean isFeasible;
        int cont = 0;
        for (int i = 0; i < restricciones.length; i++) {
            // System.out.println(restricciones.length);
            int suma = 0;
            for (int j = 0; j < restricciones[0].length; j++) {
                suma += x[i] * restricciones[i][j];

            }
            if (suma >= 1) {
                cont = cont + 1; // Restriccion i cubierta
            }
            // System.out.println(java.util.Arrays.deepToString(restricciones));
        }
        isFeasible = (cont == restricciones.length) ? true : false;

        return isFeasible;
    }

    protected void move(Crow followedCrow, float AP, float flightLenght) {

        for (int j = 0; j < nVariables; j++) {
            if (StdRandom.uniform() >= AP) {
                x[j] = (int) (x[j] + StdRandom.uniform() * flightLength * (followedCrow.pBest[j] - x[j]));
            } else {
                x[j] = StdRandom.uniform(2);
            }
            x[j] = toBinary(x[j] + v[j]);

        }
    }

    protected void copy(Object object) {
        if (object instanceof Crow) {
            System.arraycopy(((Crow) object).x, 0, this.x, 0, nVariables);
            System.arraycopy(((Crow) object).pBest, 0, this.pBest, 0, nVariables);
            System.arraycopy(((Crow) object).v, 0, this.v, 0, nVariables);
        }
    }

    private int toBinary(double x) {
        return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
    }

    @Override
    public String toString() {
        return String.format(
                " Fitness: %.1f, x: %s",

                fitness(),
                java.util.Arrays.toString(x));
    }
}