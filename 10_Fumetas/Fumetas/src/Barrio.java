class Barrio {
    public static void main(String[] args) {
        Estanco estanc = new Estanco();
        Fumador[] fumadors = new Fumador[3];

        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
            fumadors[i].start();
        }

        Thread estancThread = new Thread(estanc::executar);
        estancThread.start();

        for (Fumador fumador : fumadors) {
            try {
                fumador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        estanc.cerrarEstanco();
    }
}