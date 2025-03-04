import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {
    private final int id;
    private final Tenedor tenedorIzquierda;
    private final Tenedor tenedorDerecha;
    private long inicioGana;
    private long fiGana;
    private long gana;

    public Filosofo(int id, Tenedor tenedorIzquierda, Tenedor tenedorDerecha) {
        this.id = id;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.gana = 0;
    }

    public int getId() {
        return id;
    }

    public Tenedor getTenedorIzquierda() {
        return tenedorIzquierda;
    }

    public Tenedor getTenedorDerecha() {
        return tenedorDerecha;
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando.");
        inicioGana = System.currentTimeMillis();
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000)); // 1s - 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean agafarForquilles() {
        if (!tenedorIzquierda.coger(id, "izquierda")) {
            return false;
        }
        try {
            if (!tenedorDerecha.coger(id, "derecha")) {
                tenedorIzquierda.dejar(id, "izquierda");
                return false;
            }
        } catch (Exception e) {
            tenedorIzquierda.dejar(id, "izquierda");
            return false;
        }
        return true;
    }

    private void deixarForquilles() {
        tenedorDerecha.dejar(id, "derecha");
        tenedorIzquierda.dejar(id, "izquierda");
    }

    private void calcularGana() {
        fiGana = System.currentTimeMillis();
        gana = TimeUnit.SECONDS.convert(fiGana - inicioGana, TimeUnit.MILLISECONDS);
    }

    private void resetGana() {
        inicioGana = System.currentTimeMillis();
        gana = 0;
    }

    private void menjar() {
        calcularGana();
        System.out.println("Filósofo " + id + " está comiendo. Hambre: " + gana);
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000)); // 1s - 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filósofo " + id + " ha terminado de comer.");
        resetGana();
        deixarForquilles();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            while (!agafarForquilles()) {
                try {
                    Thread.sleep((long) (Math.random() * 500 + 500)); // Espera entre 0.5s - 1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            menjar();
        }
    }
}
