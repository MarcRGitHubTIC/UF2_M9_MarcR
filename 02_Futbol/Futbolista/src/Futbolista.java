import java.util.Random;

public class Futbolista extends Thread {

    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols = 0;
    private int ntirades = 0;

    public Futbolista(String nom) {
        super(nom); // Default name
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (rand.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }
    public int getNtirades(){
        return ntirades;
    }

    public static void main(String[] args) throws InterruptedException {
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];

        // Crear e iniciar hilos
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista("Player " + (i + 1));
            jugadors[i].start();
        }

        // Esperar a todos
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i].join();
        }

        // Mostrar stats
        int totalGols = 0;
        int totalTirades = 0;
        for (int i = 0; i < NUM_JUGADORS; i++) {
            totalGols += jugadors[i].getNgols();
            totalTirades += jugadors[i].getNtirades();
            System.out.println(jugadors[i].getName() + " -> Gols: " + jugadors[i].getNgols() +" en " + jugadors[i].getNtirades()+" tiradas");
        }

        // Mostrar stats globales
        System.out.println("\nEstadístiques globals:");
        System.out.println("Total de gols marcats: " + totalGols);
        System.out.println("Total de tirades realitzades: " + totalTirades);
        System.out.println("Promig de gols per tirada: " + (float) totalGols / totalTirades);
    }
    
}
