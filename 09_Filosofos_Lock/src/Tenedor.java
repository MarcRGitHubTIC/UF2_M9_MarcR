import java.util.concurrent.locks.ReentrantLock;

public class Tenedor {
    private final int numero;
    public final ReentrantLock bloqueo;

    public Tenedor(int numero) {
        this.numero = numero;
        this.bloqueo = new ReentrantLock();
    }

    public void coger() {
        bloqueo.lock();
    }

    public void dejar() {
        if (bloqueo.isHeldByCurrentThread()) {
            bloqueo.unlock();
        } 
    }

    public int getNumero() {
        return numero;
    }
}
