package mitsk.simulation.descrete.shop;

import mitsk.simulation.discrete.step.Simulation;
import mitsk.simulation.discrete.step.SimulationObject;

import java.util.ArrayList;
import java.util.List;

public class Kasa extends SimulationObject {
    public List<Client> kolejkaDoKasy = new ArrayList<Client>();
    boolean klienciPrzyKasie;
    int czasBezBlienta = 0;
    boolean kasaDziala = true;

    public Kasa(Simulation simulation) {
        super(simulation);
        klienciPrzyKasie = false;
    }

    @Override
    public void timeAdvanced() throws InterruptedException {

    }
//  OBSLUGA FLAGI OKRESLAJACEJ ZAMKNIECIE LUB OTWARCIE AKSY
    public int wKolejce() {
        if (kolejkaDoKasy.size() == 0) {
            czasBezBlienta = czasBezBlienta + 1;
            return czasBezBlienta;
        } else if (kolejkaDoKasy.size() >= 3) {
            kasaDziala = true;
            czasBezBlienta = 0;
        }
        return czasBezBlienta;
    }
//  OBSLUGA DODAWANIA OBIEKTOW DO KOLEJKI DO KASY
    public void addToKolejkaDoKasy(Client client) {
        kolejkaDoKasy.add(client);
    }
}
