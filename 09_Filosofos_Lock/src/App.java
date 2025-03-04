public class App {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Mesa mesa = new Mesa(numFilosofos);
        mesa.showMesa();
        mesa.callMesa();
    }
}
