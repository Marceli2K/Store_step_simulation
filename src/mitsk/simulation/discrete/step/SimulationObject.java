package mitsk.simulation.discrete.step;

public abstract class SimulationObject {

    public SimulationObject(Simulation simulation) {
    }

    public abstract void timeAdvanced() throws InterruptedException;
}
