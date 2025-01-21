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

    private float calculaMensualBrut() {
        return souAnualBrut / 12;
    }

    public void cobra() {
        float ingresoMensual = calculaMensualBrut();
        cobrat += ingresoMensual;
    }  

    public void pagaImpostos() {
        float ingresoMensual = calculaMensualBrut();
        float impuestos = ingresoMensual * 0.24f;
        cobrat -= impuestos;
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
                Thread.sleep(rnd.nextInt(10));
            } catch (InterruptedException e) {
                System.err.println("Error al simular el temps: " + e.getMessage());
            }
        }
    }
}
