import java.util.Random;

public class Assistent extends Thread {
    private final String nom;
    private final Esdeveniments esdeveniment;
    private final Random random = new Random();

    public Assistent(String nom, Esdeveniments esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int probabilitat = random.nextInt(100);

                if (probabilitat < 30) {  // Probabilidad del 70% para ferReserva
                    esdeveniment.ferReserva(this);
                } else {  // Probabilidad del 30% para cancelarReserva
                    esdeveniment.cancelaReserva(this);
                }

                // Espera aleatoria entre 0 y 1 segundo
                Thread.sleep(random.nextInt(1000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
