public class Principal {
    public static void main(String[] args) {
        Fil hiloPepe = new Fil("Pepe");
        Fil hiloJuan = new Fil("Juan");
        // Start de hilos
        hiloPepe.start();
        hiloJuan.start();


        System.out.println("Termina thread main");
    }
}
