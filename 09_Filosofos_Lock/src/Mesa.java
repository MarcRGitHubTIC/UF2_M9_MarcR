public class Mesa {
    private Filosofo[] comensales;
    private Tenedor[] tenedores;

    public Mesa(int numFilosofos) {
        tenedores = new Tenedor[numFilosofos];
        comensales = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = new Tenedor(i);
        }

        for (int i = 0; i < numFilosofos; i++) {
            comensales[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % numFilosofos]);
        }
    }

    public void showMesa() {
        System.out.println("------ Mesa ------");
        for (Filosofo f : comensales) {
            System.out.println("Filosofo" + f.id + " | Izq: " + f.tenedorIzquierda.getNumero() + " | Der: " + f.tenedorDerecha.getNumero());
        }
        System.out.println("------------------");
    }

    public void callMesa() {
        for (Filosofo f : comensales) {
            new Thread(f).start();
        }
    }
}
