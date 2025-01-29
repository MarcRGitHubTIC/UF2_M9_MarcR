import java.util.Random;
public class Soci extends Thread{
    private Compte account;
    private Random random = new Random();
    private int max_year;
    public Soci(Compte compte, String nombre) {
        this.account = compte;
        this.max_year = 10;
    }
    public Compte getCompte() {
        return account;
    }
    @Override
    public void run(){
        for(int i = 0; i < max_year; i++){
            for(int j = 0; j < 12; j++){
                synchronized (account){
                    float saldo = 0f;
                    if (j % 2 == 0){
                        saldo = account.getSaldo() + 10f;
                        account.setSaldo(saldo);
                    }else{
                        saldo = account.getSaldo() - 10f;
                        account.setSaldo(saldo);
                    }
                }
                try{
                    sleep(random.nextInt(101));
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }
    }
}