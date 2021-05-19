import mitsk.simulation.discrete.step.Simulation;

public class main {
    public static void main(String args[]) throws InterruptedException {
        Simulation symulacja = new Simulation(100, 1);
        symulacja.run();
    }
}
