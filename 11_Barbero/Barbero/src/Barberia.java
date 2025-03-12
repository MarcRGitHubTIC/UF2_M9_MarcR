import java.util.LinkedList;
import java.util.Queue;

public class Barberia {

    private Queue<Cliente> salaEspera;
    private int numChairs;
    public final Object condBarber;
    private static Barberia instance;

    private Barberia(int numChairs) {
        this.numChairs = numChairs;
        this.salaEspera = new LinkedList<>();
        this.condBarber = new Object();
    }

    public static Barberia getInstance(int numChairs) {
        if (instance == null) {
            instance = new Barberia(numChairs);
        }
        return instance;
    }
    public Cliente seguentClient() {
        return salaEspera.poll();
    }

    public void entrarClient(Cliente client) {
        synchronized (condBarber) {
            if (salaEspera.size() < numChairs) {
                salaEspera.add(client);
                System.out.println(client.getName() + " entra a la sala espera.");
                condBarber.notify();
            } else {
                System.out.println(client.getName() + " no hay sitio, se va de la barberia.");
            }
        }
    }

    public void exec() {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(500);
                Cliente client = new Cliente(i);
                entrarClient(client);
            }

            Thread.sleep(10000);

            for (int i = 11; i <= 20; i++) {
                Thread.sleep(500);
                Cliente client = new Cliente(i);
                entrarClient(client);
            }
        } catch (InterruptedException e) {
            System.out.println("Barberia interrumpida.");
        }
    }

    public static void main(String[] args) {

        Barberia barberia = Barberia.getInstance(3);
        
        Barbero barbero = new Barbero("Barbero", barberia);
        
        barbero.start();
        
        barberia.exec();
    }
}
