import java.util.Scanner;

public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor();
            motors[i].setName("Motor " + i);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public void passaAPotencia(int potencia) {
        System.out.printf("Passant a potència %d%n", potencia);
        for (Motor motor : motors) {
            motor.setPotencia(potencia);
        }
        esperaFinalitzacio(); // Esperar que todos los motores terminen de ajustarse
    }

    private void esperaFinalitzacio() {
        boolean enProceso;
        do {
            enProceso = false;
            for (Motor motor : motors) {
                if (motor.isEnProceso()) {
                    enProceso = true;
                    break;
                }
            }
            try {
                Thread.sleep(500); // Pausa breve antes de volver a verificar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (enProceso);
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        try (Scanner scanner = new Scanner(System.in)) {
            Boolean apagar = false;
            while (apagar == false) {
                System.out.print("Introduce la potencia objetivo (0 para apagar): ");
                try {
                    int potencia = Integer.parseInt(scanner.nextLine());
                    coet.passaAPotencia(potencia);

                    if (potencia == 0) {
                        System.out.println("Apagando motores...");
                        apagar = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Entrada inválida");
                }
            }
        }
    }
}
