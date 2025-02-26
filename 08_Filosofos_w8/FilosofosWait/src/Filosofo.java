public class Filosofo implements Runnable {
    private int id;
    private Tenedor tenedorIzquierda;
    private Tenedor tenedorDerecha;
    private int hambre;

    public Filosofo(int id, Tenedor tenedorIzquierda, Tenedor tenedorDerecha) {
        this.id = id;
        this.tenedorIzquierda = tenedorIzquierda;
        this.tenedorDerecha = tenedorDerecha;
        this.hambre = 0;
    }

    public Tenedor getTenedorIzquierda() {
        return tenedorIzquierda;
    }

    public Tenedor getTenedorDerecha() {
        return tenedorDerecha;
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " pensando");
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
        boolean izqCogido = tenedorIzquierda.cogerTenedor(id);
        boolean derCogido = tenedorDerecha.cogerTenedor(id);
    
        if (!izqCogido || !derCogido) {
            if (izqCogido) tenedorIzquierda.soltarTenedor();
            if (derCogido) tenedorDerecha.soltarTenedor();
            hambre++;
        }
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
    
        cogerTenedores();
    
        if (tenedorIzquierda.estaCogido(id) && tenedorDerecha.estaCogido(id)) {
            exito = true;
            hambre = 0;
            System.out.println("Filósofo " + id + " ha conseguido ambos tenedores y puede comer");
        } else {
            System.out.println("Filósofo " + id + " no ha conseguido ambos tenedores");
    
            if (tenedorIzquierda.estaCogido(id)) {
                tenedorIzquierda.soltarTenedor();
                System.out.println("Filósofo " + id + " suelta tenedor izquierdo " + tenedorIzquierda.getNumero());
            }
            if (tenedorDerecha.estaCogido(id)) {
                tenedorDerecha.soltarTenedor();
                System.out.println("Filósofo " + id + " suelta tenedor derecho " + tenedorDerecha.getNumero());
            }
        }
    
        if (!exito) {
            hambre++;
            System.out.println("Filósofo " + id + " no pudo comer, hambre = " + hambre);
        }
    
        return exito;
    }
    
}
