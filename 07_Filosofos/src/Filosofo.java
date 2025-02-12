public class Filosofo implements Runnable {
    private String nombre;
    private Tenedor tenedorIzquierda;
    private Tenedor tenedorDerecha;
    private int hambre;

    public Filosofo(String nombre, Tenedor tenedorIzquierda, Tenedor tenedorDerecha) {
        this.nombre = nombre;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.hambre = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Tenedor getTenedorIzquierda() {
        return tenedorIzquierda;
    }

    public Tenedor getTenedorDerecha() {
        return tenedorDerecha;
    }

    private void comer() {
        System.out.println("Filosofo: " + nombre + " coge izquierda " + tenedorIzquierda.getNumero());
        System.out.println("Filosofo: " + nombre + " coge derecha " + tenedorDerecha.getNumero());
        System.out.println("Filosofo: " + nombre + " come");

        // Comer entre 1 y 2 segundos
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Filosofo: " + nombre + " termina de comer");
    }

    private void pensar() {
        System.out.println("Filosofo: " + nombre + " pensando");
        // Pensar entre 1 y 2 segundos
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000)); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            if (cogerTenedores()) {
                comer();
                // Resetear hambre despues de comer
                hambre = 0;
                // Soltar los tenedores despues de comer
                soltarTenedores();
            } else {
                hambre++;
                System.out.println("Filosofo: " + nombre + " hambre=" + hambre);
            }
        }
    }

    private boolean cogerTenedores() {
        // Intentar coger tenedor izquierdo
        if (tenedorIzquierda.cogerTenedor()) {
            // Intentar coger tenedor derecho
            if (tenedorDerecha.cogerTenedor()) {
                return true;
            } else {
                // Soltar tenedor izquierdo si no puede coger derecho
                tenedorIzquierda.soltarTenedor();
            }
        }
        // Esperar entre 0.5 y 1 segundo
        try {
            Thread.sleep((long) (Math.random() * 500 + 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void soltarTenedores() {
        System.out.println("Filosofo: " + nombre + " suelta izquierda " + tenedorIzquierda.getNumero());
        // False para soltar tenedor
        tenedorIzquierda.soltarTenedor();
        
        System.out.println("Filosofo: " + nombre + " suelta derecha " + tenedorDerecha.getNumero());
        // False para soltar tenedor
        tenedorDerecha.soltarTenedor();
    }
}
