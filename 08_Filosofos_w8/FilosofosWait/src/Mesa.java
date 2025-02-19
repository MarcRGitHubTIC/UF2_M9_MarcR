public class Mesa {
    private Tenedor[] tenedores;
    private Filosofo[] filosofos;

    public Mesa() {
        tenedores = new Tenedor[4];
        filosofos = new Filosofo[4];

        // Crear tenedores
        for (int i = 0; i < 4; i++) {
            tenedores[i] = new Tenedor(i);
        }

        // Crear filosofos
        for (int i = 0; i < 4; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % 4]);
        }
    }

    public void mostrarMesa() {
        System.out.println("------------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println("Comensal: Filosofo " + i + " left: " + filosofos[i].getTenedorIzquierda().getNumero() + " right: " + filosofos[i].getTenedorDerecha().getNumero());
        }
        System.out.println("------------------------------------");
    }

    public void llamarMesa() {
        for (int i = 0; i < 4; i++) {
            new Thread(filosofos[i]).start();
        }
    }
}
