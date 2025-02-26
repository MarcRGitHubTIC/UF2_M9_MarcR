import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {
    public int id;
    public Tenedor tenedorIzquierda;
    public Tenedor tenedorDerecha;
    private long startHambre;
    private long endHambre;
    private long hambre;

    public Filosofo(int id, Tenedor tenedorIzquierda, Tenedor tenedorDerecha) {
        this.id = id;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.hambre = 0;
    }

    private void pensar() {
        System.out.println("Filosofo" + id + " pensando");
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean cogerTenedores() {
        System.out.println("Filosofo" + id + " tiene tenedor izq(" + tenedorIzquierda.getNumero() + ") der(" + tenedorDerecha.getNumero() + ")");
        
        tenedorIzquierda.coger();
        
        if (tenedorDerecha.bloqueo.tryLock()) {
            return true;
        } else {
            if (tenedorIzquierda.bloqueo.isHeldByCurrentThread()) {
                tenedorIzquierda.dejar();
            }
            return false;
        }
    }

    private void dejarTenedores() {
        if (tenedorIzquierda.bloqueo.isHeldByCurrentThread()) {
            tenedorIzquierda.dejar();
        }
        if (tenedorDerecha.bloqueo.isHeldByCurrentThread()) {
            tenedorDerecha.dejar();
        }
    }

    private void calcularHambre() {
        hambre = TimeUnit.SECONDS.convert(endHambre - startHambre, TimeUnit.MILLISECONDS);
    }

    private void resetHambre() {
        startHambre = System.currentTimeMillis();
        hambre = 0;
    }

    private void comer() {
        endHambre = System.currentTimeMillis();
        calcularHambre();
        System.out.println("Filosofo" + id + " come con hambre " + hambre);
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filosofo" + id + " ha terminado de comer");
        resetHambre();
        dejarTenedores();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            startHambre = System.currentTimeMillis();
            while (!cogerTenedores()) {
                try {
                    Thread.sleep((long) (Math.random() * 500 + 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            comer();
        }
    }
}
