public class Associacio {
    private int numSocis = 1000;
    private Soci[] socis = new Soci[numSocis];
    public Associacio(Compte account) {
        for(int i = 0; i < numSocis; i++){
            socis[i] = new Soci(account,"Socio " + i);
        }
    }
    public void iniciaCompteTempsSocis(){
        for(Soci socio : socis){
            socio.start();
        }
    }
    public void esperaPeriodeSocis(){
        for(Soci socio : socis){
            try{
                socio.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void mostraBalancComptes(){
        System.out.println("Balance:" + socis[0].getCompte().getSaldo());
    }
}