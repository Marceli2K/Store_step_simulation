package mitsk.montecarlo;

import mitsk.generators.Uniform;

public class Main {
    private static IntegralCalculator Licz;
    public static void main(String args[]) throws InterruptedException {

        Licz = new IntegralCalculator(new Uniform(0, 4)); // jako parametry klasy Unifotm należy podać przedział całkowania
        System.out.println("ZADANIE 2");
        zad2();
        System.out.println("\n");
        System.out.println("ZADANIE 3");
        zad3();

    }
    private static void zad2(){
        System.out.println("Integratal for function f(x)=3: "+Licz.integralCalculator( 100000, 0, 0, 3 )); // Punkt a  Jako parametry metody integralCalculator należy podać liczbe prób określoną zmienną trials,
        // natomiast kolejne argumenty to wartość funkcji, gdzie a to zmienna x^2, b to x natomiast c to wartosc jednostek

        System.out.println("Integratal for function f(x)=x: "+Licz.integralCalculator( 100000, 0, 1, 0 )); //punkt b
    }

    private static void zad3(){
        System.out.println("Integratal for function f(x)=2x-6, 15 trials: " + Licz.integralCalculator( 15, 0, 2, -6 )); //punkt a);
        System.out.println("Integratal for function f(x)=2x-6, 100 trials: " +Licz.integralCalculator( 100, 0, 2, -6 )); //punkt a
        System.out.println("Integratal for function f(x)=2x-6, 500 trials: " +Licz.integralCalculator( 500, 0, 2, -6 )); //punkt a
        System.out.println("Integratal for function f(x)=2x-6, 1000 trials: " +Licz.integralCalculator( 1000, 0, 2, -6 )); //punkt a

        System.out.println("\n");
        System.out.println("Integratal for function f(x)=-x^2+4x+2, 15 : " +Licz.integralCalculator( 15, -1, 4, 2 )); // punkt b
        System.out.println("Integratal for function f(x)=-x^2+4x+2, 100 : " +Licz.integralCalculator( 100, -1, 4, 2 )); // punkt b
        System.out.println("Integratal for function f(x)=-x^2+4x+2, 500 : " +Licz.integralCalculator( 500, -1, 4, 2 )); // punkt b
        System.out.println("Integratal for function f(x)=-x^2+4x+2, 1000 : " +Licz.integralCalculator( 1000, -1, 4, 2 )); // punkt b


    }

}
