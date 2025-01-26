public class Compte {
    private float saldo;
    private static Compte compte;
    private Compte(float saldo) {
        this.saldo = saldo;
    }
    public static Compte getCompte(float saldo) {
        if(compte == null){
            compte = new Compte(0.0f);
        }
        return compte;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}