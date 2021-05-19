package mitsk.simulation.descrete.shop;

import mitsk.generators.Uniform;
import mitsk.generators.Weighted;
import mitsk.simulation.discrete.step.Simulation;
import mitsk.simulation.discrete.step.SimulationObject;

import java.util.ArrayList;
import java.util.List;

public class Client extends SimulationObject {
    protected double czasObslugiPrzezKasjera;
    private double czasPoJakimObsluzyNasKasjer;
    public Simulation simulation;
    int timeWhenClientStart = 0;
    Uniform uniformObject;
    int randomTimeInShop = 0;
    List<Object> klienciWSklepie = new ArrayList<Object>();
    int id;
    private boolean inkasa;
    public Weighted obslugaPrzezKasjera;
    int gotocheckout;
    int czasCierpliwosci;
    boolean wObsludze;
    boolean toDelete;

    public Client(Simulation simulation, int i) {
        super(simulation);
        this.id = i + 1;
        klienciWSklepie.add(this);
        this.simulation = simulation;
        uniformObject = new Uniform(7, 13);
        randomTimeInShop = (int) uniformObject.getNext();
        timeWhenClientStart = simulation.getTime();
        gotocheckout = simulation.getTime() + randomTimeInShop;
        wObsludze = false;
        obslugaPrzezKasjera = new Weighted(new Weighted.Obiekty(1, 1, 0.2), new Weighted.Obiekty(3, 3, 0.5), new Weighted.Obiekty(4, 4, 0.1));
        czasObslugiPrzezKasjera = obslugaPrzezKasjera.getNext();
        toDelete = false;
    }

    @Override
    public void timeAdvanced() {

    }

    // OBSLUGA ZNIECIERPLIWIENIA
    public void Checkout() {
        if (gotocheckout >= simulation.getTime() && !inkasa) {
            czasCierpliwosci = simulation.getTime() + 5;
            klienciWSklepie.remove(this);
            inkasa = true;
        }
    }

    public boolean getInKasa() {
        return inkasa;
    }

    public void inKasa(boolean inkasa) {
        this.inkasa = inkasa;
    }

    // FUNKCJA ODPOWIADAJĄCA ZA GENEROWANIE CZASU OBSLUGI KLIENTA PRZEZ KASJERA
    public void czasNaObsluge() {
        czasPoJakimObsluzyNasKasjer = czasObslugiPrzezKasjera + simulation.getTime();
    }

    public int getCzasNaObsluge() {
        return (int) czasPoJakimObsluzyNasKasjer;
    }
}
