public class App {
    public static void main(String[] args) {
        Compte compte1 = Compte.getCompte(0.0f);
        Associacio assosiacio = new Associacio(compte1);
        assosiacio.iniciaCompteTempsSocis();
        assosiacio.esperaPeriodeSocis();
        assosiacio.mostraBalancComptes();
    }
}