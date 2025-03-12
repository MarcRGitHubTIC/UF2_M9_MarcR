import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

// Classe Tabac
class Tabac {}

// Classe Paper
class Paper {}

// Classe Llumi
class Llumi {}

// Classe Estanco
class Estanco {
    private Queue<Tabac> tabac = new LinkedList<>();
    private Queue<Paper> paper = new LinkedList<>();
    private Queue<Llumi> llumins = new LinkedList<>();
    private boolean obert = true;
    private final Random random = new Random();

    public synchronized void nouSubministrament() {
        if (!obert) return;
        try {
            Thread.sleep(500 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int producte = random.nextInt(3);
        switch (producte) {
            case 0:
                addTabac();
                break;
            case 1:
                addPaper();
                break;
            case 2:
                addLlumi();
                break;
        }
        notifyAll();
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Estanco-> Añadiendo tabaquillo");
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Estanco -> Añadiendo papel");
    }

    public synchronized void addLlumi() {
        llumins.add(new Llumi());
        System.out.println("Estanco -> Añadiendo cerillas");
    }

    public synchronized Tabac venTabac(int id) throws InterruptedException {
        while (tabac.isEmpty() && obert) wait();
        if (!tabac.isEmpty()) {
            return tabac.poll();
        }
        return null;
    }

    public synchronized Paper venPaper(int id) throws InterruptedException {
        while (paper.isEmpty() && obert) wait();
        if (!paper.isEmpty()) {
            return paper.poll();
        }
        return null;
    }

    public synchronized Llumi venLlumi(int id) throws InterruptedException {
        while (llumins.isEmpty() && obert) wait();
        if (!llumins.isEmpty()) {
            return llumins.poll();
        }
        return null;
    }

    public synchronized void cerrarEstanco() {
        obert = false;
        notifyAll();
        System.out.println("Estanco chapado");
    }

    public void executar() {
        System.out.println("Estanco abierto");
        while (obert) {
            nouSubministrament();
        }
    }
}
