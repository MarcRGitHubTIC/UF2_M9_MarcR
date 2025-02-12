public class Mesa {
    private Tenedor[] tenedores;
    private Filosofo[] filosofos;

    public Mesa() {
        tenedores = new Tenedor[5];
        filosofos = new Filosofo[5];

        // Crear tenedores
        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Tenedor(i);
        }

        // Crear filosofos
        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo("hilo" + i, tenedores[i], tenedores[(i + 1) % 5]);
        }
    }

    public void mostrarMesa() {
        System.out.println("------------------------------------");
        for (int i = 0; i < 5; i++) {
            System.out.println("Comensal: " + filosofos[i].getNombre() + " left:" + filosofos[i].getTenedorIzquierda().getNumero() + " right:" + filosofos[i].getTenedorDerecha().getNumero());
        }
        System.out.println("------------------------------------");
    }

    public void llamarMesa() {
        for (int i = 0; i < 5; i++) {
            new Thread(filosofos[i]).start();
        }
    }
}
