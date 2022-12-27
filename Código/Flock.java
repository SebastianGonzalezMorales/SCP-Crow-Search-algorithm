import java.util.Random;
import java.util.ArrayList;

public class Flock {

    private final int T = 500; // Iter Max
    private final int nAgents = 50; // Flock Size
    private final float AP = 0.1f, flightLenght = 2f; // Adjustable parameters

    private ArrayList<Crow> swarm = null;
    private Crow crow = null;
    protected final Random rnd = new Random();

    private long sTime, eTime;

    public void execute() {
        startTime();
        init();
        run();
        endTime();
        log();
    }

    private void startTime() {
        sTime = System.currentTimeMillis();
    }

    private void init() {
        // Verifico que el cuervo es factible
        swarm = new ArrayList<>();
        crow = new Crow();
        Crow p = null;
        for (int i = 1; i <= nAgents; i++) {
            do {
                p = new Crow();
            } while (!p.isFeasible());
            p.updatePBest();
            swarm.add(p);
        }

        // Copiando el primer cuervo de la bandada y se compara si es mejor con el que
        // ya hay
        crow.copy(swarm.get(0));
        for (int i = 1; i < nAgents; i++) {
            if (swarm.get(i).isBetterThan(crow)) {
                crow.copy(swarm.get(i));
            }
        }
    }

    private void run() {
        int t = 1;
        Crow followedCrow;
        while (t <= T) {
            for (int i = 0; i < nAgents; i++) {
                if (swarm.get(i).isBetterThanPBest()) {
                    swarm.get(i).updatePBest();
                }
                if (swarm.get(i).isBetterThan(crow)) {
                    crow.copy(swarm.get(i));
                }
            }
            for (int i = 0; i < nAgents; i++) {
                do {
                    followedCrow = swarm.get(rnd.nextInt(nAgents));
                    swarm.get(i).move(followedCrow, AP, flightLenght);
                } while (!swarm.get(i).isFeasible());
            }
            t++;
        }
    }

    private void endTime() {
        eTime = System.currentTimeMillis();
    }

    private void log() {
        StdOut.printf("%s, s: %s, t: %s\n", crow, StdRandom.getSeed(), (eTime - sTime));
    }
}