import java.util.Random;

public class Treballador extends Thread {
    private final float souAnualBrut;
    private final int edatIniciTreball;
    private final int edatFiTreball;
    private int edatActual;
    private float cobrat;
    private final Random rnd;

    public Treballador(String nom, float souAnualBrut, int edatIniciTreball, int edatFiTreball) {
        super(nom);
        this.souAnualBrut = souAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;
        this.edatActual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        cobrat += souAnualBrut / 12;
    }

    public void pagaImpostos() {
        cobrat -= (cobrat / 12) * 0.24;
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edatActual;
    }

    @Override
    public void run() {
        for (edatActual = 0; edatActual < edatFiTreball; edatActual++) {
            if (edatActual >= edatIniciTreball) {
                cobra();
                pagaImpostos();
            }
            try {
                Thread.sleep(rnd.nextInt(10)); // Simulacion de tiempo
            } catch (InterruptedException e) {
                System.err.println("Error al simular el temps: " + e.getMessage());
            }
        }
    }
}
