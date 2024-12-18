public class MainDemoFil {
    public static void main(String[] args) {
        Thread filActual = Thread.currentThread();

        System.out.println("Nom del fil: " + filActual.getName());
        System.out.println("Prioritat del fil: " + filActual.getPriority());
        System.out.println("Descripció del fil (toString()): " + filActual.toString());
    }
}
