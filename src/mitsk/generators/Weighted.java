package mitsk.generators;

import java.util.Random;

public class Weighted implements Generator {
    public static double weight;
    private Obiekty c;
    private Obiekty b;
    private double wynik;
    private Obiekty a;

    public Weighted(Obiekty a, Obiekty b, Obiekty c) {
        this.a = a;
        this.b = b;
        this.c = c;
        weight = a.weight_object + b.weight_object + c.weight_object;

    }

    @Override
    public double getNext() {
        Random rand = new Random();
        double zmienna;
        zmienna = rand.nextDouble() * (1) + 0;
        double przedzial = zmienna * weight;

        if (a.weight_object >= przedzial) {
            zmienna = rand.nextDouble() * (1) + 0;
            wynik = a.from_object + (a.to_object - a.from_object) * zmienna;
        } else if (a.weight_object < przedzial && (a.weight_object + b.weight_object) >= przedzial) {
            zmienna = rand.nextDouble() * (1) + 0;
            wynik = b.from_object + (b.to_object - b.from_object) * zmienna;
        } else {
            zmienna = rand.nextDouble() * (1) + 0;
            wynik = c.from_object + (c.to_object - c.from_object) * zmienna;
        }
//        System.out.println("Wynik koncowy: "+ wynik);
        return wynik;
    }

    public static class Obiekty {

        public double from_object;
        public double to_object;
        public double weight_object;

        public Obiekty(double a, double b, double c) {
            from_object = a;
            to_object = b;
            weight_object = c;

        }
    }
}