import java.util.Random;

public class Barbero extends Thread {
    private String name;
    private final Barberia barberia;

    public Barbero(String name, Barberia barberia) {
        this.name = name;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cliente client = barberia.seguentClient();
                if (client != null) {
                    tallarCabell(client);
                } else {
                    synchronized (barberia.condBarber) {
                        System.out.println(name + " durmiendo...");
                        barberia.condBarber.wait(); 
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " ha sido interrumpido.");
        }
    }

    private void tallarCabell(Cliente client) throws InterruptedException {
        Random rand = new Random();
        int tempsTall = 900 + rand.nextInt(100);
        System.out.println(name + " esta cortando el pelo de " + client.getName() + ", tiempo: " + tempsTall + "ms");
        Thread.sleep(tempsTall);
        System.out.println(name + " ha terminado de cortar el pelo a " + client.getName() + ".");
    }
}
