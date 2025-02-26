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

    public synchronized boolean cogerTenedor(int id) {
        long inicio = System.currentTimeMillis();
        while (idPropietari != LLIURE) {
            try {
                wait(200);
                if (System.currentTimeMillis() - inicio >= 200) {
                    return false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        idPropietari = id;
        return true;
    }
    

    public synchronized void soltarTenedor() {
        idPropietari = LLIURE;
        notifyAll();
    }

    public synchronized boolean estaCogido(int id) {
        return idPropietari == id;
    }
}
