import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    public void iniciarEsdeveniment() {
        Esdeveniments esdeveniment = new Esdeveniments(5);
        List<Assistent> assistents = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Assistent assistent = new Assistent("Asistente " + i, esdeveniment);
            assistents.add(assistent);
            assistent.start();
        }
    }
}
