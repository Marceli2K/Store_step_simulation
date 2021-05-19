package mitsk.generators;

import java.util.Random;

public class Uniform implements Generator{
    public static double max;
    public static double min;

    public Uniform(double x, double y){
    min = x;
    max = y;
    }

    @Override
    public double getNext() {
        Random rand = new Random();

        double zmienna;
        zmienna = rand.nextDouble()*(max-min)+min;

        return zmienna;
    }
}
