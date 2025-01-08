public class DormAleatori extends Thread {
    private final long instanceCreation; // Create instancia

    
    public DormAleatori(String nombre) {
        super(nombre);
        this.instanceCreation = System.currentTimeMillis(); // Constructor guardar momento creacion
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int intervaloAleatorio = (int) (Math.random() * 1000);
            long tiempoTotal = System.currentTimeMillis() - instanceCreation; // Tiempo total desde instanceCreation

            // Mostrar datos
            System.out.printf("%s(%d) a dormir %dms total %dms%n", getName(), i, intervaloAleatorio, tiempoTotal);

            try {
                Thread.sleep(intervaloAleatorio);
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido");
                Thread.currentThread().interrupt(); // Reinterrupt thread
            }
        }
    }

    public static void main(String[] args) {
        // Crear e iniciar instancias
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();
        System.out.println("-- Fin del main -----------");
    }
}
