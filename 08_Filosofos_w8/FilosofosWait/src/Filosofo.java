public class Filosofo implements Runnable {
    private int id;
    private Tenedor tenedorIzquierda;
    private Tenedor tenedorDerecha;
    private int hambre;

    // Constructor con el número de comensal
    public Filosofo(int id, Tenedor tenedorIzquierda, Tenedor tenedorDerecha) {
        this.id = id;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.hambre = 0;
    }

    // Métodos getter para los tenedores
    public Tenedor getTenedorIzquierda() {
        return tenedorIzquierda;
    }

    public Tenedor getTenedorDerecha() {
        return tenedorDerecha;
    }

    // Método pensar (duerme un tiempo aleatorio)
    private void pensar() {
        System.out.println("Filósofo " + id + " pensando");
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método comer (duerme un tiempo aleatorio)
    private void comer() {
        System.out.println("Filósofo " + id + " comiendo");
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filósofo " + id + " terminó de comer");
        hambre = 0;
    }

    private void cogerTenedores() {
        cogerTenedorIzquierda();
        cogerTenedorDerecha();
    }

    private void cogerTenedorIzquierda() {
        tenedorIzquierda.cogerTenedor(id);
        System.out.println("Filósofo " + id + " coge tenedor izquierdo " + tenedorIzquierda.getNumero());
    }

    private void cogerTenedorDerecha() {
        tenedorDerecha.cogerTenedor(id);
        System.out.println("Filósofo " + id + " coge tenedor derecho " + tenedorDerecha.getNumero());
    }

    private void soltarTenedores() {
        System.out.println("Filósofo " + id + " suelta tenedor izquierdo " + tenedorIzquierda.getNumero());
        tenedorIzquierda.soltarTenedor();

        System.out.println("Filósofo " + id + " suelta tenedor derecho " + tenedorDerecha.getNumero());
        tenedorDerecha.soltarTenedor();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            if (intentarComer()) {
                comer();
                soltarTenedores();
            }
        }
    }

    private boolean intentarComer() {
        boolean exito = false;
    
        System.out.println("Filósofo " + id + " intenta coger los tenedores");
    
        // Intentar coger los dos tenedores
        cogerTenedores();
    
        // Comprobar si ambos tenedores están cogidos correctamente
        if (tenedorIzquierda.estaCogido(id) && tenedorDerecha.estaCogido(id)) {
            exito = true;
            hambre = 0;  // Reseteamos hambre al comer exitosamente
            System.out.println("Filósofo " + id + " ha conseguido ambos tenedores y puede comer");
        } else {
            System.out.println("Filósofo " + id + " no ha conseguido ambos tenedores");
    
            // Si no se logró coger los dos tenedores, soltar los que se habían cogido
            if (tenedorIzquierda.estaCogido(id)) {
                tenedorIzquierda.soltarTenedor();
                System.out.println("Filósofo " + id + " suelta tenedor izquierdo " + tenedorIzquierda.getNumero());
            }
            if (tenedorDerecha.estaCogido(id)) {
                tenedorDerecha.soltarTenedor();
                System.out.println("Filósofo " + id + " suelta tenedor derecho " + tenedorDerecha.getNumero());
            }
        }
    
        // Incrementar hambre solo si no pudo comer
        if (!exito) {
            hambre++;
            System.out.println("Filósofo " + id + " no pudo comer, hambre = " + hambre);
        }
    
        return exito;
    }
    
}
