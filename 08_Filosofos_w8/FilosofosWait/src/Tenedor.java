public class Tenedor {
    private int numero;
    private int idPropietari;
    public static final int LLIURE = -1;

    public Tenedor(int numero) {
        this.numero = numero;
        this.idPropietari = LLIURE;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPropietari() {
        return idPropietari;
    }

    public void setIdPropietari(int id) {
        this.idPropietari = id;
    }

    public synchronized void cogerTenedor(int id) {
        while (idPropietari != LLIURE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        idPropietari = id;
    }

    public synchronized void soltarTenedor() {
        idPropietari = LLIURE;
        notifyAll();
    }

    public synchronized boolean estaCogido(int id) {
        return idPropietari == id;
    }
}
