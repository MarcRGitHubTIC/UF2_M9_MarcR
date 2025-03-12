

import java.util.Random;

class Fumador extends Thread {
    private final Estanco Estanco;
    private final int id;
    private int fumades = 0;

    public Fumador(Estanco Estanco, int id) {
        this.Estanco = Estanco;
        this.id = id;
    }

    private void fuma() throws InterruptedException {
        System.out.println("Fumador " + id + " fumando");
        Thread.sleep(500 + new Random().nextInt(500));
        fumades++;
        System.out.println("Fumador " + id + " ha fumado " + fumades + " times");
    }
    

    public void run() {
        try {
            while (fumades < 3) {
                System.out.println("Fumador " + id + " comprando hojas");
                if (Estanco.venTabac(id) == null) break;

                System.out.println("Fumador " + id + " comprando papel");
                if (Estanco.venPaper(id) == null) break;

                System.out.println("Fumador " + id + " comprando cerillas");
                if (Estanco.venLlumi(id) == null) break;

                fuma();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}