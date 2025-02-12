public class Tenedor {
    private int numero;
    private boolean enUso;

    public Tenedor(int numero) {
        this.numero = numero;
        this.enUso = false;
    }

    public synchronized boolean cogerTenedor() {
        if (!enUso) {
            enUso = true;
            return true;
        }
        return false;
    }

    public synchronized void soltarTenedor() {
        enUso = false;
    }

    public int getNumero() {
        return numero;
    }
}
