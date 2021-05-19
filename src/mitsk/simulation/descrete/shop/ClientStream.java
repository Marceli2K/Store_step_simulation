package mitsk.simulation.descrete.shop;

import mitsk.generators.RoundRobin;
import mitsk.generators.Uniform;
import mitsk.generators.Weighted;
import mitsk.simulation.discrete.step.Simulation;
import mitsk.simulation.discrete.step.SimulationObject;

import java.util.ArrayList;
import java.util.List;

public class ClientStream extends SimulationObject {
    int randomTime = 0;
    List<Client> klienci = new ArrayList<Client>();
    List<Client> klienciDoUsuniecia = new ArrayList<Client>();
    List<Client> klientID = new ArrayList<Client>();
    Simulation simulation;
    int timeToEqual;

    Uniform uniformObject = new Uniform(4, 7);
    Uniform prawdZdenerwowania;
    RoundRobin roundRobinObject = new RoundRobin(new int[]{10, 10, 15});

    Kasa kasa1 = new Kasa(simulation);
    Kasa kasa2 = new Kasa(simulation);


    public ClientStream(Simulation simulation) {
        super(simulation);
        this.simulation = simulation;
        randomTime = (int) uniformObject.getNext();
        timeToEqual = randomTime + simulation.getTime();
        prawdZdenerwowania = new Uniform(0, 1);
        timeToEqual = randomTime + simulation.getTime();
        randomTime = (int) uniformObject.getNext();

    }

    //    OBSLUGA DODAWANIA NOWEGO KLIENTA
    public void newClient(int next) throws InterruptedException {
        int i = 0;
        while (i < next) {
            Client Klient = new Client(this.simulation, klientID.size());
            simulation.register(Klient);
            klienci.add(Klient);
            klientID.add(Klient);
            int id = klientID.size();
            System.out.println("Klient " + id + " przybył  do sklepu");
            i++;
        }
    }

    @Override
    public void timeAdvanced() throws InterruptedException {
//      GENEROWANIE NOWYCH KLIENTOW CO OKRESLONY CZAS
        if (timeToEqual == simulation.getTime()) {
            int xx = (int) roundRobinObject.getNext();
            this.newClient(xx);
            randomTime = (int) uniformObject.getNext();
            timeToEqual = randomTime + simulation.getTime();

        }
        try {
//          OBSLUGA WSZYSTKICH KLIENTOW W PETLI
            for (Client client : klienci) {
                client.Checkout();

                // OBSLUGA POJSCIA DO KASY PRZEZ KLIENTA
                if (client.getInKasa() && !(kasa1.kolejkaDoKasy.contains(client) || kasa2.kolejkaDoKasy.contains(client))) {
                    if ((kasa1.kolejkaDoKasy.size() >= kasa2.kolejkaDoKasy.size() && kasa2.kasaDziala) || !kasa1.kasaDziala) { // KLIENT IDZIE DO KASY 2
                        kasa2.addToKolejkaDoKasy(client);
                        System.out.println("Klient " + client.id + " udał się do kasy2");
                    } else if ((kasa1.kolejkaDoKasy.size() < kasa2.kolejkaDoKasy.size() && kasa1.kasaDziala) || !kasa2.kasaDziala) { // KLIENT IDZIE DO KASY 1
                        kasa1.addToKolejkaDoKasy(client);
                        System.out.println("Klient " + client.id + " udał się do kasy1");
                    }
                    client.inKasa(true);
                }
                // OBSLUGA ZNIECIERPLWIENIA U KLIENTA
                if (client.getInKasa() && client.czasCierpliwosci <= simulation.getTime() && !client.wObsludze) {
                    Uniform y = new Uniform(0, 1);
                    double prawdZdenerwowania = y.getNext();
                    if (prawdZdenerwowania < 0.25) {
                        if (kasa1.kolejkaDoKasy.contains(client)) {
                            kasa1.kolejkaDoKasy.remove(client);
                        } else {
                            kasa2.kolejkaDoKasy.remove(client);
                        }
                        simulation.unregister(client);
                        klienciDoUsuniecia.add(client);
                        System.out.println("Klient " + client.id + " stracił cierpliwość i wyszedł");
//                    System.out.println(klienci.size());
                    } else {
                        client.czasCierpliwosci += 2;
                    }

                }
                //Obsługa kasy 1
                if (!kasa1.kolejkaDoKasy.isEmpty() && kasa1.kasaDziala) {
                    if (kasa1.kolejkaDoKasy.get(0) == client) {
                        if (!client.wObsludze) {
                            client.czasNaObsluge();
                            client.wObsludze = true;
                            System.out.println("kasjer - 1 podjął klienta.");
                        } else {
                            if (client.getCzasNaObsluge() == simulation.getTime()) { // ZAKONCZENIE OBSLUGI JESTLI CZAS PRZEZNACZONY NA NIA SIE SKONCZYL
                                client.wObsludze = false;
                                simulation.unregister(client);
                                kasa1.kolejkaDoKasy.remove(client);
                                klienciDoUsuniecia.add(client);
                                System.out.println("Klient " + client.id + " został obsłużony");
                                System.out.println("Kasjer - 1 zakończył obsługę klienta.");
                            }
                        }
                    }
                } else if (!kasa1.kasaDziala) {
                    if (kasa2.kolejkaDoKasy.size() >= 3) {
                        System.out.println("Kasa 1 znow dziala");
                        kasa1.kasaDziala = true;
                        kasa1.czasBezBlienta = 0;
                    }
                } else if (kasa1.kolejkaDoKasy.isEmpty()) {
                    kasa1.wKolejce();
                    if (kasa1.czasBezBlienta >= 3 && kasa1.kasaDziala) {
                        kasa1.kasaDziala = false;
                        System.out.println("Kasa 1 przestała działać");
                    }
                }

//            obsluga kasy2
                if (!kasa2.kolejkaDoKasy.isEmpty() && kasa2.kasaDziala) {
                    if (kasa2.kolejkaDoKasy.get(0) == client) {
                        if (!client.wObsludze) {
                            client.czasNaObsluge();
                            client.wObsludze = true;
                            System.out.println("kasjer - 2 podjął klienta.");
                        } else {

                            if (client.getCzasNaObsluge() == simulation.getTime()) { // ZAKONCZENIE OBSLUGI JESTLI CZAS PRZEZNACZONY NA NIA SIE SKONCZYL
                                client.wObsludze = false;
                                simulation.unregister(client);
                                kasa2.kolejkaDoKasy.remove(client);
                                klienciDoUsuniecia.add(client);
                                System.out.println("Klient " + client.id + " został obsłużony");
                                System.out.println("Kasjer - 2 zakończył obsługę klienta.");
                            }
                        }
                    }
                } else if (!kasa2.kasaDziala) {
                    if (kasa1.kolejkaDoKasy.size() >= 3) {
                        System.out.println("Kasa 2 znow dziala");
                        kasa2.kasaDziala = true;
                        kasa2.czasBezBlienta = 0;
                    }

                } else if (kasa2.kolejkaDoKasy.isEmpty()) {
                    kasa2.wKolejce();
                    if (!kasa2.kasaDziala) {
                        System.out.println("Kasa 2 przestała działać");

                    }

                }
            }
//            OBSLUGA USUWANIA OBSLUZONYCH I ZNIECIERPLIWIONYCH KLIENTOW
            for (Client klienciRem : klienciDoUsuniecia) {
                klienci.remove(klienciRem);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("");
        }
    }

}




