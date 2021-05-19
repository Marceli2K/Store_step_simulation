package mitsk.generators;


import static java.lang.Thread.sleep;

public class RoundRobin implements Generator {
    int zmienna = 0;
    int[] zmienne;
    int tmp;
    int i = 0;

    public RoundRobin(int[] lista) {
        zmienne = lista;
        tmp = zmienne.length;
    }

    @Override
    public double getNext() throws InterruptedException {
        if (tmp > i) {
            return zmienne[i++];
        } else {
            i = 0;
            return zmienne[i++];
        }
    }
}
