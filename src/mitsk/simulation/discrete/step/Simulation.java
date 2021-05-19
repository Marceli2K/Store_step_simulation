package mitsk.simulation.discrete.step;

import mitsk.simulation.descrete.shop.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private Integer step;
    private Integer currentTime = 0;
    List<SimulationObject> listaObiektowSymulacji = new ArrayList<SimulationObject>();
    int maxSimulationTime;
    ClientStream clientStreamObject;

    public Simulation(Integer maxSimulationTime, Integer step) {
        this.maxSimulationTime = maxSimulationTime;
        this.step = step;
        clientStreamObject = new ClientStream(this);
    }
//  POBIERANIE CZASSU SYMULACJI
    public int getTime() {
        return currentTime;
    }

// REJESTRACJA OBIEKTU W SYMULACJI
    public void register(SimulationObject simulationObject) {
        listaObiektowSymulacji.add(simulationObject);
    }
//  USUNIECIE OBIEKTU Z LISTY OBIEKTOW SYMULACJI
    public void unregister(SimulationObject simulationObject) {
        listaObiektowSymulacji.remove(simulationObject);
    }

    public void run() throws InterruptedException {
        while (currentTime <= maxSimulationTime) {
            System.out.println("\n############\nTIME: " + currentTime + "\n############");
            clientStreamObject.timeAdvanced();
            currentTime = currentTime + step;

        }
//        System.out.println("Liczba pozostałych klientów " + listaObiektowSymulacji.size());
    }
}
