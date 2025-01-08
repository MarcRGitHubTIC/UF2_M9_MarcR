public class Motor extends Thread {
    private int potenciaActual = 0;
    private int powerTarget = 0;
    private boolean enFuncionamiento = true;
    private boolean enProceso = false;

    public synchronized void setPotencia(int potencia) {
        this.powerTarget = potencia;
        enProceso = true;
        notify();
    }

    public synchronized boolean isEnProceso() {
        return enProceso;
    }

    @Override
    public void run() {
        try {
            while (enFuncionamiento) {
                synchronized (this) {
                    while (potenciaActual == powerTarget) {
                        enProceso = false; // Motor ajustado
                        wait(); // Espera hasta potencia objetivo
                    }
                }

                // Ajustar potencia actual a potencia objetivo
                if (potenciaActual < powerTarget) {
                    potenciaActual++;
                    System.out.printf("%s: Incre. Objectiu: %d Actual: %d%n", getName(), powerTarget, potenciaActual);
                } else if (potenciaActual > powerTarget) {
                    potenciaActual--;
                    System.out.printf("%s: Decre. Objectiu: %d Actual: %d%n", getName(), powerTarget, potenciaActual);
                }

                if (potenciaActual == powerTarget) {
                    System.out.printf("%s: FerRes Objectiu: %d Actual: %d%n", getName(), powerTarget, potenciaActual);
                }

                Thread.sleep(200); // Tiempo simulado de delay en real life
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
