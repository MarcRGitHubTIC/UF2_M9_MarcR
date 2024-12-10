public class Fil extends Thread {

    public Fil(String nom) {
        setName(nom);
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
                System.out.println(getName()+ " " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        System.out.println("Termina el fil " + getName());
    }
}
