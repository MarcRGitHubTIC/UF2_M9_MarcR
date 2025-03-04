import java.util.concurrent.locks.ReentrantLock;

public class Tenedor {
    private final int numero;
    private final ReentrantLock bloqueo;

    public Tenedor(int numero) {
        this.numero = numero;
        this.bloqueo = new ReentrantLock();
    }

    public int getNumero() {
        return numero;
    }

    public boolean coger(int filosofoId, String posicion) {
        boolean obtenido = bloqueo.tryLock();
        if (obtenido) {
            System.out.println("Filósofo " + filosofoId + " ha cogido el tenedor " + numero + " (" + posicion + ")");
        }
        return obtenido;
    }

    public void dejar(int filosofoId, String posicion) {
        if (bloqueo.isHeldByCurrentThread()) {
            bloqueo.unlock();
            System.out.println("Filósofo " + filosofoId + " ha dejado el tenedor " + numero + " (" + posicion + ")");
        }
    }
}
