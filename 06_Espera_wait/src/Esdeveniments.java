import java.util.ArrayList;
import java.util.List;

public class Esdeveniments {
    private final int maxPlaces;
    private int availablePlace;
    private final List<Assistent> assistents = new ArrayList<>();

    public Esdeveniments(int maxPlaces) {
        this.maxPlaces = maxPlaces;
        this.availablePlace = maxPlaces;
    }

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (availablePlace == 0) {
            System.out.println(assistent.getNom() + " esperando plaza. Plazas disponibles: " + availablePlace);
            wait();
        }
        assistents.add(assistent);
        availablePlace--;
        System.out.println(assistent.getNom() + " ha hecho una reserva. Plazas disponibles: " + availablePlace);
        notifyAll();
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.remove(assistent)) {
            availablePlace++;
            System.out.println(assistent.getNom() + " ha cancelado la reserva. Plazas disponibles: " + availablePlace);
            notifyAll();
        }
    }
}
        